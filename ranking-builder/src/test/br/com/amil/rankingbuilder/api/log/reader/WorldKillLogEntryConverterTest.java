package br.com.amil.rankingbuilder.api.log.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.entry.WorldKillLogEntry;

public class WorldKillLogEntryConverterTest {

	@Test
	public void testConvert() throws IOException, URISyntaxException {
		String log = "23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN";
		
		WorldKillLogEntryConverter converter = 
				new WorldKillLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.WORLD_KILL.getPattern());
		
		Optional<LogEntry> convert = converter.convert(log);
		
		assertTrue(convert.isPresent());
		assertEquals(convert.get().getClass(), WorldKillLogEntry.class);
		
		WorldKillLogEntry logEntry = (WorldKillLogEntry) convert.get();
		
		assertEquals(logEntry.getLogTime(), LocalDateTime.of(2013, 4, 23, 15, 36, 33));
		assertEquals(logEntry.getKilledPlayer(), "Nick");
		assertEquals(logEntry.getKillReason(), "DROWN");
	}
	
	@Test(expected=Exception.class)
	public void testConvertWithBadEntry() throws IOException, URISyntaxException {	
		String log = "BAD ENTRY";
		
		WorldKillLogEntryConverter converter = 
				new WorldKillLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.WORLD_KILL.getPattern());
		
		converter.convert(log);
	}
}
