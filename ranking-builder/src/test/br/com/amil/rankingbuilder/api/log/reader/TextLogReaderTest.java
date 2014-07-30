package br.com.amil.rankingbuilder.api.log.reader;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.GameLogException;
import br.com.amil.rankingbuilder.api.log.entry.EndMatchLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.KillLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.entry.NewMatchLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.WorldKillLogEntry;
import br.com.amil.rankingbuilder.api.log.source.LogSource;

public class TextLogReaderTest {
	
	@Test
	public void testReadEntriesFrom() throws GameLogException {
		LogSource<String> logSource = new LogSource<String>() {
			@Override
			public List<? extends String> retrieveAllEntries() throws GameLogException {
				List<String> list = new ArrayList<>();
				
				list.add("23/04/2013 15:34:22 - New match 11348965 has started");
				list.add("23/04/2013 15:36:04 - Roman killed Nick using M16");
				list.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
				list.add("23/04/2013 15:39:22 - Match 11348965 has ended");				
				
				return list;
			}
		};
		
		TextLogReader logReader = new TextLogReader();
		List<LogEntry> entries = logReader.readEntriesFrom(logSource);
		
		assertEquals(entries.size(), 4);
		
		assertEquals(entries.get(0).getClass(), NewMatchLogEntry.class);
		assertEquals(entries.get(1).getClass(), KillLogEntry.class);
		assertEquals(entries.get(2).getClass(), WorldKillLogEntry.class);
		assertEquals(entries.get(3).getClass(), EndMatchLogEntry.class);
	}
	
	@Test
	public void testReadEntriesFromWithBadEntries() throws GameLogException {
		LogSource<String> logSource = new LogSource<String>() {
			@Override
			public List<? extends String> retrieveAllEntries() throws GameLogException {
				List<String> list = new ArrayList<>();
				
				list.add("23/04/2013 15:34:22 - New match 11348965 has started");
				list.add("BAD ENTRY");
				list.add("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
				list.add("BAD ENTRY");				
				
				return list;
			}
		};
		
		TextLogReader logReader = new TextLogReader();
		List<LogEntry> entries = logReader.readEntriesFrom(logSource);
		
		assertEquals(entries.size(), 2);
		
		assertEquals(entries.get(0).getClass(), NewMatchLogEntry.class);
		assertEquals(entries.get(1).getClass(), WorldKillLogEntry.class);
	}
}
