package me.delected.coinvestors.storage;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.delected.coinvestors.Coinvestors;

public class Yaml {
	private File file;
	private FileConfiguration customFile;
	private final String name;

	public Yaml(String name) {
		this.name = name;
	}

	public void create() throws IOException {
		file = new File(Coinvestors.INSTANCE.getDataFolder(), name + ".yml");

		if (file.createNewFile())
			Bukkit.getLogger().warning("There was no existing " + name + " file. Successfully created a new one.");

		customFile = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getConfig() {
		return customFile;
	}

	public void save() {
		try {
			customFile.save(file);
		} catch (IOException e) {
			Bukkit.getLogger().severe("There was en error saving the " + name + ".yml file!");
		}
	}

	public void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}

	public void setup() {
		try {
			create();
			String header = "please do not edit this unless you know what you are doing! This is a yaml file containing all users" + name + " data";
			getConfig().options().copyHeader(true).header(header);
			getConfig().options().copyDefaults(true);
			save();
		} catch (IOException e) {
			Bukkit.getLogger().severe("There was en error while setting up the " + name + ".yml file: " + e.getClass().getSimpleName());
		}
	}
}
