package br.com.amil.rankingbuilder.api;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.log.reader.TextLogReader;
import br.com.amil.rankingbuilder.api.log.source.LogSource;

public class GameLogBuilderTest {

	@Test
	public void testBuild() throws GameLogException {
		LogSource<String> logSource = new LogSource<String>() {
			@Override
			public List<? extends String> retrieveAllEntries() throws GameLogException {
				List<String> list = new ArrayList<>();
				
				list.add("23/04/2013 15:34:22 - New match 11348965 has started");
				list.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
				list.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
				list.add("23/04/2013 15:39:22 - Match 11348965 has ended");
				
				list.add("23/04/2013 16:34:22 - New match 21348965 has started");
				list.add("23/04/2013 16:36:33 - <WORLD> killed Nick by DROWN");
				list.add("23/04/2013 16:39:22 - Match 21348965 has ended");
				
				return list;
			}
		};
		
		GameLog log = new GameLogBuilder<>(logSource)
			.withReader(new TextLogReader())
			.build();
		
		Deque<Match> matches = log.getMatches();
		assertEquals(matches.size(), 2);
		
		Match first = matches.poll();
		assertEquals(first.getId(), 11348965);
		assertEquals(first.getPlayers().size(), 2);
		assertEquals(first.getPlayer("Roman").getKillCount(), 1);
		assertEquals(first.getPlayer("Nick").getDeathCount(), 2);
		
		Match second = matches.poll();
		assertEquals(second.getId(), 21348965);
		assertEquals(second.getPlayers().size(), 1);
		assertEquals(second.getPlayer("Nick").getDeathCount(), 1);
		assertEquals(second.getEndTime(), LocalDateTime.of(2013, 4, 23, 16, 39, 22));
	}
}
