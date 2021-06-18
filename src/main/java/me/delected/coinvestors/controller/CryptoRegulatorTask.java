package me.delected.coinvestors.controller;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.currency.Crypto;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CryptoRegulatorTask extends BukkitRunnable {

    private static final String API_KEY = Coinvestors.instance().getConfig().getString("nomics_api_key");

    @Override
    public void run() {
        // TODO: read json request from getRequestUrl(), Crypto#setPrice for each
        // 			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //			String response = connection.getInputStream().toString();
        //			jsonObject = ObjectMapper.decode(response);
    }

    private static URL getRequestUrl() {
        try {
            return new URL(String.format(
                    "https://api.nomics.com/v1/currencies/ticker?key=%s&ids=%s" +
                            "&interval=none&convert=USD&per-page=100&page=1",
                    API_KEY, createRequestIdArgument()
                )
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String createRequestIdArgument() {
        return Arrays.stream(Crypto.values())
                .map(Crypto::name)
                .collect(Collectors.joining(","));
    }
}
