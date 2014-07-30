package br.com.amil.rankingbuilder.api.log.source;

import java.util.List;

import br.com.amil.rankingbuilder.api.GameLogException;

/**
 * Representa uma fonte de Log.
 *
 * @param <T> O tipo de dado contido no Log.
 */
public interface LogSource<T> {

	/**
	 * Recupera todas as entradas da fonte.
	 *
	 * @return A lista de entradas
	 * @throws GameLogException Se occorrer um problema para acessar a fonte
	 */
	List<? extends T> retrieveAllEntries() throws GameLogException;
	
}
