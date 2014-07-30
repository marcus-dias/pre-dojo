package br.com.amil.rankingbuilder.api.game;

import java.time.LocalDateTime;

/**
 * Representa um evento de um {@link Match} associado a um tempo.
 */
interface TimedEvent {
	
	/**
	 * Recupera o tempo deste evento.
	 *
	 * @return O tempo
	 */
	LocalDateTime getTime();
}
