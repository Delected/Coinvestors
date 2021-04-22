package me.delected.coinvestors.io;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;

import me.delected.coinvestors.exceptions.IncorrectFileTypeException;

public abstract class FileAccessor {
	protected final File file;

	protected FileAccessor(File file, String type) throws IOException, IncorrectFileTypeException {
		this.file = file;
		if (isTyped(file, type)) {
			throw new IncorrectFileTypeException(file, type);
		}
		try {
			if (file.createNewFile()) {
				Bukkit.getLogger().warning("There was no File at the path " + file.getPath() + " . Created a new one!");
			}
		} catch (IOException e) {
			Bukkit.getLogger().severe("Could not access " + file.getPath() + "!");
			throw e;
		}

	}

	protected FileAccessor(String path, String type) throws IOException, IncorrectFileTypeException {
		this(new File(path), type);
	}

	private static boolean isTyped(File file, String fileType) {
		return file.getPath().endsWith(fileType);
	}

}
