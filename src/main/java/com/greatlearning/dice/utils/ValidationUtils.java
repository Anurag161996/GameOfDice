package com.greatlearning.dice.utils;

import com.google.common.base.Optional;
import com.greatlearning.dice.exceptions.IncorrectInputException;

public class ValidationUtils {

	public static void validInput(String... args) throws IncorrectInputException {
		try {
			if (!Optional.fromNullable(args).isPresent() || args.length != 2)
				throw new IncorrectInputException("N and M are invalid");
			if (Integer.parseInt(args[0]) < 2)
				throw new IncorrectInputException("N should be at Least 2");
			if (Integer.parseInt(args[1]) < 1)
				throw new IncorrectInputException("M should be at Least 1");
		} catch (NumberFormatException ex) {
			throw new IncorrectInputException("N and M are Not Integer");
		}
	}
}
