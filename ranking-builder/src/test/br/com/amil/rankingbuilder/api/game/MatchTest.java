package br.com.amil.rankingbuilder.api.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

public class MatchTest {

	@Test
	public void testConstructor() {
		LocalDateTime time = LocalDateTime.now();
		long id = 12345;
		
		Match match = new Match(id, time);
		
		assertEquals(match.getId(), id);
		assertEquals(match.getStartTime(), time);
	}

	@Test
	public void testGetPlayers() {
		Player player = new Player("player1");
		Player player2 = new Player("player2");
		
		Match match = new Match(1, LocalDateTime.now());
		
		match.addPlayer(player);
		match.addPlayer(player2);
		
		assertSame(match.getPlayer("player1"), player);
		assertSame(match.getPlayer("player2"), player2);
	}

	@Test
	public void testGetTopStreak() {
		Player player = new Player("player1");
		Player player2 = new Player("player2");
		
		player.addKill(getKill(getTime(1, 0)));
		player.addKill(getKill(getTime(1, 1)));
		player.addDeath(getDeath(getTime(1, 2)));
		player.addKill(getKill(getTime(1, 3)));
		
		player2.addKill(getKill(getTime(1, 0)));
		player2.addKill(getKill(getTime(1, 1)));
		player2.addKill(getKill(getTime(1, 2)));
		
		Match match = new Match(1, LocalDateTime.now());
		
		match.addPlayer(player);
		match.addPlayer(player2);
		
		Optional<Streak> topStreak = match.getTopStreak();
		
		assertTrue(topStreak.isPresent());
		assertSame(topStreak.get().getPlayer(), player2);
	}

	@Test
	public void testGetEndTime() {
		LocalDateTime startTime = LocalDateTime.now();
		
		Match match = new Match(1, startTime);
		
		LocalDateTime endTime = startTime.plusMinutes(1);
		
		match.setEndTime(endTime);
		
		assertEquals(endTime, match.getEndTime());
	}

	@Test
	public void testContainsPlayer() {
		String name = "test";
		Player player = new Player(name);
		Player copy = new Player(name);
		
		Match match = new Match(1, LocalDateTime.now());
		match.addPlayer(player);
		
		assertTrue(match.containsPlayer(copy.getName()));		
	}
	
	private Kill getKill(LocalDateTime time) {
		return new Kill(new Player("killer"), new Weapon(".44 Magnum"), time);
	}
	
	private Death getDeath(LocalDateTime time) {
		return new Death(new Player("killer"), new Weapon("FAL"), time);
	}
	
	private LocalDateTime getTime(int minute, int second) {
		return LocalDateTime.of(2014, 1, 1, 1, minute, second);
	}
}
