package br.com.amil.rankingbuilder.api.log.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.log.entry.KillLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;

public class KillLogEntryConverterTest {

	@Test
	public void testConvert() throws IOException, URISyntaxException {	
		String log = "23/04/2013 15:36:04 - Roman killed Nick using M16";
		
		KillLogEntryConverter converter = 
				new KillLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.KILL.getPattern());
		
		Optional<LogEntry> convert = converter.convert(log);
		
		assertTrue(convert.isPresent());
		assertEquals(convert.get().getClass(), KillLogEntry.class);
		
		KillLogEntry logEntry = (KillLogEntry) convert.get();
		
		assertEquals(logEntry.getLogTime(), LocalDateTime.of(2013, 4, 23, 15, 36, 4));
		assertEquals(logEntry.getKillerPlayer(), "Roman");
		assertEquals(logEntry.getKilledPlayer(), "Nick");
		assertEquals(logEntry.getWeaponUsed(), "M16");
	}
	
	@Test(expected=Exception.class)
	public void testConvertWithBadEntry() throws IOException, URISyntaxException {	
		String log = "BAD ENTRY";
		
		KillLogEntryConverter converter = 
				new KillLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.KILL.getPattern());
		
		converter.convert(log);
	}
}
