package com.greatlearning.dice.service.impl;

import java.util.Random;

import com.greatlearning.dice.constants.DiceConstants;
import com.greatlearning.dice.service.Dice;
import com.greatlearning.dice.utils.PrintMessage;

public class DiceImpl implements Dice {

	PrintMessage printMessage;

	public DiceImpl() {
		printMessage = new PrintMessage();
	}

	@Override
	public int rollDice() {
		Random random = new Random();
		Integer diceNumber = random.nextInt(DiceConstants.MAX_VALUE - DiceConstants.MIN_VALUE + 1)
				+ DiceConstants.MIN_VALUE;
		printMessage.print("Dice rolled is: " + diceNumber);
		return diceNumber;
	}

}
