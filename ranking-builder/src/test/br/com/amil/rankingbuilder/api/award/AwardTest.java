package br.com.amil.rankingbuilder.api.award;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.amil.rankingbuilder.api.game.Player;

public class AwardTest {

	@Test
	public void testAwardMessage() {
		final String message = "teste";
		
		Player player = new Player("Player");
		
		Award award = new Award(player) {
			@Override
			protected String getMessage() {
				return message;
			}
		};
		
		String awardMessage = award.awardMessage();
		
		assertTrue(awardMessage.endsWith(message));
		assertTrue(awardMessage.contains(player.getName()));
	}
}
