package me.delected.coinvestors.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.util.ItemStackCreator;


public class AccountCreationGuiStage extends GuiStage {

	private static final String CREATE_LINK = "ACCOUNT_CREATE_CONFIRM_LINK";
	private static final String TITLE = ChatColor.DARK_BLUE + "Create an Account?";

	static {
		MenuLinker.registerLink(CREATE_LINK, AccountCreationGuiStage::createAccountFor);
	}

	public AccountCreationGuiStage() {
		super(MenuState.ACCOUNT_CREATE);

	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 9, TITLE);
		ItemStack[] contents = new ItemStack[9];
		contents[4] = accountCreationStack();
		contents[8] = closeStack(ChatColor.WHITE + "Aborts the account creation");
		result.setContents(contents);
		return result;
	}

	private ItemStack accountCreationStack() {
		return new ItemStackCreator(Material.CRAFTING_TABLE)
				.setLink(CREATE_LINK)
				.setName(ChatColor.GREEN + "Confirm!")
				.build();
	}

	private static void createAccountFor(Player player) {
		Coinvestors.accountService().createAccount(player);
		GuiStage.toMainMenu(player);
	}

}
