package br.com.amil.rankingbuilder.api.award;

import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Award que representa que uma série de {@link Kill} realizados em um intevalor de minutos.
 */
public class KillsInIntervalAward extends Award {
	
	private final int minutes;
	private final int numberOfKills;
	
	/**
	 * Cria este Award com base no número de mortes em determinado intervalo em minutos.
	 *
	 * @param player {@link Player} que atingiu o objetivo
	 * @param minutes Intervalo em minutos
	 * @param numberOfKills Número de mortes
	 */
	public KillsInIntervalAward(Player player, int minutes, int numberOfKills) {
		super(player);
		this.minutes = minutes;
		this.numberOfKills = numberOfKills;
	}
	
	/**
	 * Mensagem que indica o número de mortes e o intevalo em minutos.
	 * @see br.com.amil.rankingbuilder.api.award.Award#getMessage()
	 */
	@Override
	public String getMessage() {
		return String.format(Messages.getString("KillsInIntervalAward.message"), numberOfKills, minutes); //$NON-NLS-1$
	}

}
