package me.delected.coinvestors.model.currency;

public class ExchangePair {
	private final Crypto major;
	private final Crypto minor;

	public ExchangePair(final Crypto major, final Crypto minor) {
		this.major = major;
		this.minor = minor;
	}

	public Crypto getMajor() {
		return major;
	}

	public Crypto getMinor() {
		return minor;
	}
}
