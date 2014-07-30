package br.com.amil.rankingbuilder.api.game;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

public class DeathTest {
	
	@Test
	public void testFromKill() {
		Player killerPlayer = new Player("killer");
		Weapon weaponUsed = new Weapon("Desert Eagle");
		
		Kill kill = new Kill(new Player("killed"), weaponUsed, LocalDateTime.now());
		
		Death death = Death.fromKill(killerPlayer, kill);
		
		assertEquals(death.getKilledBy(), killerPlayer);
		assertEquals(death.getWeaponUsed(), weaponUsed);
	}

}
