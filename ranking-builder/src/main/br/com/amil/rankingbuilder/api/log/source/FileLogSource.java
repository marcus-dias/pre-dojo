package br.com.amil.rankingbuilder.api.log.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import br.com.amil.rankingbuilder.api.GameLogException;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Representa uma fonte de Log proveniente de um arquivo.
 */
public class FileLogSource implements LogSource<String> {
	
	private final Path logFilePath;
	
	/**
	 * Cria a fonte.
	 *
	 * @param O {@link Path} do arquivo
	 */
	public FileLogSource(Path logFilePath) {
		this.logFilePath = logFilePath;
	}

	/**
	 * @see br.com.amil.rankingbuilder.api.log.source.LogSource#retrieveAllEntries()
	 */
	@Override
	public List<String> retrieveAllEntries() throws GameLogException {
		try {
			return Files.readAllLines(logFilePath);
		} catch (IOException e) {
			throw new GameLogException(String.format(Messages.getString("FileLogSource.openFileError"), logFilePath.toString())); //$NON-NLS-1$
		}
	}
}
