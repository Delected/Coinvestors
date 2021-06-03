package me.delected.coinvestors.menu.newmenu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

public class WalletInputGui extends InputGui<Wallet> {

	private static final String DEFAULT_TITLE = ChatColor.GREEN + "Select a wallet!";
	private static final int DEFAULT_SIZE = 36;

	private final String title;
	private final int size;

	public WalletInputGui(final GuiStage next) {
		this(DEFAULT_TITLE, DEFAULT_SIZE, next);
	}

	public WalletInputGui(final String title, final int size, final GuiStage next) {
		super(next);
		this.title = title;
		this.size = size;
	}

	@Override
	public void open(final Player player) {
		preBuild().dataSupplier(() -> Coinvestors.accountService().getWalletsOfPlayer(player)).buildAndOpen(player);
	}

	protected SelectionGui.Builder<Wallet> preBuild() {
		return new SelectionGui.Builder<Wallet>(title, size)
				.nextGui(nextGui)
				.consumer(this::setData)
				.renderer(RenderUtils::renderWalletNoAction);
	}

	@Override
	protected ItemStack presentStack(final String link) {
		return RenderUtils.renderWalletLink(getData(), link);
	}

	@Override
	protected ItemStack absentStack(final String link) {
		return RenderUtils.absentStack(link, "Click here to select the destination wallet!");
	}
}
