package br.com.amil.rankingbuilder.api.log.entry;

import java.time.LocalDateTime;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;

/**
 * Representa uma entrada assassinato em {@link Match}
 */
public class KillLogEntry extends LogEntry {
	
	private final String killerPlayer;
	private final String killedPlayer;
	private final String weaponUsed;
	
	/**
	 * Cria a entrada.
	 *
	 * @param logTime O tempo
	 * @param killerPlayer {@link Player} matador
	 * @param killedPlayer {@link Player} morto
	 * @param weaponUsed {@link Weapon} arma utilizada
	 */
	public KillLogEntry(LocalDateTime logTime, String killerPlayer,
			String killedPlayer, String weaponUsed) {
		super(logTime);
		
		this.killerPlayer = killerPlayer;
		this.killedPlayer = killedPlayer;
		this.weaponUsed = weaponUsed;
	}

	/**
	 * Recupera o {@link Player} matador.
	 *
	 * @return O {@link Player} matador
	 */
	public String getKillerPlayer() {
		return killerPlayer;
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
	 * Recupera a {@link Weapon} utilizada.
	 *
	 * @return A {@link Weapon} utilizada
	 */
	public String getWeaponUsed() {
		return weaponUsed;
	}
}
