package me.delected.coinvestors.exceptions;

import java.io.File;

public class IncorrectFileTypeException extends IllegalArgumentException {

	private static final String DEFAULT_MESSAGE = "The file type provided is not valid! ";

	public IncorrectFileTypeException() {
		super(DEFAULT_MESSAGE);
	}

	public IncorrectFileTypeException(final String message) {
		super(message);
	}

	public IncorrectFileTypeException(File file, String expectedType) {
		this(DEFAULT_MESSAGE + "Expected " + file.getPath() + " to have the type " + expectedType);
	}

}
