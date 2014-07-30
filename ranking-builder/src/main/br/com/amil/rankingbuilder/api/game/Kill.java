package br.com.amil.rankingbuilder.api.game;

import java.time.LocalDateTime;


/**
 * Representa uma morte efetuada por um {@link Player}
 */
public class Kill implements TimedEvent {
	
	private final Player killedPlayer;
	private final Weapon weaponUsed;
	private final LocalDateTime time;
	
	/**
	 * Cria uma morte.
	 *
	 * @param killedPlayer {@link Player} morto
	 * @param weaponUsed {@link Weapon} arma utilizada
	 * @param time Hora da morte
	 */
	public Kill(Player killedPlayer, Weapon weaponUsed, LocalDateTime time) {
		super();
		this.killedPlayer = killedPlayer;
		this.weaponUsed = weaponUsed;
		this.time = time;
	}
	
	/**
	 * {@link Player} morto.
	 *
	 * @return {@link Player} morto
	 */
	public Player getKilledPlayer() {
		return killedPlayer;
	}
	
	/**
	 * {@link Weapon} utilizada.
	 *
	 * @return {@link Weapon} utilizada
	 */
	public Weapon getWeaponUsed() {
		return weaponUsed;
	}
	
	/**
	 * @see br.com.amil.rankingbuilder.api.game.TimedEvent#getTime()
	 */
	@Override
	public LocalDateTime getTime() {
		return time;
	}
}
