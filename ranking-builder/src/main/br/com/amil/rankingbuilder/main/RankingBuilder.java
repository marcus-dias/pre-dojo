package br.com.amil.rankingbuilder.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import br.com.amil.rankingbuilder.api.AwardCollector;
import br.com.amil.rankingbuilder.api.GameLog;
import br.com.amil.rankingbuilder.api.GameLogBuilder;
import br.com.amil.rankingbuilder.api.GameLogException;
import br.com.amil.rankingbuilder.api.award.KillsInIntervalAwardChecker;
import br.com.amil.rankingbuilder.api.award.NoDeathWinAwardChecker;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Ranking;
import br.com.amil.rankingbuilder.api.log.reader.TextLogReader;
import br.com.amil.rankingbuilder.api.log.source.FileLogSource;
import br.com.amil.rankingbuilder.api.util.Messages;
import br.com.amil.rankingbuilder.ui.ConsoleAwardPrinter;
import br.com.amil.rankingbuilder.ui.ConsoleMatchPrinter;
import br.com.amil.rankingbuilder.ui.ConsoleRankingPrinter;

/**
 * Classe principal de entrada da aplicação.
 */
public class RankingBuilder {
	
	/**
	 * Método de entrada.
	 *
	 * @param args Caminho do arquivo de log
	 * @throws GameLogException Se ocorrer erro ao processar o log
	 * @throws IOException Se ocorrer erro ao acessar o arquivo de log
	 */
	public static void main(String[] args) throws GameLogException, IOException {
		if (args.length == 0) {
			System.out.println(Messages.getString("RankingBuilder.useMessage")); //$NON-NLS-1$
			System.exit(1);
		}
		
		String filePathName = args[0];
		
		if (!Files.exists(Paths.get(filePathName))) {
			System.out.println(String.format(Messages.getString("RankingBuilder.logFileNotFound"), filePathName)); //$NON-NLS-1$
			System.exit(1);
		}
		
		Path path = Paths.get(filePathName);
		
		Properties patterns = new Properties();
		patterns.load(RankingBuilder.class.getResourceAsStream("/log-pattern.properties")); //$NON-NLS-1$
		
		GameLog gameLog =
				new GameLogBuilder<>(new FileLogSource(path))
				.withReader(new TextLogReader())
				.build();
		
		gameLog.getMatches().stream().forEach(RankingBuilder::print);
	}
	
	private static void print(Match match) {
		System.out.println();
		
		System.out.println(Messages.getString("RankingBuilder.separator")); //$NON-NLS-1$
		
		new ConsoleMatchPrinter(match).printTo(System.out);
		
		System.out.println();
		
		Ranking ranking = Ranking.buildRankingFromMatch(match);
		
		new ConsoleRankingPrinter(ranking).printTo(System.out);
		
		System.out.println();
		
		KillsInIntervalAwardChecker killChecker = new KillsInIntervalAwardChecker(5, 1);
		AwardCollector collector = new AwardCollector(killChecker);
		
		if (ranking.getWinner().isPresent()) {
			NoDeathWinAwardChecker noDeathChecker = new NoDeathWinAwardChecker(ranking.getWinner().get());
			collector.addChecker(noDeathChecker);
			
			new ConsoleAwardPrinter(collector.collectAwards(match)).printTo(System.out);
		}
	}
}
