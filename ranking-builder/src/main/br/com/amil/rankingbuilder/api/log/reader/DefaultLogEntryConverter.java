package br.com.amil.rankingbuilder.api.log.reader;

import java.util.Optional;

import br.com.amil.rankingbuilder.api.log.entry.LogEntry;

/**
 * Conversor que retorna sempre uma entrada vazia.
 */
class DefaultLogEntryConverter implements LogEntryConverter<String> {

	/**
	 * Retorna uma entrada vazia.
	 * 
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter#convert(java.lang.Object)
	 */
	@Override
	public Optional<LogEntry> convert(String entry) {
		return Optional.empty();
	}

	/**
	 * @see br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter#setNextConverter(br.com.amil.rankingbuilder.api.log.reader.LogEntryConverter)
	 */
	@Override
	public LogEntryConverter<String> setNextConverter(LogEntryConverter<String> converter) {
		return this;
	}
}
