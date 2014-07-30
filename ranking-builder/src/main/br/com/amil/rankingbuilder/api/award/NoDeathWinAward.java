package br.com.amil.rankingbuilder.api.award;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Award que representa que um {@link Player} vencedor de um {@link Match} não morreu.
 */
public class NoDeathWinAward extends Award {

	/**
	 * Cria este Award.
	 *
	 * @param player {@link Player} a ser verificado
	 */
	public NoDeathWinAward(Player player) {
		super(player);
	}

	/**
	 * Retorna a mensagem de vencedor não morto.
	 * @see br.com.amil.rankingbuilder.api.award.Award#getMessage()
	 */
	@Override
	public String getMessage() {
		return String.format(Messages.getString("NoDeathWinAward.message")); //$NON-NLS-1$
	}
}
