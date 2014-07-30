package br.com.amil.rankingbuilder.api.award;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.game.Kill;
import br.com.amil.rankingbuilder.api.game.Player;
import br.com.amil.rankingbuilder.api.game.Weapon;

public class KillsInIntervalAwardCheckerTest {	
	@Test
	public void testCheckNotEnoughKills() {
		Player player = new Player("test");
		
		KillsInIntervalAwardChecker checker = getChecker(5, 1);
		
		player.addKill(getKill(getTime(1, 1)));
		player.addKill(getKill(getTime(1, 2)));
		
		Optional<KillsInIntervalAward> check = checker.check(player);
		
		assertFalse(check.isPresent());
	}
	
	@Test
	public void testCheckNotInTime() {
		Player player = new Player("test");
		
		KillsInIntervalAwardChecker checker = getChecker(5, 1);
		
		player.addKill(getKill(getTime(1, 0)));
		player.addKill(getKill(getTime(1, 2)));
		player.addKill(getKill(getTime(1, 3)));
		player.addKill(getKill(getTime(1, 4)));
		player.addKill(getKill(getTime(3, 0)));
		
		Optional<KillsInIntervalAward> check = checker.check(player);
		
		assertFalse(check.isPresent());
	}
	
	@Test
	public void testCheckPass() {
		Player player = new Player("test");
		
		KillsInIntervalAwardChecker checker = getChecker(5, 1);
		
		player.addKill(getKill(getTime(1, 0)));
		player.addKill(getKill(getTime(1, 2)));
		player.addKill(getKill(getTime(1, 3)));
		player.addKill(getKill(getTime(1, 4)));
		player.addKill(getKill(getTime(1, 30)));
		
		Optional<KillsInIntervalAward> check = checker.check(player);
		
		assertTrue(check.isPresent());
	}

	private KillsInIntervalAwardChecker getChecker(int kills, int minutes) {
		KillsInIntervalAwardChecker checker = new KillsInIntervalAwardChecker(kills, minutes);
		return checker;
	}
	
	private Kill getKill(LocalDateTime time) {
		return new Kill(new Player("killed"), new Weapon("Walther PPK"), time);
	}
	
	private LocalDateTime getTime(int minute, int second) {
		return LocalDateTime.of(2014, 1, 1, 1, minute, second);
	}

}
