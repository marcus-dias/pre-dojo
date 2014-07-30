package br.com.amil.rankingbuilder.ui;

import java.io.PrintStream;


/**
 * Representa um objeto capaz de imprimir no console da aplica��o.
 */
public interface ConsolePrinter {
	
	/**
	 * Imprime no console da aplica��o.
	 *
	 * @param stream Sa�da do console
	 */
	void printTo(PrintStream stream);
}
