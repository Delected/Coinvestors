package me.delected.coinvestors.commands.profilecommands;

import me.delected.coinvestors.Chat;
import me.delected.coinvestors.commands.SubCommand;
import me.delected.coinvestors.storage.StorageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ProfileDelete implements SubCommand {

    @Override
    public String getPrimaryName() { return "delete"; }

    @Override
    public String getDescription() { return Chat.color("&fdelete - &7Deletes a user's profile, or your own!"); }

    @Override
    public List<String> getAliases() { return Arrays.asList("delete", "remove", "clear", "reset"); }

    @Override
    public String getUsage() { return " (optional: username)"; }

    @Override
    public boolean canConsoleRun() { return true; }

    @Override
    public String getPermission() { return "profile.delete"; }

    public boolean run(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(ChatColor.RED + "Only players can see delete own profile, please add a name to delete someone else's profile.");
                return true;
            }
            Player p = (Player) sender;
            if (!StorageUtils.hasProfile(p)) {
                p.sendMessage(ChatColor.RED + "You do not have a profile! Create one with /profile create");
            }
            p.sendMessage(Chat.color("&4&lAre you sure you would like to delete your profile? &c&lThis will delete ALL of your wallets, " +
                    "you will lose all of your money that is stored in this profile! &8Type the command again to confirm."));
            StorageUtils.deleteProfile(p);
            // delete all wallets
            return true;
        } else if (args.length == 2) {
            if (!sender.hasPermission("coinvestors.profile.delete.others")) { sender.sendMessage(ChatColor.RED + "You do not have permission to delete another user's profile!"); return true; }
            Player p = Bukkit.getPlayer(args[1]);
            if (p == null) { sender.sendMessage(ChatColor.RED + "This player does not exist!");  return true; }
            if (!StorageUtils.hasProfile(p)) {
                sender.sendMessage(ChatColor.RED + "This player does not have a profile, or they do not exist!"); return true;
            }

            StorageUtils.deleteProfile(p);
            return true;
        } else {
            sendIncorrectArgMsg(sender, "Profile", "Create");
            return true;
        }
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) { return null; }
}
