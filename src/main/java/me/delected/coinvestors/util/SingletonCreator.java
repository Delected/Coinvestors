package me.delected.coinvestors.util;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.reflections.Reflections;

public class SingletonCreator {
	public static <T> Set<T> createSingletons(Class<? extends T> superclass, String pkg) {
		Reflections reflections = new Reflections(pkg);
		return reflections.getSubTypesOf(superclass).stream()
				//remove abstract classes and interfaces in that packages
				.filter(command -> !Modifier.isAbstract(command.getModifiers()) && !Modifier.isInterface(command.getModifiers()))
				//make sure the default constructor exists
				.filter(command -> Stream.of(command.getDeclaredConstructors()).anyMatch(constructor -> constructor.getParameterCount() == 0))
				//create a new instance using this constructor
				.map(subclass -> {
					try {
						return subclass.getDeclaredConstructor().newInstance();
					} catch (ReflectiveOperationException e) {
						Bukkit.getLogger().severe("Could not initialize the " + subclass.getSimpleName());
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
