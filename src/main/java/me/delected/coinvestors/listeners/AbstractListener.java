package me.delected.coinvestors.listeners;

import java.util.Set;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import me.delected.coinvestors.util.SubtypeInstanceBuilder;

public abstract class AbstractListener<E extends Event> implements Listener {

	public abstract void handle(E event);

	public static Set<Listener> createListeners() {
		return SubtypeInstanceBuilder.createInstances(AbstractListener.class, "me.delected.coinvestors.listeners");
	}

}
