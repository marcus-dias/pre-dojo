package br.com.amil.rankingbuilder.api.log.entry;

import java.time.LocalDateTime;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;

/**
 * Representa uma morte em {@link Match} causada pelo mundo
 */
public class WorldKillLogEntry extends LogEntry {
	
	private final String killedPlayer;
	private final String killReason;
	
	/**
	 * Cria a entrada.
	 *
	 * @param logTime O tempo do log
	 * @param killedPlayer O {@link Player} morto
	 * @param killReason O motivo da morte
	 */
	public WorldKillLogEntry(LocalDateTime logTime, String killedPlayer, String killReason) {
		super(logTime);
		
		this.killedPlayer = killedPlayer;
		this.killReason = killReason;
	}

	/**
	 * Recupera o {@link Player} morto.
	 *
	 * @return O {@link Player} morto
	 */
	public String getKilledPlayer() {
		return killedPlayer;
	}

	/**
	 * Recupera o motivo da morte.
	 *
	 * @return O motivo da morte
	 */
	public String getKillReason() {
		return killReason;
	}
}
