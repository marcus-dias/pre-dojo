package br.com.amil.rankingbuilder.api.game;

import java.time.LocalDateTime;

/**
 * Representa a morte de um {@link Player}
 */
public class Death implements TimedEvent {
	
	private final Player killedBy;
	private final Weapon weaponUsed;
	private final LocalDateTime time;
	
	/**
	 * Cria uma Morte a partir de um {@link Kill}
	 *
	 * @param player {@link Player} que matou
	 * @param kill a morte
	 * @return a morte
	 */
	public static Death fromKill(Player player, Kill kill) {
		return new Death(player, kill.getWeaponUsed(), kill.getTime());
	}
	
	/**
	 * Cria a morte.
	 *
	 * @param killedBy {@link Player} que matou
	 * @param weaponUsed {@link Weapon} utilizada
	 * @param time Hora da morte
	 */
	public Death(Player killedBy, Weapon weaponUsed, LocalDateTime time) {
		super();
		this.killedBy = killedBy;
		this.weaponUsed = weaponUsed;
		this.time = time;
	}
	
	/**
	 * {@link Player} matador
	 *
	 * @return {@link Player} matador
	 */
	public Player getKilledBy() {
		return killedBy;
	}
	
	/**
	 * {@link Weapon} utilizada
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
