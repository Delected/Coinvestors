package me.delected.coinvestors.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import me.delected.coinvestors.util.SubtypeInstanceBuilder;

public class CommandDistributor implements TabExecutor {

	//TODO: replace with custom TabExecutor class which is able to register itself etc

	private final Set<AbstractCommand> executors = setupExecutors();

	private static Set<AbstractCommand> setupExecutors() {
		return SubtypeInstanceBuilder.createInstances(AbstractCommand.class, "me.delected.coinvestors.commands");
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		System.out.println("Command: " + command + "; Alias: " + s + "; Args: " + Arrays.toString(strings));
		System.out.println(executors);
		if (strings.length == 0) {
			printInfo(commandSender);
			return true;
		}
		String commandString = strings[0].toLowerCase();
		for (AbstractCommand executor : executors) {
			if (executor.isCalled(commandString))
				return executor.onCommand(commandSender, command, s, Arrays.copyOfRange(strings, 1, s.length()));
		}
		commandSender.sendMessage("There is no such command. Type /cv info for help!");
		return true;
	}

	private void printInfo(CommandSender sender) {
		sender.sendMessage("The ultimate crypto plugin!");
	}

	@Override
	public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		return executors.stream().filter(e -> e.isCalled("foo")).map(Object::toString).collect(Collectors.toList());
	}
}
