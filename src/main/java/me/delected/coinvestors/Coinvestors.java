package me.delected.coinvestors;

import java.util.Objects;
import java.util.Optional;

import me.delected.coinvestors.controller.CryptoRegulatorTask;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import me.delected.coinvestors.commands.CommandDistributor;
import me.delected.coinvestors.commands.menu.OpenCommand;
import me.delected.coinvestors.controller.GuiPlayerStateManager;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.model.accounts.AccountService;
import me.delected.coinvestors.util.SubtypeInstanceBuilder;
import me.delected.coinvestors.vault.DummyEconomy;
import net.milkbowl.vault.economy.Economy;

public final class Coinvestors extends JavaPlugin {
	private static Coinvestors INSTANCE;
	private static Economy ECONOMY = null;
	private final GuiPlayerStateManager guiPlayerStateManager = new GuiPlayerStateManager();
	private final AccountService accountService = new AccountService();

	@Override
	public void onLoad() {
		INSTANCE = this;
		SubtypeInstanceBuilder.setLogger(Bukkit.getLogger());
	}

	@Override
	public void onEnable() {
		// have a log of all transactions maybe
		if (!setupEconomy()) {
			Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!",
					getDescription().getName()));
			//disable();
			//return;
		} else {
			System.out.println("success");
		}
		accountService.setEconomy(ECONOMY);
		saveDefaultConfig();
		//register commands
		Optional<PluginCommand> cvCommand = Optional.ofNullable(getCommand("coinvestors"));
		CommandDistributor distributor;
		cvCommand.orElseThrow(ContactTheDevsException::new)
				.setExecutor((distributor = new CommandDistributor()));
		cvCommand.get().setTabCompleter(distributor);
		//register all Listeners
		PluginManager pluginManager = getServer().getPluginManager();
		AbstractListener.createListeners().forEach(l -> pluginManager.registerEvents(l, this));
		//DEBUG
		Objects.requireNonNull(getCommand("open")).setExecutor(new OpenCommand());

		new CryptoRegulatorTask().runTaskTimerAsynchronously(
				this, 0L, getConfig().getLong("update_interval", 150));
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (rsp == null) {
			ECONOMY = registerEconomy();
			return true;
		}
		ECONOMY = rsp.getProvider();
		System.out.println(ECONOMY);
		return true;
	}

	private DummyEconomy registerEconomy() {
		DummyEconomy result = new DummyEconomy();
		getServer().getServicesManager().register(Economy.class, result, this, ServicePriority.Highest);
		return result;
	}

	public static Coinvestors instance() {
		return INSTANCE;
	}

	public static GuiPlayerStateManager guiManager() {
		return INSTANCE.guiPlayerStateManager;
	}

	public static AccountService accountService() {
		return INSTANCE.accountService;
	}

	public static Economy economy() {
		return ECONOMY;
	}
}
