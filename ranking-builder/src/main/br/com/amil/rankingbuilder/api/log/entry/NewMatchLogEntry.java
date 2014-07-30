package br.com.amil.rankingbuilder.api.log.entry;

import java.time.LocalDateTime;

import br.com.amil.rankingbuilder.api.game.Match;

/**
 * Representa uma entrada no log de novo {@link Match}.
 */
public class NewMatchLogEntry extends LogEntry {
	
	private final long matchId;
	
	/**
	 * Cria uma nova entrada.
	 *
	 * @param logTime O tempo do log
	 * @param matchId O id do {@link Match}
	 */
	public NewMatchLogEntry(LocalDateTime logTime, long matchId) {
		super(logTime);
		this.matchId = matchId;
	}

	/**
	 * Recupera o id do {@link Match}.
	 *
	 * @return O id do {@link Match}
	 */
	public long getMatchId() {
		return matchId;
	}
}
