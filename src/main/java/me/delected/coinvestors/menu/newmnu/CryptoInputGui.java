package me.delected.coinvestors.menu.newmnu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public class CryptoInputGui extends InputGui<Crypto> {

	private static final String DEFAULT_TITLE = ChatColor.GOLD + "Select a currency!";
	private static final int DEFAULT_SIZE = 36;

	private final String title;
	private final int size;

	public CryptoInputGui(final GuiStage prev, final String title, final int size) {
		super(prev);
		this.title = title;
		this.size = size;
	}

	public CryptoInputGui(final GuiStage prev) {
		this(prev, DEFAULT_TITLE, DEFAULT_SIZE);
	}

	@Override
	public void open(final Player player) {
		new SelectionGui.Builder<Crypto>(title, size).consumer(this::setData).nextGui(prev)
				.dataSupplier(Crypto::valueList).renderer(CryptoInputGui::cryptoInfoStack)
				.buildAndOpen(player);
	}

	private static ItemStackCreator cryptoStackCreator(Crypto c) {
		return new ItemStackCreator(Material.SUNFLOWER)
				.setName(ChatColor.BLUE + c.getFullName())
				.setLore(ChatColor.YELLOW + "Actual value: " + c.getPrice());
	}

	private static ItemStack cryptoInfoStack(Crypto c) {
		return cryptoStackCreator(c).build();
	}

	@Override
	protected final ItemStack presentStack(String link) {
		return cryptoStackCreator(getData()).setLink(link).build();
	}

	@Override
	protected final ItemStack absentStack(String link) {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.RED + "Select a currency!").build();
	}
}
