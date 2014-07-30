package br.com.amil.rankingbuilder.api.log.entry;

import java.time.LocalDateTime;

/**
 * Representa uma entrada de Log.
 */
public class LogEntry {
	
	private final LocalDateTime logTime;
	
	/**
	 * Cria uma entrada de Log.
	 *
	 * @param logTime O tempo de Log
	 */
	public LogEntry(LocalDateTime logTime) {
		super();
		this.logTime = logTime;
	}

	/**
	 * Recupera o tempo do Log.
	 *
	 * @return O tempo do Log
	 */
	public LocalDateTime getLogTime() {
		
		return logTime;
	}
}
