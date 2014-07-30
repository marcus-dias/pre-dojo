package br.com.amil.rankingbuilder.api.game;

/**
 * Representa uma entrada em um {@link Ranking}.
 */
public class RankingEntry {
	
	private final Player player;
	private final int killCount;
	
	/**
	 * Cria uma nova entrada.
	 *
	 * @param player O Jogador
	 * @param killCount Número de mortes
	 */
	public RankingEntry(Player player, int killCount) {
		super();
		this.player = player;
		this.killCount = killCount;
	}
	
	/**
	 * Recupera o Jogador.
	 *
	 * @return O Jogador
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Recupera o número de mortes
	 *
	 * @return O número de mortes
	 */
	public int getKillCount() {
		return killCount;
	}
}
