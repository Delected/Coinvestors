package me.delected.coinvestors;

import me.delected.coinvestors.commands.CommandDistributor;
import me.delected.coinvestors.commands.profilecommands.ProfileCommand;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.storage.Yaml;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Optional;

public final class Coinvestors extends JavaPlugin {
	public static JavaPlugin INSTANCE;

	public static Yaml profilesYaml = new Yaml("profiles");
	public static Yaml walletsYaml = new Yaml("wallets");
	public static Yaml rigsYaml = new Yaml("rigs");

	@Override
	public void onLoad() {
		INSTANCE = this;
	}

	@Override
	public void onEnable() {
		// profiles.yml
		profilesYaml.setup();
		// wallets.yml
		walletsYaml.setup();
		// rigs.yml
		rigsYaml.setup();
		// have a log of all transactions
		saveDefaultConfig();
		Optional<PluginCommand> cvCommand = Optional.ofNullable(getCommand("coinvestors"));
		cvCommand.orElseThrow(ContactTheDevsException::new).setExecutor(new CommandDistributor());
	}

}
