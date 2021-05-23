package me.delected.coinvestors.color;

import java.awt.*;

public class GradientUtil {
    //TODO find out how to make gradient lol, something like ```arr[i] = ((256 * i) / arr.length) * 0x10000;```
    public static String[] createGradientArray(int size, Color from, Color to) {
        int fromRed = from.getRed();
        int fromGreen = from.getGreen();
        int fromBlue = from.getBlue();

        int toRed = to.getRed();
        int toGreen = to.getGreen();
        int toBlue = to.getBlue();

        return new String[]{};
    }

    public static String gradientString(String input, Color from, Color to) {
        StringBuilder sb = new StringBuilder();
        String[] gradient = createGradientArray(input.length(), from, to);
        char[] in = input.toCharArray();
        for (int i = 0; i < in.length; i++) {
            if (String.valueOf(in[i]).matches("^[a-zA-Z0-9$!.?,;]")) {
                --i;
                continue;
            }
            sb.append(gradient[i]).append(in[i]);
        }
        return sb.toString();
    }

}
