package me.delected.coinvestors.controller;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CryptoRegulator {
	//fixme: bind the functionality to an object;
	private final boolean foo = false;

	//todo
	public void start() {

	}

	//closes the crypto market
	public void stop(){

	}

	public static void main(String[] args) {
		System.out.println(recalculatePrices());
	}

	public static Optional<Object[]> recalculatePrices() {
		ObjectMapper.JsonObject jsonObject;
		try {
			URL url = new URL("https://api.nomics.com/v1/currencies/ticker?key=4777d99b1e3d2b5dc28f496a8791a214&ids=" + createRequestArgument() + "&interval=none&convert=USD&per-page=100&page=1");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String response = connection.getInputStream().toString();
			jsonObject = ObjectMapper.decode(response);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

		return jsonObject.getArray();


	}

	private static String createRequestArgument() {
		return Arrays.stream(Crypto.values())
				.map(Crypto::name)
				.collect(Collectors.joining(","));
	}
}
