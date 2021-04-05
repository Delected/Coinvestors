package me.delected.coinvestors.commands.profilecommands;

import me.delected.coinvestors.util.ChatUtils;
import me.delected.coinvestors.commands.SubCommand;
import me.delected.coinvestors.io.yaml.YamlUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ProfileCreate implements SubCommand {

    @Override
    public String getPrimaryName() { return "create"; }

    @Override
    public String getDescription() { return ChatUtils.color("&fcreate - &7Create your own profile! &c(player only)"); }

    @Override
    public List<String> getAliases() { return Arrays.asList("make", "create", "new", "start"); }

    @Override
    public String getUsage() { return ""; }

    @Override
    public boolean canConsoleRun() { return false; }

    @Override
    public String getPermission() { return "profile.create"; }

    public boolean run(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sendIncorrectArgMsg(sender, "Profile", "Create");
            return true;
        }
        Player p = (Player) sender;
        if (YamlUtils.hasProfile(p)) { p.sendMessage(ChatColor.RED + "You already have a profile! Use /profile view to see it!"); return true; }
        YamlUtils.createProfile(p);

        sender.sendMessage(ChatColor.GREEN + "You have created a new profile! Use /profile view to see it!");
        return true;
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) { return null; }
}
