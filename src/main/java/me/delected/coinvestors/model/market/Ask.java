package me.delected.coinvestors.model.market;

import me.delected.coinvestors.model.wallet.Wallet;

public class Ask extends AbstractTransactionRequest implements Comparable<Ask> {

	public Ask(final double amount, final double prize, final Wallet.WalletUUID target) {
		super(amount, prize, target);
	}

	@Override
	public int compareTo(final Ask o) {
		return -Double.compare(this.getPrize(), o.getPrize());
	}
}
