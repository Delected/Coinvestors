package me.delected.coinvestors.model.wallet;

public interface Wallet {

	WalletAddress getAddress();

	boolean withdraw(double amount);

	void deposit(double amount);
}
