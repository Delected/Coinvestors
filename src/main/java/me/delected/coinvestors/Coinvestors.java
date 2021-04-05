package me.delected.coinvestors;

import java.util.Optional;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import me.delected.coinvestors.commands.CommandDistributor;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.io.yaml.Yaml;

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
		CommandDistributor distributor;
		cvCommand.orElseThrow(ContactTheDevsException::new).setExecutor((distributor = new CommandDistributor()));
		cvCommand.get().setTabCompleter(distributor);
	}

}
