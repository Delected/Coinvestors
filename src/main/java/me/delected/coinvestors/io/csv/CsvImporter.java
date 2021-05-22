package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;
import me.delected.coinvestors.io.FileAccessor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CsvImporter extends CsvAccessor {

	public CsvImporter(String filePath, String delimiter) throws IOException, IncorrectFileTypeException {
		super(filePath, delimiter);
	}

	public CsvImporter(String filePath) throws IOException, IncorrectFileTypeException {
		this(filePath, ";");
	}

	public List<String[]> readContent() {
		List<String[]> strs;
		try {
			strs = new BufferedReader(new FileReader(file)).lines()
					.skip(1)
					.map(s -> s.split(delimiter))
					.filter(str -> str.length != 0)
					.collect(Collectors.toList());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return null;
		}
		return strs;
	}
}
