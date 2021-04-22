package me.delected.coinvestors.io.csv;

import java.io.IOException;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;
import me.delected.coinvestors.io.FileAccessor;

public abstract class CsvAccessor extends FileAccessor {
	protected final String delimiter;

	protected CsvAccessor(final String path, final String delimiter) throws IOException, IncorrectFileTypeException {
		super(path, ".csv");
		this.delimiter = delimiter;
	}

}
