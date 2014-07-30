package br.com.amil.rankingbuilder.api.award;

import java.util.Optional;

import br.com.amil.rankingbuilder.api.game.Player;

/**
 * Interface funcional que identifica que um {@link Player} � eleg�vel a um {@link Award}.
 *
 * @param <T> Tipo derivado de {@link Award}
 */
public interface AwardChecker<T extends Award> {
	
	/**
	 * Verifica se o {@link Player} � eleg�vel a este {@link Award}
	 *
	 * @param player {@link Player} que ser� verificado
	 * @return Indica se o {@link Award} foi concedido ou n�o
	 */
	Optional<T> check(Player player);
}
