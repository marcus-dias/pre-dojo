package br.com.amil.rankingbuilder.api.log.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.entry.NewMatchLogEntry;

public class NewMatchLogEntryConverterTest {

	@Test
	public void testConvert() throws IOException, URISyntaxException {
		String log = "23/04/2013 15:34:22 - New match 11348965 has started";
		
		NewMatchLogEntryConverter converter = 
				new NewMatchLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.NEW_MATCH.getPattern());
		
		Optional<LogEntry> convert = converter.convert(log);
		
		assertTrue(convert.isPresent());
		assertEquals(convert.get().getClass(), NewMatchLogEntry.class);
		
		NewMatchLogEntry logEntry = (NewMatchLogEntry) convert.get();
		
		assertEquals(logEntry.getLogTime(), LocalDateTime.of(2013, 4, 23, 15, 34, 22));
		assertEquals(logEntry.getMatchId(), Long.parseLong("11348965"));
	}
	
	@Test(expected=Exception.class)
	public void testConvertWithBadEntry() throws IOException, URISyntaxException {	
		String log = "BAD ENTRY";
		
		NewMatchLogEntryConverter converter = 
				new NewMatchLogEntryConverter(LogPatterns.TIME.getPattern(), LogPatterns.NEW_MATCH.getPattern());
		
		converter.convert(log);
	}
}
