package br.com.amil.rankingbuilder.api;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.AwardCollector;
import br.com.amil.rankingbuilder.api.award.Award;
import br.com.amil.rankingbuilder.api.award.AwardChecker;
import br.com.amil.rankingbuilder.api.game.Death;
import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Match;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;

public class AwardCollectorTest {

	@Test
	public void testCollectAwards() {
		AwardChecker<Award> hasKillsChecker = buildHasKillChecker();
				
		AwardChecker<Award> hasDeathChecker = buildHasDeathChecker();
		
		Player killerPlayer = new Player("killer");
		killerPlayer.addKill(getKill());
		
		Player killedPlayer = new Player("killed");
		killedPlayer.addDeath(Death.fromKill(killerPlayer, getKill()));
		
		Match match = new Match(1, LocalDateTime.now());
		match.addPlayer(killerPlayer);
		match.addPlayer(killedPlayer);
		
		AwardCollector hasKillsCollector = new AwardCollector(hasKillsChecker, hasDeathChecker);
		List<Award> awards = hasKillsCollector.collectAwards(match);
		
		assertEquals(awards.size(), 2);
		assertEquals(awards.get(0).getPlayer(), killerPlayer);
		assertEquals(awards.get(1).getPlayer(), killedPlayer);
	}

	private Kill getKill() {
		return new Kill(new Player("killed"), new Weapon("UZI"), LocalDateTime.now());
	}

	private AwardChecker<Award> buildHasKillChecker() {
		AwardChecker<Award> playerHasKillsChecker = new AwardChecker<Award>() {
			@Override
			public Optional<Award> check(final Player player) {
				Award award = new Award(player) {
					@Override
					protected String getMessage() {
						return "matou";
					}
				};
				
				if (player.getKillCount() > 0) {
					return Optional.of(award);
				}
				else {
					return Optional.empty();
				}
			}
		};
		
		return playerHasKillsChecker;
	}
	
	private AwardChecker<Award> buildHasDeathChecker() {
		AwardChecker<Award> playerHasDeathsChecker = new AwardChecker<Award>() {
			@Override
			public Optional<Award> check(final Player player) {
				Award award = new Award(player) {
					@Override
					protected String getMessage() {
						return "morreu";
					}
				};
				
				if (player.getDeathCount() > 0) {
					return Optional.of(award);
				}
				else {
					return Optional.empty();
				}
			}
		};
		
		return playerHasDeathsChecker;
	}

}
