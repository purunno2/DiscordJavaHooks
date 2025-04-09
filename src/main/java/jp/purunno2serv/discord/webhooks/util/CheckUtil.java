package jp.purunno2serv.discord.webhooks.util;

public class CheckUtil {

	public static boolean checkEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}

		return true;
	}

}
