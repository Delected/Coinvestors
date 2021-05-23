package me.delected.coinvestors;

import java.util.Objects;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.delected.coinvestors.commands.CommandDistributor;
import me.delected.coinvestors.commands.menu.OpenCommand;
import me.delected.coinvestors.controller.CryptoRegulator;
import me.delected.coinvestors.controller.GuiPlayerStateManager;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.model.accounts.AccountService;
import me.delected.coinvestors.util.SubtypeInstanceBuilder;

public final class Coinvestors extends JavaPlugin {
	private static Coinvestors INSTANCE;
	private final GuiPlayerStateManager guiPlayerStateManager = new GuiPlayerStateManager();
	private final AccountService accountService = new AccountService();
	//fixme
	private final CryptoRegulator cryptoRegulator = new CryptoRegulator();

	@Override
	public void onLoad() {
		INSTANCE = this;
	}

	@Override
	public void onEnable() {
		SubtypeInstanceBuilder.setLogger(Bukkit.getLogger());
		// have a log of all transactions maybe
		saveDefaultConfig();
		//register commands
		Optional<PluginCommand> cvCommand = Optional.ofNullable(getCommand("coinvestors"));
		CommandDistributor distributor;
		cvCommand.orElseThrow(ContactTheDevsException::new).setExecutor((distributor = new CommandDistributor()));
		cvCommand.get().setTabCompleter(distributor);
		//register all Listeners
		PluginManager pluginManager = getServer().getPluginManager();
		AbstractListener.createListeners().forEach(l -> pluginManager.registerEvents(l, this));
		//DEBUG
		Objects.requireNonNull(getCommand("open")).setExecutor(new OpenCommand());
		/*
		Bukkit.getScheduler().runTaskTimer(this, CryptoRegulator::recalculatePrices,
				0L, getConfig().getInt("reload_seconds") * 1000L);*/
	}

	public static JavaPlugin INSTANCE() {
		return INSTANCE;
	}

	public static GuiPlayerStateManager guiManager() {
		return INSTANCE.guiPlayerStateManager;
	}

	public static AccountService accountService() {
		return INSTANCE.accountService;
	}


}
