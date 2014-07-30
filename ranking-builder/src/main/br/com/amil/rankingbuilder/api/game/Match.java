package br.com.amil.rankingbuilder.api.game;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Representa uma Partida.
 */
public class Match {
	
	private final Map<String, Player> players = new HashMap<>();
	private final long id;
	private final LocalDateTime startTime;
	private LocalDateTime endTime;
	
	/**
	 * Cria uma partida.
	 *
	 * @param id Número identificador da partida
	 * @param startTime Hora de ínicio da partida
	 */
	public Match(long id, LocalDateTime startTime) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = startTime;
	}
	
	/**
	 * Identificação da partida
	 *
	 * @return Identificação da partida
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Hora de início da partida
	 *
	 * @return Hora de início da partida
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	/**
	 * Adiciona um {@link Player} a partida
	 *
	 * @param player o {@link Player}
	 */
	public void addPlayer(Player player) {
		players.put(player.getName(), player);
	}
	
	/**
	 * Recupera todos os {@link Player} da partida
	 *
	 * @return Todos os {@link Player} da partida
	 */
	public Collection<Player> getPlayers() {
		return Collections.unmodifiableCollection(players.values());
	}
	
	/**
	 * Recupera o {@link Player} pelo nome
	 *
	 * @param playerName Nome do {@link Player}
	 * @return o {@link Player}
	 */
	public Player getPlayer(String playerName) {
		return players.get(playerName);
	}
	
	/**
	 * Recupera o maior {@link Streak} da partida
	 *
	 * @return O maior {@link Streak} da partida
	 */
	public Optional<Streak> getTopStreak() {
		Optional<Streak> streak = players.values().stream()
			.map(Player::getTopStreak)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.max(Comparator.comparingInt(s -> s.getKillsInARowCount()));
			
		return streak;
	}
	
	/**
	 * Atribui o tempo do final da Partida
	 *
	 * @param endTime o tempo do final da Partida
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Recupera o tempo final da Partida
	 *
	 * @return o tempo final da Partida
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * Verifica se a Partida contém o {@link Player} identificado pelo nome
	 *
	 * @param playerName O nome do {@link Player}
	 * @return true Se contém o {@link Player}
	 */
	public boolean containsPlayer(String playerName) {
		return players.containsKey(playerName);
	}
}
