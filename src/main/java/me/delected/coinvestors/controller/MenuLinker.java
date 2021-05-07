package me.delected.coinvestors.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.entity.Player;

public class MenuLinker {
	private static final Map<String, Consumer<Player>> LINK_ACTIONS = new HashMap<>();

	public static Consumer<Player> getLink(String link) {
		return LINK_ACTIONS.get(link);
	}

	public static void registerLink(String link, Consumer<Player> action) {
		LINK_ACTIONS.put(link, action);
	}
}
