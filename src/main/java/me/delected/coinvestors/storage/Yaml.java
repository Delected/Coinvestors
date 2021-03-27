package me.delected.coinvestors.storage;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Yaml {
    private File file;
    private FileConfiguration customFile;
    private final String name;

    public Yaml(String name) {
        this.name = name;
    }

    public void create() throws IOException {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Coinvestors").getDataFolder(), name + ".yml");

        if (!file.exists()) {
            file.createNewFile();
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public FileConfiguration get() {
        return customFile;
    }

    public void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().severe("There was en error while setting up the " + name + ".yml file!");
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public void setup() {
        try {
            create();
            get().options().copyHeader(true).header("please do not edit this unless you know what you are doing! This is a yaml file containing all user " + name);
            get().options().copyDefaults(true);
            save();
        } catch (IOException ioex) {
            Bukkit.getLogger().severe("There was en error while setting up the " + name + ".yml file!");
        }
    }
}
