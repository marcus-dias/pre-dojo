package br.com.amil.rankingbuilder.api.award;

import java.util.Optional;

import br.com.amil.rankingbuilder.api.game.Player;

/**
 * {@link AwardChecker} que verifica se um {@link Player} vencedor não morreu.
 */
public class NoDeathWinAwardChecker implements AwardChecker<NoDeathWinAward> {

	private final Player winner;
	
	/**
	 * Cria o checker.
	 *
	 * @param {@link Player} Vencedor da partida 
	 */
	public NoDeathWinAwardChecker(Player winner) {
		super();
		this.winner = winner;
	}

	/** Verifica se este {@link Player} é vencedor e nã possui mortes.
	 * @see br.com.amil.rankingbuilder.api.award.AwardChecker#check(br.com.amil.rankingbuilder.api.game.Player)
	 */
	@Override
	public Optional<NoDeathWinAward> check(Player player) {
		if (player.equals(winner) && player.getDeathCount() == 0) {
			return Optional.of(new NoDeathWinAward(player));
		}
		else {
			return Optional.empty();
		}
	}

}
