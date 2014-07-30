package br.com.amil.rankingbuilder.api.award;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.game.Death;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;

public class NoDeathWinAwardCheckerTest {
	
	@Test
	public void testCheckPlayerHasDeath() {
		Player player = new Player("test");
		player.addDeath(getDeath());
		
		NoDeathWinAwardChecker checker = getChecker(player);
		Optional<NoDeathWinAward> check = checker.check(player);
		
		assertFalse(check.isPresent());
	}
	
	@Test
	public void testCheckPlayerNotWinner() {
		Player player = new Player("test");
		
		NoDeathWinAwardChecker checker = getChecker(new Player("winner"));
		Optional<NoDeathWinAward> check = checker.check(player);
		
		assertFalse(check.isPresent());
	}
	
	@Test
	public void testCheckPass() {
		Player player = new Player("winner");
		
		NoDeathWinAwardChecker checker = getChecker(player);
		Optional<NoDeathWinAward> check = checker.check(player);
		
		assertTrue(check.isPresent());
	}

	private NoDeathWinAwardChecker getChecker(Player player) {
		NoDeathWinAwardChecker checker = new NoDeathWinAwardChecker(player);
		return checker;
	}
	
	private Death getDeath() {
		return new Death(new Player("killer"), new Weapon("AR-15"), LocalDateTime.now());
	}
}
