package me.delected.coinvestors.commands;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.exceptions.ContactTheDevsException;

public abstract class AbstractCommand implements TabExecutor {

	private static final String PREFIX = "cv";

	private final String name;
	private final Set<String> aliases;

	protected AbstractCommand(final String name) {
		this.name = name;
		this.aliases = getAliases();
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

	private Optional<Command> getCommand() {
		return Optional.ofNullable(Coinvestors.INSTANCE.getCommand(PREFIX + " " + name));
	}

	public String getHelp() {
		return getCommand().map(value -> name + value.getUsage())
				.orElse("An internal error occurred retrieving the command info. Please contact the server admins or developers");
	}

	protected Set<String> getAliases() {
		return new HashSet<>(getCommand().orElseThrow(ContactTheDevsException::new).getAliases());
	}

}
