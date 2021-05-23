package me.delected.coinvestors.color;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * used for converting strings and hex color codes into colored strings
 * <p>
 * credits to https://www.spigotmc.org/threads/1-16-1-new-color-utils-rgb-and-hex-parsing.451068/
 */
public class ChatUtil {
	private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");


	public static String color(String s) {
		if (s == null || s.equals("")) {
			return "";
		}
		Builder builder = new Builder();
		Matcher matcher = HEX_PATTERN.matcher(s);

		while (matcher.find()) {
			builder.text(s.substring(0, matcher.start()));
			builder.hex(matcher.group().substring(1, matcher.group().length() - 1));
			matcher = HEX_PATTERN.matcher(s.substring(matcher.end()));
		}
		return builder.build();
	}

	public static class Builder {

		private static final char ALT_COLOR_CHAR = '&';

		private final StringBuilder stringBuilder = new StringBuilder();

		public Builder hex(String hex) {
			stringBuilder.append(fromHex(hex));
			return this;
		}

		private ChatColor fromHex(String hex) {
			return ChatColor.of(hex);
		}

		public Builder cColor(ChatColor color) {
			stringBuilder.append(color);
			return this;
		}

		public Builder text(String text) {
			stringBuilder.append(text);
			return this;
		}

		public String build() {
			return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR, stringBuilder.toString());
		}
	}

}