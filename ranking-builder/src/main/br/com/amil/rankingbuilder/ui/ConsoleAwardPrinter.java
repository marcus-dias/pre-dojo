package br.com.amil.rankingbuilder.ui;

import java.io.PrintStream;
import java.util.List;

import br.com.amil.rankingbuilder.api.award.Award;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Imprime a lista de {@link Award} de um {@link Match}
 */
public class ConsoleAwardPrinter implements ConsolePrinter {
	
	private final List<Award> awards;
	
	/**
	 * Cria o printer.
	 *
	 * @param awards lista de {@link Award}
	 */
	public ConsoleAwardPrinter(List<Award> awards) {
		super();
		this.awards = awards;
	}

	/**
	 * @see br.com.amil.rankingbuilder.ui.ConsolePrinter#printTo(java.io.PrintStream)
	 */
	@Override
	public void printTo(PrintStream stream) {
		awards.stream()
			.forEach(a -> stream.println(String.format(Messages.getString("ConsoleAwardPrinter.awardPrefix"), a.awardMessage()))); //$NON-NLS-1$
	}

}
