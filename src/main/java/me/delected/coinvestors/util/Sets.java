package me.delected.coinvestors.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Sets {
	public static <T> Set<T> newSet(T... t) {
		return Arrays.stream(t).collect(Collectors.toSet());
	}
}
