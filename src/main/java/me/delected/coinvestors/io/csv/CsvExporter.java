package me.delected.coinvestors.io.csv;

import me.delected.coinvestors.io.FileAccessor;

import java.io.File;

public class CsvExporter extends FileAccessor {
    String delim;

    public CsvExporter(File file, String delim) {
        super(file);
        this.delim = delim;
    }
}
