package br.com.amil.rankingbuilder.ui;

import java.io.PrintStream;
import java.util.Optional;

import br.com.amil.rankingbuilder.api.game.Ranking;
import br.com.amil.rankingbuilder.api.game.Streak;
import br.com.amil.rankingbuilder.api.game.Weapon;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Imprime o {@link Ranking} de um {@link Match}
 */
public class ConsoleRankingPrinter implements ConsolePrinter {
	
	private final Ranking ranking;
	private int index = 0;
	
	/**
	 * Cria o printer.
	 *
	 * @param ranking O {@link Printer}
	 */
	public ConsoleRankingPrinter(Ranking ranking) {
		super();
		this.ranking = ranking;
	}

	/**
	 * @see br.com.amil.rankingbuilder.ui.ConsolePrinter#printTo(java.io.PrintStream)
	 */
	@Override
	public void printTo(PrintStream stream) {
		index = 0;
		
		stream.println(Messages.getString("ConsoleRankingPrinter.rankingTitle")); //$NON-NLS-1$
		stream.println();
		
		stream.println(String.format(Messages.getString("ConsoleRankingPrinter.rankingHeader"), Messages.getString("ConsoleRankingPrinter.position"), Messages.getString("ConsoleRankingPrinter.player"), Messages.getString("ConsoleRankingPrinter.deaths"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		
		ranking.getRankingEntries().stream()
			.forEach(r -> stream.println(String.format(Messages.getString("ConsoleRankingPrinter.rankingBody"), getPosition(), r.getPlayer().getName(), r.getKillCount()))); //$NON-NLS-1$
		
		stream.println();
		
		Optional<Weapon> weapon = ranking.getWinner().flatMap(p -> p.getMostUsedWeapon());
		
		if (weapon.isPresent()) {
			stream.println(String.format(Messages.getString("ConsoleRankingPrinter.mostUsedWeapon"), weapon.get().getName())); //$NON-NLS-1$
		}
		
		if (ranking.getMatch().getTopStreak().isPresent()) {
			Streak streak = ranking.getMatch().getTopStreak().get();
			
			stream.println(String.format(Messages.getString("ConsoleRankingPrinter.topStreak"), streak.getKillsInARow().size(), streak.getPlayer().getName())); //$NON-NLS-1$
		}
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	private int getPosition() {
		return ++index;
	}
}


