package br.com.amil.rankingbuilder.api.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Representa uma sequ�ncia de {@link Kill} efetuadas por um {@link Player}.
 */
public class Streak {
	
	private final List<Kill> killsInARow = new ArrayList<>();
	private final Player player;
	
	/**
	 * Cria um Streak.
	 *
	 * @param player O Jogador
	 */
	private Streak(Player player) {
		this.player = player;
	}
	
	/**
	 * Cria o maior Streak de um {@link Player}
	 *
	 * @param player O Jogador
	 * @return O Streak
	 */
	static Optional<Streak> topStreakFrom(Player player) {
		List<Streak> streaks = new ArrayList<>();
		Streak currentStreak = new Streak(player);
		
		List<TimedEvent> events = getTimedEventsFrom(player);
		
		streaks.add(currentStreak);
		
		List<TimedEvent> sorted = events.stream()
				.sorted((t1, t2) -> t1.getTime().compareTo(t2.getTime())).collect(Collectors.toList());
		
		for (TimedEvent event : sorted) {
			if (event instanceof Kill) {
				currentStreak.killsInARow.add((Kill) event);
			}
			else {
				currentStreak = new Streak(player);
				streaks.add(currentStreak);
			}
		}
		
		return streaks.stream()
			.max(Comparator.comparingInt(s -> s.getKillsInARowCount()));
	}
	
	/**
	 * Recupera as {@link Kill} em sequ�ncia
	 *
	 * @return As {@link Kill} em sequ�ncia
	 */
	public List<Kill> getKillsInARow() {
		return Collections.unmodifiableList(killsInARow);
	}
	
	/**
	 * Recupera o n�mero de {@link Kill} em sequ�ncia
	 *
	 * @return O n�mero de {@link Kill} em sequ�ncia
	 */
	public int getKillsInARowCount() {
		return Collections.unmodifiableList(killsInARow).size();
	}
	
	/**
	 * Recupera o {@link Player}
	 *
	 * @return O {@link Player}
	 */
	public Player getPlayer() {
		return player;
	}
	
	private static List<TimedEvent> getTimedEventsFrom(Player player) {
		List<TimedEvent> events = new ArrayList<>();
		
		events.addAll(player.getKills());
		events.addAll(player.getDeaths());
		
		return events;
	}
}
