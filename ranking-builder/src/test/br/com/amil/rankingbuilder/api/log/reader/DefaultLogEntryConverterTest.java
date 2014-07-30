package br.com.amil.rankingbuilder.api.log.reader;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.log.entry.LogEntry;

public class DefaultLogEntryConverterTest {

	@Test
	public void testConvert() {
		Optional<LogEntry> convert = new DefaultLogEntryConverter().convert("test");
		
		assertFalse(convert.isPresent());
		
		convert = new DefaultLogEntryConverter().convert("another");
		
		assertFalse(convert.isPresent());
	}

}
