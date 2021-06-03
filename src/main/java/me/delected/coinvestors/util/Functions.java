package me.delected.coinvestors.util;

import java.util.function.Consumer;

public class Functions {
	public static <T> Consumer<T> ignore() {
		return t -> {
		};
	}
}
