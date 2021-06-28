package com.greatlearning.dice;

import com.greatlearning.dice.service.GameOfDice;
import com.greatlearning.dice.service.impl.GameOfDiceServiceImpl;

public class Main {
	  public static void main(String ...args) {
		  GameOfDice gameOfDice = new GameOfDiceServiceImpl();
		  gameOfDice.startGame(args);
	    }
}
