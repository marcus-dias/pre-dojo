package br.com.amil.rankingbuilder.api.log.entry;

import java.time.LocalDateTime;

import br.com.amil.rankingbuilder.api.game.Match;

/**
 * Representa uma entrada no Log de fim de {@link Match}
 */
public class EndMatchLogEntry extends LogEntry {
	
	private final long matchId;
	
	/**
	 * Cria a entrada.
	 *
	 * @param logTime O tempo
	 * @param matchId O id do {@link Match}
	 */
	public EndMatchLogEntry(LocalDateTime logTime, long matchId) {
		super(logTime);
		this.matchId = matchId;
	}
	
	/**
	 * Recupera o id do {@link Match}
	 *
	 * @return O id do {@link Match}
	 */
	public long getMatchId() {
		return matchId;
	}
}
