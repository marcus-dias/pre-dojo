package br.com.amil.rankingbuilder.api.log.reader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.entry.WorldKillLogEntry;

/**
 * Converte uma entrada de morte de mundo de um {@link Match}
 */
class WorldKillLogEntryConverter implements LogEntryConverter<String> {
	
	private final Pattern pattern;
	private final DateTimeFormatter formatter;
	private LogEntryConverter<String> nextConverter;
	
	/**
	 * Cria o conversor.
	 *
	 * @param timePattern O padrão de tempo
	 * @param logPattern O padrão de log
	 */
	public WorldKillLogEntryConverter(String timePattern, String logPattern) {
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
			String killedPlayer = matcher.group(2);
			String killReason = matcher.group(3);
			
			LogEntry logEntry = new WorldKillLogEntry(logTime, killedPlayer, killReason);
			
			return Optional.of(logEntry);
		}
		
		return nextConverter.convert(entry);
	}
	
	/**
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter#setNextConverter(br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter)
	 */
	@Override
	public LogEntryConverter<String> setNextConverter(LogEntryConverter<String> converter) {
		this.nextConverter = converter;
		
		return this;
	}
}
