package me.delected.coinvestors.commands.test;

import me.delected.coinvestors.Chat;
import me.delected.coinvestors.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TwitchTierThree implements SubCommand {
    @Override
    public String getPrimaryName() {
        return "t3";
    }

    @Override
    public String getDescription() {
        return Chat.color("sss");
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("t3", "t3s");
    }

    @Override
    public String getUsage() {
        return " xddsadfe343";
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean canConsoleRun() {
        return true;
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        sender.sendMessage("u t3 subbed to poki!");
        return false;
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return null;
    }
}
