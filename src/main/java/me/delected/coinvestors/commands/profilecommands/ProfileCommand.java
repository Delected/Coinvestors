//package me.delected.coinvestors.commands.profilecommands;
//
//import me.delected.coinvestors.util.ChatUtils;
//import me.delected.coinvestors.commands.SubCommand;
//import org.bukkit.ChatColor;
//import org.bukkit.command.*;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public final class ProfileCommand implements TabExecutor {
//    SubCommand[] subCmds = {new ProfileView(), new ProfileCreate()};
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (args.length == 0) {
//            sender.sendMessage(ChatUtils.color("&8&m----------&8[ &2Coinvestors &f- &aProfile &8]&m----------"));
//            Arrays.stream(subCmds)
//                    .map(SubCommand::getDescription)
//                    .forEach(sender::sendMessage);
//            sender.sendMessage(ChatUtils.color("&8&m-------------------------------------------"));
//            return true;
//
//        }
//
//        for (SubCommand cmd : subCmds) {
//            if (!cmd.getAliases().contains(args[0].toLowerCase())) continue;
//            if (sender instanceof ConsoleCommandSender && !cmd.canConsoleRun()) { sender.sendMessage(ChatColor.RED + "This command can only be used by a player!"); return true; }
//            if (cmd.getPermission() != null && !sender.hasPermission("coinvestors." + cmd.getPermission())) { sender.sendMessage(ChatColor.RED + "You do not have permission to run this command!"); return true; }
//            cmd.run(sender, args); return true;
//        }
//        sender.sendMessage(ChatColor.RED + "This subcommand does not exist! Type the command without any arguments to see all subcommands.");
//        return true;
//    }
//
//    @Override
//    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
//        if (args.length == 1) { return Arrays.stream(subCmds).map(SubCommand::getPrimaryName).collect(Collectors.toList()); }
//        for (SubCommand cmd : subCmds) {
//            if (!cmd.getAliases().contains(args[0].toLowerCase())) continue;
//            return cmd.getTabCompletions(sender, args);
//        }
//        return null;
//    }
//}
