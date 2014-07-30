package br.com.amil.rankingbuilder.api;

/**
 * Representa exce��es do aplicativo.
 */
public class GameLogException extends Exception {

	private static final long serialVersionUID = -2296454762498905333L;

	/**
	 * Cria a exce��o com a mensagem especificada.
	 *
	 * @param message Mensagem da exce��o
	 */
	public GameLogException(String message) {
		super(message);
	}

	/**
	 * Cria a exce��o com a mensagem especificada e a exce��o de origem.
	 *
	 * @param message Mensagem da exce��o
	 * @param cause Exce��o de origem
	 */
	public GameLogException(String message, Throwable cause) {
		super(message, cause);
	}

}
