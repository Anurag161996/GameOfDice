package com.greatlearning.dice.service.impl;

import java.util.List;

import com.greatlearning.dice.model.Player;
import com.greatlearning.dice.service.DiceRule;
import com.greatlearning.dice.service.GameOfDice;
import com.greatlearning.dice.utils.ValidationUtils;

public class GameOfDiceServiceImpl implements GameOfDice {

	DiceRule diceRule;

	public GameOfDiceServiceImpl() {
		diceRule = new DiceRuleImpl();
	}

	@Override
	public void startGame(String... args) {
		try {
			ValidationUtils.validInput(args);
			int N = Integer.parseInt(args[0]);
			int M = Integer.parseInt(args[1]);
			List<Player> playerList = diceRule.setUpGame(N);
			diceRule.startGame(playerList, N, M);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
