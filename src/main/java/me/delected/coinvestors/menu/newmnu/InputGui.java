package me.delected.coinvestors.menu.newmnu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;

public abstract class InputGui<T> {

	private T data;
	protected final GuiStage nextGui;

	protected InputGui(final GuiStage nextGui) {
		this.nextGui = nextGui;
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
			return absentStack(link);
		}
		return presentStack(link);
	}

	protected abstract ItemStack presentStack(String link);

	protected abstract ItemStack absentStack(String link);

}
