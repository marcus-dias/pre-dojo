package br.com.amil.rankingbuilder.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.amil.rankingbuilder.api.award.Award;
import br.com.amil.rankingbuilder.api.award.AwardChecker;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;

/**
 * Coletor de todos os {@link Award} a partir de um {@link Match}
 */
public class AwardCollector {
	
	public final List<AwardChecker<? extends Award>> checkers;
	
	/**
	 * Cria o coletor.
	 *
	 * @param awardCheckers Lista de {@link AwardChecker} que serão aplicados no {@link Match}
	 */
	@SafeVarargs
	public AwardCollector(AwardChecker<? extends Award>... awardCheckers) {
		this.checkers = new ArrayList<>(Arrays.asList(awardCheckers));
	}
	
	/**
	 * Adiciona um {@link AwardChecker}
	 *
	 * @param awardChecker {@link AwardChecker} a adicionar
	 * @return este coletor
	 */
	public AwardCollector addChecker(AwardChecker<? extends Award> awardChecker) {
		checkers.add(awardChecker);
		return this;
	}
	
	/**
	 * Coleta os {@link Award} do {@link Match}
	 *
	 * @param match {@link Match} a ser verificado
	 * @return lista de {@link Award}
	 */
	public List<Award> collectAwards(Match match) {
		List<Award> awards = new ArrayList<>();
		 
		match.getPlayers().stream()
			.map(p -> checkAll(p))
			.forEach(awards::addAll);
		
		return awards;
	}
	
	private List<Award> checkAll(Player player) {
		List<Award> collect = checkers.stream()
			.map(c -> c.check(player))
			.filter(Optional::isPresent)
			.map(o -> (Award) o.get())
			.collect(Collectors.toList());
			
		return collect;
	}
}
