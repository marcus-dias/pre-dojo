package br.com.amil.rankingbuilder.api;

import java.util.ArrayDeque;
import java.util.Deque;

import br.com.amil.rankingbuilder.api.game.Death;
import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Representa o Log de um Jogo, contendo todos os {@link Match}. <br/>
 * Os {@link Match} são enfileirados como no Log de origem.
 */
public class GameLog {
	
	private final Deque<Match> matches = new ArrayDeque<>();
	
	GameLog() {
		
	}

	/**
	 * Recupera todos os {@link Match} enfileirados.
	 *
	 * @return Os {@link Match} deste Log
	 */
	public Deque<Match> getMatches() {
		return new ArrayDeque<Match>(matches);
	}
	

	void newMatch(Match match) {
		matches.add(match);
	}

	void addDeathToPlayer(String playerName, Death death) {
		Match match = getCurrentMatch();
		
		Player player = getOrCreatePlayer(playerName, match);
		
		player.addDeath(death);
	}

	void endMatch(Match match) {
		Match currentMatch = getCurrentMatch();
		
		currentMatch.setEndTime(match.getEndTime());
	}

	void addKillToPlayer(String playerName, Kill kill) {
		Match match = getCurrentMatch();
		
		Player player = getOrCreatePlayer(playerName, match);
		
		player.addKill(kill);
		
		addDeathToPlayer(kill.getKilledPlayer().getName(), Death.fromKill(player, kill));
	}
	
	private Player getOrCreatePlayer(String playerName, Match match) {
		Player player = null;
		
		if (!match.containsPlayer(playerName)) {
			player = new Player(playerName);
			match.addPlayer(player);
		}
		else {
			player = match.getPlayer(playerName);
		}
		
		return player;
	}

	private Match getCurrentMatch() {
		Match match = matches.peekLast();
		
		if (match == null) {
			throw new IllegalStateException(Messages.getString("GameLog.noCurrentMatch")); //$NON-NLS-1$
		}
		
		return match;
	}
}
