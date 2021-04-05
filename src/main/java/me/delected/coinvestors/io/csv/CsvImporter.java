package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;
import me.delected.coinvestors.io.FileAccessor;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CsvImporter extends FileAccessor {
    String delim;

    public CsvImporter(File file, String delim) {
        super(file);
        this.delim = delim;
    }

    public List<String[]> toListByRow() {
        if (!isFileCsv()) throw new IncorrectFileTypeException();

        List<String[]> strs;
        try {
            strs = new BufferedReader(new FileReader(file)).lines()
                    .map(s -> s.split(delim))
                    .filter(str -> str.length != 0)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            return null;
        }
        return strs;

    }



    private boolean isFileCsv() { return file.getPath().endsWith(".csv"); }
}
