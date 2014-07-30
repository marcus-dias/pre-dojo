package br.com.amil.rankingbuilder.api.log.reader;

import java.io.IOException;
import java.util.Properties;

/**
 * Representa os padrões contidos no Log.
 */
public enum LogPatterns {
	
	/** Tempo. */
	TIME("log.pattern.time"),
	
	/** Nova Partida. */
	NEW_MATCH("log.pattern.newmatch"),
	
	/** Morte. */
	KILL("log.pattern.kill"),
	
	/** Morte pelo mundo. */
	WORLD_KILL("log.pattern.worldkill"),
	
	/** Fim de Partida. */
	END_MATCH("log.pattern.endmatch");
	
	private static final String COMMON = "log.pattern.common"; 
	
	private static Properties properties = new Properties();
	
	static {
		try {
			 properties.load(LogPatterns.class.getResourceAsStream("/log-pattern.properties"));
		} catch (IOException e) {
			System.out.println(String.format("Erro ao ler o arquivo de patterns: %s", e.getMessage()));
		}
	}
	
	private final String pattern;

	private LogPatterns(String pattern) {
		this.pattern = pattern;
	}
	
	/**
	 * Retorna o padrão de log.
	 *
	 * @return O padrão de log
	 */
	public String getPattern() {
		if (this == TIME) {
			return properties.getProperty(pattern);
		}
		
		return properties.getProperty(COMMON).concat(properties.getProperty(pattern));
	}
}
