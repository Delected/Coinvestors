package me.delected.coinvestors.exceptions;

public class ContactTheDevsException extends RuntimeException {
	public ContactTheDevsException() {
		super("Ooops. Something went terribly wrong. Please report under https://github.com/Delected/Coinvestors/issues");
	}
}
