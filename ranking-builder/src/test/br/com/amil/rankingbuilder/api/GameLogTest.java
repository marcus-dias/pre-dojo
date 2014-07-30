package br.com.amil.rankingbuilder.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Deque;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.game.Death;
import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;

public class GameLogTest {

	@Test
	public void testNewMatch() {
		GameLog log = new GameLog();
		
		Match match = new Match(1, LocalDateTime.now());
		Match match2 = new Match(2, LocalDateTime.now());
		
		log.newMatch(match);
		log.newMatch(match2);
		
		Deque<Match> matches = log.getMatches();
		
		assertSame(matches.poll(), match);
		assertSame(matches.poll(), match2);
	}

	@Test
	public void testAddDeathToPlayer() {
		GameLog log = new GameLog();
		
		Match match = new Match(1, LocalDateTime.now());
		
		log.newMatch(match);
		log.addDeathToPlayer("test", Death.fromKill(new Player("killer"), getKill()));
		
		Match m = log.getMatches().poll();
		
		assertTrue(m.containsPlayer("test"));
		assertEquals(m.getPlayer("test").getDeathCount(), 1);
	}

	

	@Test
	public void testEndMatch() {
		GameLog log = new GameLog();
		
		Match match = new Match(1, LocalDateTime.now());
		LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);
		
		Match endMatch = new Match(2, endTime);
		
		log.newMatch(match);
		log.endMatch(endMatch);
		
		Match m = log.getMatches().poll();
		
		assertSame(m, match);
		assertEquals(m.getEndTime(), endTime);
	}

	@Test
	public void testAddKillToPlayer() {
		GameLog log = new GameLog();
		
		Match match = new Match(1, LocalDateTime.now());
		
		log.newMatch(match);
		log.addKillToPlayer("test", getKill());
		
		Match m = log.getMatches().poll();
		
		assertTrue(m.containsPlayer("test"));
		assertEquals(m.getPlayer("test").getKillCount(), 1);
	}
	
	private Kill getKill() {
		return new Kill(new Player("killed"), new Weapon("AK47"), LocalDateTime.now());
	}
}
