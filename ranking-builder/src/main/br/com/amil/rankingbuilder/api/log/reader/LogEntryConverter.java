package br.com.amil.rankingbuilder.api.log.reader;

import java.util.Optional;

import br.com.amil.rankingbuilder.api.log.entry.LogEntry;

/**
 * Representa um conversor de entrada do log.
 *
 * @param <I> o tipo de entrada
 */
public interface LogEntryConverter<I> {
	
	/**
	 * Converte a entrada no tipo especificado.
	 *
	 * @param entry A entrada
	 * @return A entrada de log
	 */
	Optional<LogEntry> convert(I entry);
	
	/**
	 * Atribui o próximo conversor na cadeia de conversores
	 *
	 * @param converter Próximo conversor na cadeia
	 * @return O conversor especificado
	 */
	LogEntryConverter<String> setNextConverter(LogEntryConverter<String> converter);
}
