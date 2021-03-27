package me.delected.coinvestors;

import me.delected.coinvestors.commands.profilecommands.ProfileCommand;
import me.delected.coinvestors.commands.test.FortniteCommand;
import me.delected.coinvestors.storage.Yaml;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Coinvestors extends JavaPlugin {
    private static JavaPlugin plugin;
    public static JavaPlugin getPlugin() { return plugin; }

    public static Yaml profilesYaml = new Yaml("profiles");
    public static Yaml walletsYaml = new Yaml("wallets");
    public static Yaml rigsYaml = new Yaml("rigs");

    @Override
    public void onEnable() {
        plugin = this;
        // profiles.yml
        profilesYaml.setup();
        // wallets.yml
        walletsYaml.setup();
        // rigs.yml
        rigsYaml.setup();
        // have a log of all transactions
        saveDefaultConfig();



        registerCommand("profile", new ProfileCommand());
        registerCommand("fortnite", new FortniteCommand());

        setupVault();
    }

    private void registerCommand(String name, Object c) {
        Objects.requireNonNull(getCommand(name)).setExecutor((CommandExecutor) c);
        Objects.requireNonNull(getCommand(name)).setTabCompleter((TabCompleter) c);
    }

    private void setupVault() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("Vault is a requirement for Coinvestors to work. Please download it, and restart your server. Disabling...");
            setEnabled(false);
        }
    }
}
