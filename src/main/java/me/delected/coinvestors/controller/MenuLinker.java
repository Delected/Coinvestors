package me.delected.coinvestors.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuLinker {
	private static final Map<String, Consumer<Player>> LINK_ACTIONS = new HashMap<>();
	private static final Map<String, BiConsumer<Player, InventoryClickEvent>> EVENT_LINK_ACTIONS = new HashMap<>();

	public static Consumer<Player> getAction(String link) {
		return LINK_ACTIONS.getOrDefault(link, p -> warn(p, link));
	}

	public static BiConsumer<Player, InventoryClickEvent> getEventAction(String link) {
		return EVENT_LINK_ACTIONS.getOrDefault(link, (p, e) -> warn(p, link));
	}

	public static void registerLink(String link, Consumer<Player> action) {
		LINK_ACTIONS.put(link, action);
	}

	public static void registerEventLink(String link, BiConsumer<Player, InventoryClickEvent> eventBiConsumer) {
		EVENT_LINK_ACTIONS.put(link, eventBiConsumer);
	}

	private static void warn(Player player, String link) {
		Bukkit.getLogger().warning(player + " tried to use link " + link + " which had no mapping!");
	}
}
