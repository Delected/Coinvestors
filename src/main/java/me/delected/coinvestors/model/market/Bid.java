package me.delected.coinvestors.model.market;

public class Bid extends AbstractTransactionRequest implements Comparable<Bid> {

	public Bid(final double amount, final double prize, final Wallet.WalletUUID target) {
		super(amount, prize, target);
	}

	@Override
	public int compareTo(final Bid o) {
		return Double.compare(this.getPrize(), o.getPrize());
	}
}
