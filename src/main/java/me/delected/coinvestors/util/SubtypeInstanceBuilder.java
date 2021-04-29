package me.delected.coinvestors.util;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.reflections.Reflections;

/**
 * A utility class for making it easy to create singletons of subtypes in the same package.
 */
public class SubtypeInstanceBuilder {

	/**
	 * Builds a singleton of all subtypes in a package using the declared standard Constructor.
	 * If a class shall be instantiated by this, it has to have a standard constructor, otherwise it will be skipped
	 *
	 * @param superclass The class all singletons extend
	 * @param pkg        the package location like it would be in the import statement. Also searches all subpackages
	 * @param <T>        The Class the result should have, may be a superclass or interface of the superclass
	 * @return a set containing all the instances
	 */
	public static <T> Set<T> createInstances(Class<? extends T> superclass, String pkg) {
		Reflections reflections = new Reflections(pkg);
		return reflections.getSubTypesOf(superclass).stream()
				//remove abstract classes and interfaces in that packages
				.filter(subclass -> !Modifier.isAbstract(subclass.getModifiers()) && !Modifier.isInterface(subclass.getModifiers()))
				//make sure the default constructor exists
				.filter(subclass -> Stream.of(subclass.getDeclaredConstructors()).anyMatch(constructor -> constructor.getParameterCount() == 0))
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
