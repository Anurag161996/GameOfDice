package com.greatlearning.dice.utils;

public class PrintMessage {

	public void print(String msg) {
		System.out.println(msg);
	}

	public <T> void print(String msg, T... args) {
		System.out.printf(msg, args);
	}
}
