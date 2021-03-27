package me.delected.coinvestors.commands.walletcommands;

import me.delected.coinvestors.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RemoveWallet implements SubCommand {
    @Override
    public String getPrimaryName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean canConsoleRun() {
        return false;
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        return false;
    }

    @Override
    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return null;
    }
}
