package me.delected.coinvestors.menu;

import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.debug.DebugUtils;
import me.delected.coinvestors.util.ItemStackCreator;
import me.delected.coinvestors.util.PersistentDataManager;

public class StringInputGuiStage extends GuiStage {

	private static final String BACK_LINK = "STRING_INPUT_BACK_LINK";

	static {
		MenuLinker.registerLink(BACK_LINK, StringInputGuiStage::openPreviousState);
	}

	private final MenuState previousState;
	private final Consumer<String> onInput;
	private final AnvilInventory inventory;


	public StringInputGuiStage(final MenuState previousState, final Consumer<String> onInputConfirm) {
		super(MenuState.STRING_INPUT);
		this.previousState = previousState;
		this.onInput = onInputConfirm;
		this.inventory = create();
	}

	private AnvilInventory create() {
		Inventory raw = Bukkit.createInventory(null, InventoryType.ANVIL);
		DebugUtils.inspectClass(raw.getClass());
		AnvilInventory result = (AnvilInventory) raw;
		result.setContents(ItemStackCreator.fromMaterials(Material.GLASS_PANE, Material.GLASS_PANE, Material.LIME_STAINED_GLASS_PANE));
		result.forEach(PersistentDataManager::setUnmodifiable);
		PersistentDataManager.setLink(result.getContents()[2], BACK_LINK);
		result.setRepairCost(0);
		return result;
	}

	@Override
	public Inventory build(final Player player) {
		return inventory;
	}

	public MenuState getPreviousState() {
		return previousState;
	}

	public String getCurrentText() {
		return inventory.getRenameText();
	}

	private static void openPreviousState(Player player) {
		GuiPlayerState state = Coinvestors.getManager().getStateOf(player);
		StringInputGuiStage stage = (StringInputGuiStage) state.getActualStage();
		stage.onInput.accept(stage.getCurrentText());
		state.changeToLastState(stage.previousState);
		player.openInventory(state.getMenuInventory());
	}
}
