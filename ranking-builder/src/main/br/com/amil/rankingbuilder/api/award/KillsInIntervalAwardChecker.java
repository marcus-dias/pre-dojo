package br.com.amil.rankingbuilder.api.award;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Player;

/**
 * {@link AwardChecker} que verifica se um {@link Player} matou N vezes em um intervalo X em minutos.
 */
public class KillsInIntervalAwardChecker implements AwardChecker<KillsInIntervalAward> {
	
	private final int kills;
	private final int minutes;
	
	/**
	 * Cria o checker com base no número de mortes no intervalo em minutos especificados.
	 *
	 * @param O número de {@link Kill}
	 * @param O intervalo em minutos
	 */
	public KillsInIntervalAwardChecker(int kills, int minutes) {
		this.kills = kills;
		this.minutes = minutes;
	}

	/** Verifica se o {@link Player} matou N vezes no intervalo de X minutos.
	 * @see br.com.amil.rankingbuilder.api.award.AwardChecker#check(br.com.amil.rankingbuilder.api.game.Player)
	 */
	@Override
	public Optional<KillsInIntervalAward> check(Player player) {
		List<Kill> killList = player.getKills().stream()
			.sorted(Comparator.comparing(Kill::getTime)).collect(Collectors.toList());
		
		int offset = kills - 1;
		
		int seconds = minutes * 60;
		
		for (int i = 0; offset < killList.size(); i++, offset+=kills) {
			LocalDateTime startKill = killList.get(i).getTime();
			LocalDateTime endKill = killList.get(offset).getTime();
			
			long durationInSeconds = Duration.between(startKill, endKill).toMillis() / 1000;
			
			if (durationInSeconds <= seconds) {
				return Optional.of(new KillsInIntervalAward(player, minutes, kills));
			}
		}
		
		return Optional.empty();
	}
}
