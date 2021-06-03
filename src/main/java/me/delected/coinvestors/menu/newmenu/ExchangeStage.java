package me.delected.coinvestors.menu.newmenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import me.delected.coinvestors.model.accounts.AccountService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

/* Todo summary: create links and static methods like the already
    existing one, put the right ones in the ItemStack creation methods, register the links,
       call the exchange method of the AccountService in the confirmAction method,
       Finally, remove the todos ;)
 */
public class ExchangeStage extends ReturningGuiStage implements Confirmable {
	//CONSTANTS
	private static final String TITLE = ChatColor.YELLOW + "Exchange crypto";
	private static final int SIZE = 36;

	private static final String SOURCE_CRYPTO_INPUT_LINK = "EXCHANGE_SOURCE_C_INPUT";
	private static final String TARGET_CRYPTO_INPUT_LINK = "EXCHANGE_SOURCE_S_TARGET";

	static {
		MenuLinker.registerLink(SOURCE_CRYPTO_INPUT_LINK, ExchangeStage::openSourceCryptoInput);
		MenuLinker.registerLink(TARGET_CRYPTO_INPUT_LINK, ExchangeStage::openTargetCryptoInput);
	}

	//INPUT PROVIDERS
	private final CryptoInputGui sourceCryptoInputGui = new CryptoInputGui(this);
	private final CryptoInputGui targetCryptoInputGui = new CryptoInputGui(this);
	private final AmountInputGui amountInputGui = new AmountInputGui(this);
	private final RestrictedInputGui<Wallet> sourceWalletInputGui;
	private final RestrictedInputGui<Wallet> targetWalletInputGui;

	//Constructor
	protected ExchangeStage(GuiStage previous) {
		super(MenuState.EXCHANGE, previous);
		//Init of the restricted guis with underlying WalletInputGuis and dependency on the regarding crypto input
		WalletInputGui sourceWalletInputGui = new WalletByCryptoInputGui(this, sourceCryptoInputGui::getData);
		this.sourceWalletInputGui = new RestrictedInputGui<>(this, sourceWalletInputGui, sourceCryptoInputGui);
		WalletInputGui targetWalletInputGui = new WalletByCryptoInputGui(this, sourceCryptoInputGui::getData);
		this.targetWalletInputGui = new RestrictedInputGui<>(this, targetWalletInputGui, sourceCryptoInputGui);

	}

	//TODO: put the created link strings on their place
	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, SIZE, TITLE);
		result.setItem(11, sourceCryptoInputGui.getInfoStack(SOURCE_CRYPTO_INPUT_LINK));
		result.setItem(15, targetCryptoInputGui.getInfoStack(TARGET_CRYPTO_INPUT_LINK));
		result.setItem(20, sourceWalletInputGui.getInfoStack("null"));
		result.setItem(22, amountInputGui.getInfoStack("null"));
		result.setItem(24, targetWalletInputGui.getInfoStack("null"));
		result.setItem(34, confirmStack(CONFIRM_LINK));
		ItemStack rs = returnStack(ChatColor.RED + "Return to previous menu",
				ChatColor.WHITE + "Aborts the exchange");
		result.setItem(35, rs);
		return result;
	}

	@Override
	public List<String> invalids() {
		List<String> invalids = new ArrayList<>();
		if (sourceCryptoInputGui.getData() == null) {
			invalids.add("Source crypto");
		}
		if (sourceWalletInputGui.getData() == null) {
			invalids.add("Source wallet");
		}
		if (amountInputGui.getData() == null) {
			invalids.add("transaction amount");
		}
		if (targetCryptoInputGui.getData() == null) {
			invalids.add("Destination crypto");
		}
		if (targetWalletInputGui.getData() == null) {
			invalids.add("Destination wallet");
		}
		return invalids;
	}

	//helper method which gets the instance of this class the player is using at the moment
	private static ExchangeStage getGui(Player player) {
		return actualStage(player, ExchangeStage.class);
	}

	private static void openSourceCryptoInput(Player player) {
		getGui(player).sourceCryptoInputGui.open(player);
	}

	private static void openTargetCryptoInput(Player player) {
		getGui(player).targetCryptoInputGui.open(player);
	}

	//TODO: Perform the exchange using the AccountService
	@Override
	public Consumer<Player> confirmAction() {
		return p -> {
			new AccountService().exchangeCryptoIfPossible(p, sourceWalletInputGui.getData(), amountInputGui.getData(), targetWalletInputGui.getData());
		};
	}

	//Checks if all input is done
	@Override
	public boolean isValid() {
		return Stream.of(sourceWalletInputGui, targetWalletInputGui, amountInputGui,
				sourceCryptoInputGui, targetCryptoInputGui).noneMatch(i -> Objects.isNull(i.getData()));
	}
}
