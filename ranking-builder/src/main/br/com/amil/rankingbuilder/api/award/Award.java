package br.com.amil.rankingbuilder.api.award;

import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.util.Messages;


/**
 * Classe base que representa um Award. Um Award é uma recompensa dada a um {@link Player} por atingir um objetivo.<br/>
 * 
 * O Award é concedido a um {@link Player}, sendo o mesmo verificado por um {@link AwardChecker}.
 */
public abstract class Award {
	
	protected final Player player;

	/**
	 * Cria um Award para o {@link Player}
	 *
	 * @param player Jogador que recebeu um Award
	 */
	public Award(Player player) {
		super();
		this.player = player;
	}

	/**
	 * Retorna o {@link Player} que recebeu o Award
	 *
	 * @return o {@link Player} que recebeu o Award
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Recupera a mensagem do Award. É o nome do {@link Player} seguido pela mensagem da subclasse.
	 *
	 * @return Mensagem do Award
	 */
	public String awardMessage() {
		return String.format(Messages.getString("Award.awardPrefix"), player.getName()).concat(getMessage()); //$NON-NLS-1$
	}
	
	/**
	 * Recupera a mensagem personalizada do Award. Deve ser sobrescrito por uma subclasse.
	 *
	 * @return A mensagem
	 */
	protected abstract String getMessage();
}
