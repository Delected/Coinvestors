package me.delected.coinvestors.model.wallet.address;

public class Ethereum implements AddressGenerator {
    @Override
    public byte[] generate() {
        byte[] bytes = new byte[42];
        // 0x
        bytes[0] = Byte.parseByte("0");
        bytes[1] = Byte.parseByte("x");
        // generate random string 40 char long, 0-9a-fA-F


        return bytes;
    }
}
