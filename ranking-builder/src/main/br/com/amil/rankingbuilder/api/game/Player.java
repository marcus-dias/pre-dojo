package br.com.amil.rankingbuilder.api.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Representa um Jogador.
 */
public class Player {

	public static final Player WORLD = new Player(Messages.getString("Player.world")); //$NON-NLS-1$
	
	private final String name;
	private final List<Kill> kills = new ArrayList<>();
	private final List<Death> deaths = new ArrayList<>();

	/**
	 * Cria um Jogador.
	 *
	 * @param name nome do Jogador
	 */
	public Player(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Recupera o nome do Jogador.
	 *
	 * @return O nome do Jogador
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adiciona um {@link Kill} ao Jogador
	 *
	 * @param kill A morte
	 */
	public void addKill(Kill kill) {
		kills.add(kill);
	}
	
	/**
	 * Recupera os {@link Kill} do Jogador
	 *
	 * @return As mortes
	 */
	public List<Kill> getKills() {
		return Collections.unmodifiableList(kills);
	}
	
	/**
	 * Recupera a quantidade de {@link Kill} do Jogador
	 *
	 * @return A quantidade de {@link Kill} do Jogador
	 */
	public int getKillCount() {
		return kills.size();
	}
	
	/**
	 * Recupera os {@link Death} do Jogador
	 *
	 * @return As mortes
	 */
	public void addDeath(Death death) {
		deaths.add(death);
	}
	
	/**
	 * Recupera a quantidade de {@link Death} do Jogador
	 *
	 * @return A quantidade de {@link Death} do Jogador
	 */
	public int getDeathCount() {
		return deaths.size();
	}
	
	/**
	 * Recupera os {@link Death} do Jogador
	 *
	 * @return As mortes
	 */
	public List<Death> getDeaths() {
		return Collections.unmodifiableList(deaths);
	}
	
	/**
	 * Recupera a {@link Weapon} mais utilizada por um Jogador para matar.
	 *
	 * @return A {@link Weapon} mais utilizada por um Jogador para matar
	 */
	public Optional<Weapon> getMostUsedWeapon() {
		Optional<Weapon> weapon = kills.stream()
			.collect(Collectors.groupingBy(Kill::getWeaponUsed))
			.entrySet()
			.stream()
			.max(Comparator.comparingInt(w -> w.getValue().size()))
			.map(w -> w.getKey());
		
		return weapon;
	}

	/**
	 * Recupera a maior sequência de mortes efetuadas pelo Jogador.
	 *
	 * @return A maior sequência de mortes efetuadas pelo Jogador.
	 */
	public Optional<Streak> getTopStreak() {
		return Streak.topStreakFrom(this);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
