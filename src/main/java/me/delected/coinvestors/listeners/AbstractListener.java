package me.delected.coinvestors.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class AbstractListener<E extends Event> implements Listener {

	@EventHandler
	public abstract void handle(E event);

}
