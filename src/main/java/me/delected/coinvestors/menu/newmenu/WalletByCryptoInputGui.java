package me.delected.coinvestors.menu.newmenu;

import java.util.function.Supplier;

import org.bukkit.entity.Player;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.currency.Crypto;

public class WalletByCryptoInputGui extends WalletInputGui {

	private final Supplier<Crypto> cryptoSupplier;

	public WalletByCryptoInputGui(final GuiStage next, final Supplier<Crypto> cryptoSupplier) {
		super(next);
		this.cryptoSupplier = cryptoSupplier;
	}

	public WalletByCryptoInputGui(final String title, final int size, final GuiStage next,
								  final Supplier<Crypto> cryptoSupplier) {
		super(title, size, next);
		this.cryptoSupplier = cryptoSupplier;
	}

	@Override
	public void open(final Player player) {
		preBuild().dataSupplier(() -> Coinvestors.accountService().getWalletsOfPlayer(player, cryptoSupplier.get()))
				.buildAndOpen(player);
	}
}
