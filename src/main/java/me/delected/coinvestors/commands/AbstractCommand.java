package me.delected.coinvestors.commands;

import java.util.Optional;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.delected.coinvestors.Coinvestors;

public abstract class AbstractCommand implements CommandExecutor {

	private static final String PREFIX = "cv";

	private final String name;
	private final Set<String> aliases;

	protected AbstractCommand(final String name, final Set<String> aliases) {
		this.name = name;
		this.aliases = aliases;
	}

	public boolean isCalled(String command) {
		return name.equals(command) || aliases.contains(command);
	}

	protected boolean playerOnly(CommandSender sender) {
		if (sender instanceof Player)
			return true;
		sender.sendMessage("You must be a player to perform this command!");
		return false;
	}

	public String getHelp() {
		Optional<Command> command = Optional.ofNullable(Coinvestors.INSTANCE.getCommand(PREFIX + " " + name));
		return command.map(value -> name + value.getUsage())
				.orElse("An internal error occurred retrieving the command info. Please contact the server admins or developers");
	}

}
