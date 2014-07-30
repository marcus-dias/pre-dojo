package br.com.amil.rankingbuilder.api.log.reader;

import java.util.List;

import br.com.amil.rankingbuilder.api.GameLogException;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.source.LogSource;

/**
 * Representa um leitor de log.
 *
 * @param <T> O tipo de entrada do log
 */
public interface LogReader<T> {
	
	/**
	 * Efetua a leitura das entradas de log e as converte para {@link LogEntry}.
	 *
	 * @param logSource A fonte de log
	 * @return Lista de {@link LogEntry}
	 * @throws GameLogException Se houver erro na leitura de entradas
	 */
	List<LogEntry> readEntriesFrom(LogSource<? extends T> logSource) throws GameLogException;
}
