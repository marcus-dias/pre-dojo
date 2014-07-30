package br.com.amil.rankingbuilder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.amil.rankingbuilder.api.AwardCollectorTest;
import br.com.amil.rankingbuilder.api.GameLogBuilderTest;
import br.com.amil.rankingbuilder.api.GameLogTest;
import br.com.amil.rankingbuilder.api.award.AwardTest;
import br.com.amil.rankingbuilder.api.award.KillsInIntervalAwardCheckerTest;
import br.com.amil.rankingbuilder.api.award.NoDeathWinAwardCheckerTest;
import br.com.amil.rankingbuilder.api.game.DeathTest;
import br.com.amil.rankingbuilder.api.game.MatchTest;
import br.com.amil.rankingbuilder.api.game.PlayerTest;
import br.com.amil.rankingbuilder.api.game.RankingTest;
import br.com.amil.rankingbuilder.api.log.reader.DefaultLogEntryConverterTest;
import br.com.amil.rankingbuilder.api.log.reader.EndMatchLogEntryConverterTest;
import br.com.amil.rankingbuilder.api.log.reader.KillLogEntryConverterTest;
import br.com.amil.rankingbuilder.api.log.reader.NewMatchLogEntryConverterTest;
import br.com.amil.rankingbuilder.api.log.reader.TextLogReaderTest;
import br.com.amil.rankingbuilder.api.log.reader.WorldKillLogEntryConverterTest;

@RunWith(Suite.class)
@SuiteClasses({AwardTest.class, KillsInIntervalAwardCheckerTest.class,
	NoDeathWinAwardCheckerTest.class, DeathTest.class, MatchTest.class,
	PlayerTest.class, RankingTest.class, DefaultLogEntryConverterTest.class,
	EndMatchLogEntryConverterTest.class, KillLogEntryConverterTest.class,
	NewMatchLogEntryConverterTest.class, WorldKillLogEntryConverterTest.class,
	TextLogReaderTest.class, AwardCollectorTest.class, GameLogTest.class,
	GameLogBuilderTest.class})
public class TestSuite {

}
