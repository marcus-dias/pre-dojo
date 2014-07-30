package br.com.amil.rankingbuilder.api.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class RankingTest {
	
	@Test
	public void testBuildRankingFromGame() {
		Match match = new Match(1, LocalDateTime.now());
		
		Player player1 = new Player("player1");
		player1.addKill(getKill());
		player1.addKill(getKill());
		
		Player player2 = new Player("player2");
		player2.addKill(getKill());
		
		match.addPlayer(player1);
		match.addPlayer(player2);
		
		Ranking ranking = Ranking.buildRankingFromMatch(match);
		
		assertEquals(ranking.getRankingEntries().get(0).getPlayer(), player1);
		assertEquals(ranking.getRankingEntries().get(1).getPlayer(), player2);
	}

	@Test
	public void testGetWinner() {
		Match match = new Match(1, LocalDateTime.now());
		
		Player player1 = new Player("player1");
		player1.addKill(getKill());
		player1.addKill(getKill());
		
		Player player2 = new Player("player2");
		player2.addKill(getKill());
		
		match.addPlayer(player1);
		match.addPlayer(player2);
		
		Ranking ranking = Ranking.buildRankingFromMatch(match);
		
		assertTrue(ranking.getWinner().isPresent());
		assertEquals(ranking.getWinner().get(), player1);
	}
	
	private Kill getKill() {
		return new Kill(new Player("killer"), new Weapon("Shotgun"), LocalDateTime.now());
	}

}
