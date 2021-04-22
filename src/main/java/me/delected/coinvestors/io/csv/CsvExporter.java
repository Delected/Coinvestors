package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.io.FileAccessor;

import java.io.IOException;

public class CsvExporter extends CsvAccessor {

	public CsvExporter(String filePath, String delimiter) throws IOException, IllegalArgumentException {
		super(filePath, delimiter);
	}
}
