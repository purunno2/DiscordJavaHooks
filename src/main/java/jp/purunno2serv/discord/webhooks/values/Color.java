package jp.purunno2serv.discord.webhooks.values;

public class Color {

	public static final String PATTERN_RGB = "[0-9]{1,3},[0-9]{1,3},[0-9]{1,3}";

	public static final String PATTERN_DEC = "[0-9]+";

	public static final String PATTERN_HEX6 = "#?[a-z,A-Z,0-9]{6}";

	public static final int HEX = 16;

	public static final int DEC_MAX = 16777215;

	public static final int RGB_MAX = 255;
}