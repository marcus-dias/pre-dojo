package br.com.amil.rankingbuilder.api.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

public class PlayerTest {
	@Test
	public void testGetName() {
		String name = "test";
		Player player = new Player(name);
		
		assertEquals(name, player.getName());
	}

	@Test
	public void testGetKills() {
		Player player = new Player("test");
		
		Kill kill1 = getKill(getTime(1, 0));
		Kill kill2 = getKill(getTime(1, 1));
		
		player.addKill(kill1);
		player.addKill(kill2);
		
		assertSame(player.getKills().get(0), kill1);
		assertSame(player.getKills().get(1), kill2);
	}

	@Test
	public void testGetKillCount() {
		Player player = new Player("test");
		
		player.addKill(getKill(getTime(1, 0)));
		player.addKill(getKill(getTime(1, 1)));
		player.addKill(getKill(getTime(1, 2)));
		
		assertEquals(player.getKillCount(), 3);
	}

	@Test
	public void testGetDeathCount() {
		Player player = new Player("test");
		
		player.addDeath(getDeath(getTime(1, 0)));
		player.addDeath(getDeath(getTime(1, 1)));
		player.addDeath(getDeath(getTime(1, 2)));
		
		assertEquals(player.getDeathCount(), 3);
	}

	@Test
	public void testGetDeaths() {
		Player player = new Player("test");
		
		Death death1 = getDeath(getTime(1, 0));
		Death death2 = getDeath(getTime(1, 1));
		
		player.addDeath(death1);
		player.addDeath(death2);
		
		assertSame(player.getDeaths().get(0), death1);
		assertSame(player.getDeaths().get(1), death2);
	}

	@Test
	public void testGetMostUsedWeapon() {
		Player player = new Player("test");
		
		Weapon ak47 = new Weapon("AK47");
		Weapon fal = new Weapon("FAL");
		
		player.addKill(getKill(ak47, getTime(1,	0)));
		player.addKill(getKill(ak47, getTime(1, 1)));
		player.addKill(getKill(fal, getTime(1, 2)));
		
		Optional<Weapon> weapon = player.getMostUsedWeapon();
		
		assertTrue(weapon.isPresent());
		assertEquals(weapon.get(), ak47);
	}

	@Test
	public void testGetTopStreak() {
		Player player = new Player("player1");
		
		player.addKill(getKill(getTime(1, 0)));
		player.addKill(getKill(getTime(1, 1)));
		player.addKill(getKill(getTime(1, 2)));
		player.addDeath(getDeath(getTime(1, 3)));
		player.addKill(getKill(getTime(1, 4)));
		player.addKill(getKill(getTime(1, 5)));
		player.addKill(getKill(getTime(1, 6)));
		player.addKill(getKill(getTime(1, 7)));
		player.addDeath(getDeath(getTime(1, 7)));
		player.addKill(getKill(getTime(1, 8)));
		
		Optional<Streak> streak = player.getTopStreak();
		
		assertTrue(streak.isPresent());
		assertEquals(streak.get().getKillsInARowCount(), 4);
	}
	
	private Kill getKill(LocalDateTime time) {
		return new Kill(new Player("killer"), new Weapon("BFG 9000"), time);
	}
	
	private Kill getKill(Weapon weapon, LocalDateTime time) {
		return new Kill(new Player("killer"), weapon, time);
	}
	
	private Death getDeath(LocalDateTime time) {
		return new Death(new Player("killer"), new Weapon("Chainsaw"), time);
	}
	
	private LocalDateTime getTime(int minute, int second) {
		return LocalDateTime.of(2014, 1, 1, 1, minute, second);
	}
}
