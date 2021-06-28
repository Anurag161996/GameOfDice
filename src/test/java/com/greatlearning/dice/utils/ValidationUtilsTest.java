package com.greatlearning.dice.utils;

import org.junit.Test;

import com.greatlearning.dice.exceptions.IncorrectInputException;

public class ValidationUtilsTest {

	
	@Test(expected = IncorrectInputException.class)
	public void whenExceptionThrown_validInputWithNoArgs() throws IncorrectInputException {
		ValidationUtils.validInput();
	}
	
	@Test(expected = IncorrectInputException.class)
	public void whenExceptionThrown_validInputWithOneArgs() throws IncorrectInputException {
		ValidationUtils.validInput("1");
	}
	
	@Test(expected = IncorrectInputException.class)
	public void whenExceptionThrown_validInputWithWrongNumberOfPlayer() throws IncorrectInputException {
		ValidationUtils.validInput("1","2");
	}
	
	@Test(expected = IncorrectInputException.class)
	public void whenExceptionThrown_validInputWithWrongPoints() throws IncorrectInputException {
		ValidationUtils.validInput("2","0");
	}
	
	@Test
	public void validInputSuccess() throws IncorrectInputException {
		ValidationUtils.validInput("2","1");
	}
}
