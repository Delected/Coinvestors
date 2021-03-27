package me.delected.coinvestors.commands;

import me.delected.coinvestors.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;
import java.util.stream.Collectors;

public interface SubCommand {
    String getPrimaryName();

    String getDescription();

    List<String> getAliases();

    String getUsage();

    String getPermission();

    boolean canConsoleRun();

    boolean run(CommandSender sender, String[] args);

    default String getActualUsage() { return "/<command> <subcommand> " + getUsage(); }

    List<String> getTabCompletions(CommandSender sender, String[] args);

    /**
     * @return List<String> of all online players
     */
    default List<String> getOnlinePlayersList() { return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()); }


    // sends an incorrect usage message with proper usage
    default void sendIncorrectArgMsg(CommandSender sender, String category, String sub) {
        sender.sendMessage(Chat.color("&8&m----------&8[ &2" + category + "&f - &a" + sub + " &8]&m----------"));
        sender.sendMessage(Chat.color("&cIncorrect usage! " + getActualUsage()));
        sender.sendMessage(Chat.color("&8&m-------------------------------------------"));
    }
}
