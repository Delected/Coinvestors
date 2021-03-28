package me.delected.coinvestors.commands;

import me.delected.coinvestors.Chat;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;
import java.util.stream.Collectors;
@Deprecated
public interface SubCommand extends NewSubCommand {
	String getPrimaryName();

	String getDescription();

	List<String> getAliases();

	String getUsage();

	boolean canConsoleRun();

	/**
	 * @see this:getTabCompletions()
	 */
	@Deprecated
	default String getActualUsage() {
		return "/<command> <subcommand> " + getUsage();
	}


	/**
	 * @deprecated useless atm, will come back, but mabe not here
	 */
	@Deprecated
	List<String> getTabCompletions(CommandSender sender, String[] args);



	/**
	 * @deprecated doesn't belong here
	 * @return List<String> of all online players
	 */
	@Deprecated
	default List<String> getOnlinePlayersList() {
		return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
	}

	@Deprecated
	// sends an incorrect usage message with proper usage
	default void sendIncorrectArgMsg(CommandSender sender, String category, String sub) {
		sender.sendMessage(Chat.color("&8&m----------&8[ &2" + category + "&f - &a" + sub + " &8]&m----------"));
		sender.sendMessage(Chat.color("&cIncorrect usage! " + getActualUsage()));
		sender.sendMessage(Chat.color("&8&m-------------------------------------------"));
	}
}
