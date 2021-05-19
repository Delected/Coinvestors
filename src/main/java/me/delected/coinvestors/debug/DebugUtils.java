package me.delected.coinvestors.debug;

import java.lang.reflect.Method;
import java.util.Arrays;

public class DebugUtils {
	public static void inspectClass(Class<?> clazz) {
		if (clazz != Object.class) {
			System.out.println("--------------------------------------------");
			System.out.println(clazz.getName());
			System.out.println("Interfaces: " + Arrays.toString(clazz.getInterfaces()));
			System.out.println("methods: ");
			for (final Method method : clazz.getMethods()) {
				System.out.println(method.getReturnType() + " " + method.getName() + Arrays.toString(method.getParameterTypes()));
			}
			System.out.println("fields: " + Arrays.toString(clazz.getDeclaredFields()));
			System.out.println("--------------------------------------------");
			inspectClass(clazz.getSuperclass());
		}
	}
}
