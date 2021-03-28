package me.delected.coinvestors.commands.newprofilecmd;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.delected.coinvestors.commands.AbstractCommand;

public class NewProfileCommand extends AbstractCommand {

	public NewProfileCommand() {
		super("profile");
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		return true;
	}

	@Override
	public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		return null;
	}
}
