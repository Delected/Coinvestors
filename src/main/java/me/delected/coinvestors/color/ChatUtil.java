package me.delected.coinvestors.color;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * used for converting strings and hex color codes into colored strings
 *
 * credits to https://www.spigotmc.org/threads/1-16-1-new-color-utils-rgb-and-hex-parsing.451068/
 */
public class ChatUtil {
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    public static String color(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        Matcher matcher = HEX_PATTERN.matcher(s);

        while (matcher.find()) {
            final ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String before = s.substring(0, matcher.start());
            final String after = s.substring(matcher.end());
            s = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(s);
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}