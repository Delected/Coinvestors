package me.delected.coinvestors.menu.newmenu;

import org.bukkit.entity.Player;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;

public class WalletByTextInputGui extends WalletInputGui {

	public WalletByTextInputGui(final GuiStage next) {
		super(next);
	}

	@Override
	public void open(final Player player) {
		InputStageProvider.openStringInput(player,
				s -> setData(Coinvestors.accountService().getWalletByKey(s).orElse(null)),
				nextGui::build);
	}

}
