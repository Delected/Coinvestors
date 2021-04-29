package me.delected.coinvestors.listeners;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import me.delected.coinvestors.commands.AbstractCommand;

public abstract class AbstractListener<E extends Event> implements Listener {

	public abstract void handle(E event);

	public static Set<Listener> createListeners() {
		Reflections reflections = new Reflections("me.delected.coinvestors.commands");
		return reflections.getSubTypesOf(AbstractListener.class).stream()
				//remove abstract classes and interfaces in that packages
				.filter(command -> !Modifier.isAbstract(command.getModifiers()) && !Modifier.isInterface(command.getModifiers()))
				//make sure the default constructor exists
				.filter(command -> Stream.of(command.getDeclaredConstructors()).anyMatch(constructor -> constructor.getParameterCount() == 0))
				//create a new instance using this constructor
				.map(commandClass -> {
					try {
						return commandClass.getDeclaredConstructor().newInstance();
					} catch (ReflectiveOperationException e) {
						Bukkit.getLogger().severe("Could not initialize the " + commandClass.getSimpleName());
						Bukkit.getLogger().severe(e.getMessage() + e.getCause());
						return null;
					}
				})
				//everything should have worked fine.
				//If not, there is a bug that has to be fixed!
				//But to continue here we just remove this null-Object.
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
	}

}
