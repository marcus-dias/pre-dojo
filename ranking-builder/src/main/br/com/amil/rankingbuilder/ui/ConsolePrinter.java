package br.com.amil.rankingbuilder.ui;

import java.io.PrintStream;


/**
 * Representa um objeto capaz de imprimir no console da aplicação.
 */
public interface ConsolePrinter {
	
	/**
	 * Imprime no console da aplicação.
	 *
	 * @param stream Saída do console
	 */
	void printTo(PrintStream stream);
}
