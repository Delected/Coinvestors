package me.delected.coinvestors;

import java.util.Objects;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.delected.coinvestors.commands.CommandDistributor;
import me.delected.coinvestors.commands.menu.OpenCommand;
import me.delected.coinvestors.controller.CryptoRegulator;
import me.delected.coinvestors.controller.GuiPlayerStateManager;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.model.accounts.AccountService;
import me.delected.coinvestors.util.SubtypeInstanceBuilder;
import net.milkbowl.vault.economy.Economy;

public final class Coinvestors extends JavaPlugin {
	private static Coinvestors INSTANCE;
	private static Economy economy = null;
	private final GuiPlayerStateManager guiPlayerStateManager = new GuiPlayerStateManager();
	private final AccountService accountService = new AccountService();
	//fixme
	private final CryptoRegulator cryptoRegulator = new CryptoRegulator();

	@Override
	public void onLoad() {
		INSTANCE = this;
		SubtypeInstanceBuilder.setLogger(Bukkit.getLogger());
	}

	@Override
	public void onEnable() {
		// have a log of all transactions maybe
		if (!setupEconomy()) {
			Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
			//disable();
			//return;
		}
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

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		economy = rsp.getProvider();
		return true;
	}

	public static Coinvestors instance() {
		return INSTANCE;
	}

	public static GuiPlayerStateManager guiManager() {
		return INSTANCE.guiPlayerStateManager;
	}

	public void disable() {
		getServer().getPluginManager().disablePlugin(this);
	}

	public static AccountService accountService() {
		return INSTANCE.accountService;
	}


}
