package me.delected.coinvestors.exceptions;

public class WalletUUIDAlreadyInUseException extends IllegalArgumentException {
	public WalletUUIDAlreadyInUseException() {
		super("The given Wallet Address String was already in use! Check source file for duplicates!");
	}
}
