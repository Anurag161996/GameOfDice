package com.greatlearning.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.greatlearning.dice.constants.PlayerStatus;
import com.greatlearning.dice.model.Player;
import com.greatlearning.dice.service.DiceRule;
import com.greatlearning.dice.service.impl.DiceRuleImpl;

public class DiceRuleImplTest {

	Player setupPlayer(String playerId) {
		Player player = new Player();
		player.setId(playerId);
		player.setTotalPoint(0);
		player.setStatus(PlayerStatus.CAN_PLAY);
		return player;
	}
	@Test
	public void setUpGameSuceess() {
		DiceRule diceRule = new DiceRuleImpl();
		Player player = setupPlayer("Player-1");
		List<Player> savedPlayerList = diceRule.setUpGame(1);
		if(savedPlayerList.isEmpty())
			Assert.assertFalse(true);
		Assert.assertTrue("ID are Not Same",player.getId().equalsIgnoreCase(savedPlayerList.get(0).getId()));
		Assert.assertSame(player.getTotalPoint(), savedPlayerList.get(0).getTotalPoint());
		Assert.assertSame(player.getStatus(), savedPlayerList.get(0).getStatus());
	}
	
	@Test
	public void playerWinsSuccess() {
		DiceRule diceRule = new DiceRuleImpl();
		Player player1 = setupPlayer("Player-1");
		Player player2 = setupPlayer("Player-2");
		List<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		Integer playerInGame = diceRule.playerWins(playerList, 1, 2);
		if(playerInGame != 1)
			Assert.assertFalse(true);
		Assert.assertTrue("ID are Not Same",playerList.get(0).getId().equalsIgnoreCase("Player-1"));
		Assert.assertTrue("ID are Not Same",playerList.get(1).getId().equalsIgnoreCase("Player-2"));
		Assert.assertSame(playerList.get(0).getStatus(), PlayerStatus.CAN_PLAY);
		Assert.assertSame(playerList.get(1).getStatus(), PlayerStatus.WON);
		Assert.assertSame(playerList.get(1).getRank(), 1);
	}
	
	@Test
	public void playerWinsFailure() {
		DiceRule diceRule = new DiceRuleImpl();
		Player player1 = setupPlayer("Player-1");
		Player player2 = setupPlayer("Player-2");
		List<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);
		Integer playerInGame = diceRule.playerWins(playerList, 1, 2);
		if(playerInGame != 1)
			Assert.assertFalse(true);
		Assert.assertTrue("ID are Not Same",playerList.get(0).getId().equalsIgnoreCase("Player-1"));
		Assert.assertTrue("ID are Not Same",playerList.get(1).getId().equalsIgnoreCase("Player-2"));
		Assert.assertNotSame(playerList.get(0).getStatus(), PlayerStatus.WON);
		Assert.assertNotSame(playerList.get(1).getStatus(), PlayerStatus.CAN_PLAY);
		Assert.assertNotSame(playerList.get(0).getRank(), 1);
	}
	
}
