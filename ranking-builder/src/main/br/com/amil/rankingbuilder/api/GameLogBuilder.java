package br.com.amil.rankingbuilder.api;

import java.util.List;

import br.com.amil.rankingbuilder.api.game.Death;
import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;
import br.com.amil.rankingbuilder.api.log.entry.EndMatchLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.KillLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.LogEntry;
import br.com.amil.rankingbuilder.api.log.entry.NewMatchLogEntry;
import br.com.amil.rankingbuilder.api.log.entry.WorldKillLogEntry;
import br.com.amil.rankingbuilder.api.log.reader.LogReader;
import br.com.amil.rankingbuilder.api.log.source.LogSource;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Construtor de um {@link GameLog}.
 *
 * @param <E> Tipo de dado da fonte de Log
 * @param <T> Fonte de Log
 */
public class GameLogBuilder<E, T extends LogSource<E>> {
	
	private final T logSource;
	private LogReader<E> logReader;
		
	/**
	 * Cria o construtor.
	 *
	 * @param logSource A fonte de Log
	 */
	public GameLogBuilder(T logSource) {
		this.logSource = logSource;
	}
	
	/**
	 * Atribui o leitor de Log.
	 *
	 * @param logReader O leitor de log
	 * @return Este construtor
	 */
	public GameLogBuilder<E, T> withReader(LogReader<E> logReader) {
		this.logReader = logReader;
		
		return this;
	}

	/**
	 * Constrói o {@link GameLog}.
	 *
	 * @return O {@link GameLog}
	 * @throws GameLogException Se ocorrer erro durante a construção
	 */
	public GameLog build() throws GameLogException {
		if (logReader == null) {
			throw new IllegalStateException(Messages.getString("GameLogBuilder.readerNotSet")); //$NON-NLS-1$
		}
		
		GameLog gameLog = new GameLog();
		
		List<LogEntry> entries = logReader.readEntriesFrom(logSource);
		
		for (LogEntry entry : entries) {
			if (entry instanceof NewMatchLogEntry) {
				NewMatchLogEntry logEntry = (NewMatchLogEntry) entry;
				gameLog.newMatch(getMatchFrom(logEntry));
			}
			if (entry instanceof KillLogEntry) {
				KillLogEntry logEntry = (KillLogEntry) entry;
				gameLog.addKillToPlayer(logEntry.getKillerPlayer(), getKillFrom(logEntry));
			}
			if (entry instanceof WorldKillLogEntry) {
				WorldKillLogEntry logEntry = (WorldKillLogEntry) entry;
				gameLog.addDeathToPlayer(logEntry.getKilledPlayer(), getWorldDeathFrom(logEntry));
			}
			if (entry instanceof EndMatchLogEntry) {
				EndMatchLogEntry logEntry = (EndMatchLogEntry) entry;
				gameLog.endMatch(getEndMatchFrom(logEntry));
			}
		}
		
		return gameLog;
	}

	private Match getEndMatchFrom(EndMatchLogEntry entry) {
		return new Match(entry.getMatchId(), entry.getLogTime());
	}

	private Death getWorldDeathFrom(WorldKillLogEntry entry) {
		Weapon weapon = new Weapon(entry.getKillReason());
		
		Death death = new Death(Player.WORLD, weapon, entry.getLogTime());
		
		return death;
	}

	private Kill getKillFrom(KillLogEntry entry) {
		Player killedPlayer = new Player(entry.getKilledPlayer());
		Weapon weapon = new Weapon(entry.getWeaponUsed());
		
		return new Kill(killedPlayer, weapon, entry.getLogTime());
	}

	private Match getMatchFrom(NewMatchLogEntry entry) {
		return new Match(entry.getMatchId(), entry.getLogTime());
	}
}
