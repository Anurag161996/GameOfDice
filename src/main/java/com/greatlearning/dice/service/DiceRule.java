package com.greatlearning.dice.service;

import java.util.List;

import com.greatlearning.dice.model.Player;

public interface DiceRule {
	void displayRanks(List<Player> playerList);

	List<Player> setUpGame(Integer noOfPlayer);

	Integer playerWins(List<Player> playerList, Integer idx, Integer playersInGame);

	void startGame(List<Player> playerList, Integer noOfPlayer, Integer points);
}
