package com.greatlearning.service;

import org.junit.Assert;
import org.junit.Test;

import com.greatlearning.dice.service.Dice;
import com.greatlearning.dice.service.impl.DiceImpl;

public class DiceImplTest {
	@Test
	public void rollDiceSuccess() {
		Dice dice = new DiceImpl();
		int random = dice.rollDice();
		Assert.assertTrue("Dice Number is not less than max value", 6 >= random);
		Assert.assertTrue("Dice Number is not more than min value", 1 <= random);
	}

	@Test
	public void rollDiceFailed() {
		Dice dice = new DiceImpl();
		int random = dice.rollDice();
		Assert.assertFalse("Dice Number is not less than max value", 6 < random);
		Assert.assertFalse("Dice Number is not more than min value", 1 > random);
	}

}
