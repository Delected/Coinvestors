package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;
import me.delected.coinvestors.listeners.inventory.InventoryClickListener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			String s = String.join(delimiter, strings);
			writer.append(s);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
