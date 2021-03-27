package me.delected.coinvestors.commands.test;

import me.delected.coinvestors.Chat;
import me.delected.coinvestors.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TwitchSub implements SubCommand {
    @Override
    public String getPrimaryName() {
        return "sub";
    }

    @Override
    public String getDescription() {
        return Chat.color("&7XDCLIQKSSS &cXD!!!!");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("s", "poki");
    }

    @Override
    public String getUsage() {
        return "xdd!!";
    }

    @Override
    public String getPermission() {
        return "a.aaa";
    }

    @Override
    public boolean canConsoleRun() {
        return false;
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        sender.sendMessage("u clicked xd");
        return true;
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if (args.length == 2) {
            return Arrays.asList("xD", "poo");
        }
        return null;
    }
}
