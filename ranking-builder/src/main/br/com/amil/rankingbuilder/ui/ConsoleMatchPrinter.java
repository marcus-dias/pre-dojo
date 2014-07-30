package br.com.amil.rankingbuilder.ui;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;

import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.log.reader.LogPatterns;
import br.com.amil.rankingbuilder.api.util.Messages;

/**
 * Imprime informações de um {@link Match}.
 */
public class ConsoleMatchPrinter implements ConsolePrinter {
	
	private final Match match;
	
	/**
	 * Cria o printer.
	 *
	 * @param match O {@link Match}
	 */
	public ConsoleMatchPrinter(Match match) {
		super();
		this.match = match;
	}

	/**
	 * @see br.com.amil.rankingbuilder.ui.ConsolePrinter#printTo(java.io.PrintStream)
	 */
	@Override
	public void printTo(PrintStream stream) {
		stream.println(String.format(Messages.getString("ConsoleMatchPrinter.matchInfo"),  //$NON-NLS-1$
				match.getId(), 
				match.getStartTime().format(DateTimeFormatter.ofPattern(LogPatterns.TIME.getPattern())), 
				match.getEndTime().format(DateTimeFormatter.ofPattern(LogPatterns.TIME.getPattern()))));
	}
}
