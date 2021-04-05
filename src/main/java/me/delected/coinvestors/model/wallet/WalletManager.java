package me.delected.coinvestors.model.wallet;

import java.security.*;
import java.util.Base64;

public class WalletManager {
    public static String[] generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        Base64.Encoder encoder = Base64.getEncoder();

        String publicKey = encoder.encodeToString(pub.getEncoded()).substring(0, 64);
        String privateKey = encoder.encodeToString(priv.getEncoded()).substring(0, 64);

        return new String[]{privateKey, publicKey};
    }
}
