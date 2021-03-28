package me.delected.coinvestors.commands;

import org.bukkit.command.CommandSender;

public interface NewSubCommand {
	boolean run(CommandSender sender, String[] args);

	String getPermission();

	default boolean hasPermission(CommandSender sender) {
		return sender.hasPermission(getPermission());
	}
}
