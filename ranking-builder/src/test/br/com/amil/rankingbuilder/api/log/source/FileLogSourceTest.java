package br.com.amil.rankingbuilder.api.log.source;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.GameLogException;

public class FileLogSourceTest {

	@Test
	public void testRetrieveAllEntries() throws IOException, GameLogException {
		Path tempFile = Files.createTempFile("game", "log");
		
		String line1 = "LOG 1";
		String line2 = "LOG 2";
		
		Files.write(tempFile, line1.concat(System.getProperty("line.separator")).getBytes());
		Files.write(tempFile, line2.getBytes(), StandardOpenOption.APPEND);
		
		FileLogSource logSource = new FileLogSource(tempFile);
		List<String> entries = logSource.retrieveAllEntries();
		
		assertEquals(entries.size(), 2);
		assertEquals(entries.get(0), line1);
		assertEquals(entries.get(1), line2);
	}
	
	@Test(expected=GameLogException.class)
	public void testRetrieveAllEntriesWithBadPath() throws IOException, GameLogException {
		Path tempFile = Files.createTempFile("game", "log");
		
		Files.delete(tempFile);
		
		FileLogSource logSource = new FileLogSource(tempFile);
		logSource.retrieveAllEntries();
	}
}
