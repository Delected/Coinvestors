package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWriter extends CsvAccessor {

	public CsvWriter(String filePath, String delimiter) throws IOException, IllegalArgumentException {
		super(filePath, delimiter);
	}

	public CsvWriter(String filePath) throws IOException, IncorrectFileTypeException {
		this(filePath, ";");
	}

	public void addRow(String... strings) {
		try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
			for (String s : strings) {
				write.append(s);
			}
			write.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
