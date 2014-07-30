package br.com.amil.rankingbuilder.api.game;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Representa um Ranking de um determinado {@link Match}
 */
public class Ranking {
	
	private List<RankingEntry> rankingEntries;
	private final Match match;
	
	/**
	 * Cria um Ranking.
	 *
	 * @param match {@link Match} a ser criado o Ranking
	 */
	private Ranking(Match match) {
		this.match = match;
	}
	
	/**
	 * Cria o Ranking a partir do {@link Match}
	 *
	 * @param match o {@link Match}
	 * @return O Ranking
	 */
	public static Ranking buildRankingFromMatch(Match match) {
		Ranking ranking = new Ranking(match);
		
		ranking.rankingEntries = match.getPlayers().stream()
			.sorted(Comparator.comparing(p -> p.getKillCount(), Comparator.reverseOrder()))
			.map(p -> new RankingEntry(p, p.getKillCount()))
			.collect(Collectors.toList());
		
		return ranking;
	}
	
	/**
	 * Recupera o vencedor.
	 *
	 * @return O vencedor
	 */
	public Optional<Player> getWinner() {
		if (rankingEntries.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(rankingEntries.get(0).getPlayer());
	}
	
	/**
	 * Recupera as entradas do Ranking
	 *
	 * @return As entradas do Ranking
	 */
	public List<RankingEntry> getRankingEntries() {
		return Collections.unmodifiableList(rankingEntries);
	}

	/**
	 * Retorna o {@link Match} deste Ranking
	 *
	 * @return o {@link Match}
	 */
	public Match getMatch() {
		return match;
	}
}
