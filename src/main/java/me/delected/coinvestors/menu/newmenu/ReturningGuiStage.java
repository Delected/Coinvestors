package me.delected.coinvestors.menu.newmenu;

import java.util.function.Supplier;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;

public abstract class ReturningGuiStage extends GuiStage {

	private static final String RETURN_LINK = "GUI_RETURN_LINK";

	static {
		MenuLinker.registerLink(RETURN_LINK, ReturningGuiStage::returnToPrevious);
	}

	private final GuiStage returnStage;

	protected ReturningGuiStage(final MenuState state, final GuiStage returnStage) {
		super(state);
		this.returnStage = returnStage;
	}

	protected ItemStack returnStack(String name, String... lore) {
		return returnLinkStack(RETURN_LINK, name, lore);
	}


	protected void turnBack(Player player) {
		redirect(player, returnStage);
	}

	private static void returnToPrevious(Player player) {
		actualStage(player, ReturningGuiStage.class).turnBack(player);
	}

}
