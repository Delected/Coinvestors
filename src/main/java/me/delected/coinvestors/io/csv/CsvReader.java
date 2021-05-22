package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CsvReader extends CsvAccessor {

	public CsvReader(String filePath, String delimiter) throws IOException, IncorrectFileTypeException {
		super(filePath, delimiter);
	}

	public CsvReader(String filePath) throws IOException, IncorrectFileTypeException {
		this(filePath, ";");
	}

	public List<String[]> readContent() {
		List<String[]> strs;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			strs = reader.lines()
					.skip(1)
					.map(s -> s.split(delimiter))
					.filter(str -> str.length != 0)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return strs;
	}
}
