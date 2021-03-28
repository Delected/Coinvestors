package me.delected.coinvestors.util;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}