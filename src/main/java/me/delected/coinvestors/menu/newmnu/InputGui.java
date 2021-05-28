package me.delected.coinvestors.menu.newmnu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;

public abstract class InputGui<T> {

	private T data;
	protected final GuiStage prev;

	protected InputGui(final GuiStage prev) {
		this.prev = prev;
	}

	public abstract void open(Player player);


	protected void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public ItemStack getInfoStack(String link) {
		if (data == null) {
			return presentStack(link);
		}
		return absentStack(link);
	}

	protected abstract ItemStack presentStack(String link);

	protected abstract ItemStack absentStack(String link);

}
