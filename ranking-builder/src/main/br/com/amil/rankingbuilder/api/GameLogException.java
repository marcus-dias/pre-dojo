package br.com.amil.rankingbuilder.api;

/**
 * Representa exceções do aplicativo.
 */
public class GameLogException extends Exception {

	private static final long serialVersionUID = -2296454762498905333L;

	/**
	 * Cria a exceção com a mensagem especificada.
	 *
	 * @param message Mensagem da exceção
	 */
	public GameLogException(String message) {
		super(message);
	}

	/**
	 * Cria a exceção com a mensagem especificada e a exceção de origem.
	 *
	 * @param message Mensagem da exceção
	 * @param cause Exceção de origem
	 */
	public GameLogException(String message, Throwable cause) {
		super(message, cause);
	}

}
