package me.delected.coinvestors.commands.test;

import me.delected.coinvestors.commands.BaseCommand;
import me.delected.coinvestors.commands.SubCommand;

public class FortniteCommand extends BaseCommand {

    @Override
    public SubCommand[] getSubCommands() {
        return new SubCommand[]{new TwitchTierThree(), new TwitchSub()};
    }

    @Override
    public String getCommandName() {
        return "fortnite";
    }
}
