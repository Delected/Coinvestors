package me.delected.coinvestors.commands;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.reflections.Reflections;

public class CommandDistributor implements TabExecutor {

	//TODO: replace with custom TabExecutor class which is able to register itself etc

	private final Set<TabExecutor> executors = setupExecutors();

	private static Set<TabExecutor> setupExecutors() {
		Reflections reflections = new Reflections("me.delected.coinvestors.commands");
		return reflections.getSubTypesOf(TabExecutor.class).stream()
				//remove abstract classes and interfaces in that packages
				.filter(command -> !Modifier.isAbstract(command.getModifiers()) && !Modifier.isInterface(command.getModifiers()))
				//make sure the default constructor exists (to remove classes like HelpCommand)
				.filter(command -> Stream.of(command.getDeclaredConstructors()).anyMatch(constructor -> constructor.getParameterCount() == 0))
				//create a new instance using this constructor
				.map(commandClass -> {
					try {
						return commandClass.getDeclaredConstructor().newInstance();
					} catch (ReflectiveOperationException e) {
						Bukkit.getLogger().severe("Could not initialize the " + commandClass.getSimpleName());
						return null;
					}
				})
				//everything should have worked fine.
				//If not, there is a bug that has to be fixed!
				//But to continue here we just remove this null-Object.
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
	}

	@Override
	public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		return false;
	}

	@Override
	public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
		return null;
	}
}
