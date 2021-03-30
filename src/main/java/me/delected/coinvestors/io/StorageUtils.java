package me.delected.coinvestors.io;

import me.delected.coinvestors.Coinvestors;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class provides helper utilities for reading/writing with the Yaml files provided
 * in the plugin's data folder
 */
public class StorageUtils {


    //----------( profiles.yml )----------\\
    private static final Yaml profile = Coinvestors.profilesYaml;
    public static void createProfile(Player p) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        profile.getConfig().set("profiles." + p.getUniqueId() + ".date-created", dtf.format(LocalDateTime.now()));
        profile.save();
    }

    public static boolean hasProfile(Player p) {
        return profile.getConfig().get("profiles." + p.getUniqueId()) != null;
    }

    public static void deleteProfile(Player p) {
        profile.getConfig().set("profiles." + p.getUniqueId(), null);
        profile.save();
    }

    //----------( wallets.yml )----------\\
    private static final FileConfiguration wallet = Coinvestors.walletsYaml.getConfig();

    public static boolean walletExists(String key) {
        return wallet.get(key) != null;
    }
}
