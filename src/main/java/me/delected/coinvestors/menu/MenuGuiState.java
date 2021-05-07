package me.delected.coinvestors.menu;

import static org.bukkit.Material.BROWN_WOOL;
import static org.bukkit.Material.GREEN_WOOL;
import static org.bukkit.Material.RED_WOOL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.util.ItemStackCreator;
import me.delected.coinvestors.util.PersistentDataManager;

public class MenuGuiState extends GuiStage {

	private static final Inventory INVENTORY = createInventory();
	private static final String MENU_TRANSACTION_LINK = "MENU_TO_TRANSACTION";

	static {
		MenuLinker.registerLink(MENU_TRANSACTION_LINK, MenuGuiState::menuToTransactionChange);
	}

	public MenuGuiState() {
		super(MenuState.MENU);
	}

	private static Inventory createInventory() {
		Inventory result = Bukkit.createInventory(null, 9, "MENU");
		ItemStack[] menu = createButtons();
		PersistentDataManager.setLink(menu[1], MENU_TRANSACTION_LINK);
		result.setContents(menu);
		return result;
	}

	private static void menuToTransactionChange(Player player) {
		GuiPlayerState state = Coinvestors.getManager().getStateOf(player);
		state.setStage(new TransactionGuiState());
		player.openInventory(state.getMenuInventory());
	}

	private static ItemStack[] createButtons() {
		return PersistentDataManager.asUnmodifiable(ItemStackCreator.fromMaterials(RED_WOOL, GREEN_WOOL, BROWN_WOOL));
	}

	@Override
	public Inventory build(final Player player) {
		return INVENTORY;
	}

}
