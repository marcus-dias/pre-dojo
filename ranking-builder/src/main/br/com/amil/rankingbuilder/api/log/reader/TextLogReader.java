package br.com.amil.rankingbuilder.api.log.reader;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.rankingbuilder.api.GameLogException;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.source.LogSource;

/**
 * Leitor de log que interpreta entradas de texto.
 */
public class TextLogReader implements LogReader<String> {
	
	private LogEntryConverter<String> converterChain;

	/**
	 * Cria o leitor de log.
	 */
	public TextLogReader() {
		super();
		
		buildConverterChain();
	}

	/**
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogReader#readEntriesFrom(br.com.amil.rankingbuilder.api.log.source.LogSource)
	 */
	@Override
	public List<LogEntry> readEntriesFrom(LogSource<? extends String> logSource) throws GameLogException {
		List<LogEntry> entries = new ArrayList<>();
		
		logSource.retrieveAllEntries().stream()
			.map(converterChain::convert)
			.filter(entry -> entry.isPresent())
			.forEach(entry -> entries.add(entry.get()));
		
		return entries;
	}
	
	private void buildConverterChain() {
		String timePattern = LogPatterns.TIME.getPattern();
		
		converterChain = 
			new NewMatchLogEntryConverter(timePattern, LogPatterns.NEW_MATCH.getPattern())
			.setNextConverter(new KillLogEntryConverter(timePattern, LogPatterns.KILL.getPattern())
			.setNextConverter(new WorldKillLogEntryConverter(timePattern, LogPatterns.WORLD_KILL.getPattern())
			.setNextConverter(new EndMatchLogEntryConverter(timePattern, LogPatterns.END_MATCH.getPattern())
			.setNextConverter(new DefaultLogEntryConverter()))));
	}
}
