package me.delected.coinvestors.model.wallet.address;

import me.delected.coinvestors.io.StorageUtils;
import me.delected.coinvestors.model.wallet.Wallet;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public interface AddressGenerator {
	byte[] generate();
}
