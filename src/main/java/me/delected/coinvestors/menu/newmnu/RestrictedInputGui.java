package me.delected.coinvestors.menu.newmnu;

import java.util.Arrays;
import java.util.Objects;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.util.ItemStackCreator;

public class RestrictedInputGui<T> extends InputGui<T> {
	private final InputGui<T> lockedGui;
	private final InputGui<?>[] inputGuis;

	protected RestrictedInputGui(final GuiStage nextGui, InputGui<T> lockedGui, InputGui<?>... inputGuis) {
		super(nextGui);
		this.lockedGui = lockedGui;
		this.inputGuis = inputGuis;
	}

	@Override
	public void open(final Player player) {
		if (isLocked()) {
			return;
		}
		lockedGui.open(player);
	}

	private boolean isLocked() {
		return Arrays.stream(inputGuis).map(InputGui::getData).anyMatch(Objects::isNull);
	}

	@Override
	public ItemStack getInfoStack(final String link) {
		if (isLocked()) {
			return lockedStack();
		}
		if (lockedGui.getData() == null) {
			return absentStack(link);
		}
		return presentStack(link);
	}

	@Override
	protected ItemStack presentStack(final String link) {
		return lockedGui.presentStack(link);
	}

	@Override
	protected ItemStack absentStack(final String link) {
		return lockedGui.absentStack(link);
	}

	private ItemStack lockedStack() {
		return new ItemStackCreator(Material.LIGHT_GRAY_WOOL)
				.setName(ChatColor.RED + "Please complete the unlocked inputs first!")
				.setUnmodifiable()
				.build();
	}

	@Override
	public T getData() {
		return lockedGui.getData();
	}
}
