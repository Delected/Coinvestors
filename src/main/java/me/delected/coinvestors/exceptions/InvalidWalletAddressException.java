package me.delected.coinvestors.exceptions;

public class InvalidWalletAddressException extends IllegalArgumentException {

	public InvalidWalletAddressException() {
		super("Invalid wallet address format");
	}
}
