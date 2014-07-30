package br.com.amil.rankingbuilder.api.log.reader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.log.entry.EndMatchLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;

/**
 * Converte uma entrada de fim de {@link Match}
 */
class EndMatchLogEntryConverter implements LogEntryConverter<String> {
	
	private final Pattern pattern;
	private final DateTimeFormatter formatter;
	private LogEntryConverter<String> nextConverter;
	
	/**
	 * Cria o conversor.
	 *
	 * @param timePattern O padrão de tempo
	 * @param logPattern O padrão de log
	 */
	public EndMatchLogEntryConverter(String timePattern, String logPattern) {
		super();
		this.formatter = DateTimeFormatter.ofPattern(timePattern);
		this.pattern = Pattern.compile(logPattern);
	}

	/**
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter#convert(java.lang.Object)
	 */
	@Override
	public Optional<LogEntry> convert(String entry) {
		Matcher matcher = pattern.matcher(entry);
		
		if (matcher.matches()) {
			LocalDateTime logTime = LocalDateTime.parse(matcher.group(1), formatter);
			long gameId = Long.parseLong(matcher.group(2));
			
			LogEntry logEntry = new EndMatchLogEntry(logTime, gameId);
			
			return Optional.of(logEntry);
		}
		
		return nextConverter.convert(entry);
	}
	
	/**
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter#setNextConverter(br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter)
	 */
	@Override
	public LogEntryConverter<String> setNextConverter(LogEntryConverter<String> nextConverter) {
		this.nextConverter = nextConverter;
		
		return this;
	}
}
