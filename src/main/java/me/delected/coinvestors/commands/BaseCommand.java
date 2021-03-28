package me.delected.coinvestors.commands;

import me.delected.coinvestors.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseCommand implements TabExecutor {
    private static final String PLUGIN_NAME = "Coinvestors"; // change this to whatever your plugin name is

    public abstract SubCommand[] getSubCommands();
    public abstract String getCommandName();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // if no arguments are provided (/<command>), send a message with each subcommand
        if (args.length == 0) {
            sender.sendMessage(Chat.color("&8&m----------&8[ &2" + PLUGIN_NAME + " &f- &a" + getCommandName() +  " &8]&m----------"));
            Arrays.stream(getSubCommands())
                    .map(SubCommand::getDescription)
                    .forEach(sender::sendMessage);
            sender.sendMessage(Chat.color("&8&m-------------------------------------------"));
            return true;

        }

        // otherwise, iterate through each subcommand
        for (SubCommand cmd : getSubCommands()) {
            // if the command's aliases does not contain the argument provided, skip it
            if (!cmd.getAliases().contains(args[0].toLowerCase())) continue;
            // if the sender is console, and the command does not allow console to run it, then send an error message
            if (sender instanceof ConsoleCommandSender && !cmd.canConsoleRun()) {
                sender.sendMessage(Chat.color("&cThis command can only be used by a player!"));
                return true;
            }
            // if the command has permissions, but the sender does not have them, send an error message
            if (cmd.getPermission() != null && !sender.hasPermission(PLUGIN_NAME.toLowerCase() + "." + cmd.getPermission())) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!");
                return true;
            }

            // otherwise, everything worked fine... run the command
            cmd.run(sender, args); return true;
        }

        // no command was found in the loop, send an error message
        sender.sendMessage(ChatColor.RED + "This subcommand does not exist! Type the command without any arguments to see all subcommands.");
        return true;
    }

    // tab completion
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // if there is 1 argument, get the primary name of every subcommand, and add it as a tab completion option
        if (args.length == 1) { return Arrays.stream(getSubCommands()).map(SubCommand::getPrimaryName).collect(Collectors.toList()); }

        // loop through each subcommand, if the arg matches that command, set the tab completion options
        for (SubCommand cmd : getSubCommands()) {
            if (!cmd.getAliases().contains(args[0].toLowerCase())) continue;
            return cmd.getTabCompletions(sender, args);
        }
        // if nothing is found, return null
        return null;
    }
}
