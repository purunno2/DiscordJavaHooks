package jp.purunno2serv.discord.webhooks;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import jp.purunno2serv.discord.webhooks.exception.PatternNotMatchException;
import jp.purunno2serv.discord.webhooks.exception.RequiredException;
import jp.purunno2serv.discord.webhooks.exception.UnspecifiedException;
import jp.purunno2serv.discord.webhooks.main.Webhooks;

class WebhooksTest {

	final String API = "";

	@Test
	void test01() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setUserName("test01");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test02() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAvatarUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test03() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setContent("test03");
		assertEquals(204, wh.execute());
	}

	@Test
	void test04() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setTitle("test04");
		assertEquals(204, wh.execute());
	}

	@Test
	void test05() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setDescription("test05");
		assertEquals(204, wh.execute());
	}

	@Test
	void test06() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test07() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setColor("#737cf8");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test08() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setFooterText("test08");
		assertEquals(204, wh.execute());
	}

	@Test
	void test09() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setFooterIconUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test10() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setThumbnailUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		assertEquals(204, wh.execute());
	}

	@Test
	void test11() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAutherName("test11");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test12() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAutherUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test13() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAutherIconUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		RequiredException e = assertThrows(RequiredException.class, () -> {
			wh.execute();
		});
		assertEquals("VALIDATE: 送信に必要な必須項目がセットされていません。", e.getMessage());
	}

	@Test
	void test14() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setImagelUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		assertEquals(204, wh.execute());
	}

	@Test
	void test15() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setFields("test15name1", "test15value1", true);
		wh.setFields("test15name2", "test15value2", true);
		wh.setFields("test15name3", "test15value3", true);
		wh.setFields("test15name4", "test15value4", true);
		assertEquals(204, wh.execute());
	}

	@Test
	void test16() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setColor("86, 70, 205");
		wh.setTitle("test16");
		assertEquals(204, wh.execute());
	}

	@Test
	void test17() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setColor("16751796");
		wh.setTitle("test17");
		assertEquals(204, wh.execute());
	}

	@Test
	void test18() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setFields("test18name1", "test18value1", true);
		wh.setFields("test18name2", "test18value2", true);
		wh.setFields("test18name3", "test18value3", true);
		wh.setFields("test18name4", "test18value4", false);
		wh.setFields("test18name5", "test18value5", true);
		wh.setFields("test18name6", "test18value6", true);
		wh.setFields("test18name7", "test18value7", false);
		wh.setFields("test18name8", "test18value8", true);
		wh.setFields("test18name9", "test18value9", false);
		assertEquals(204, wh.execute());
	}

	@Test
	void test19() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAutherUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setAutherName("test19");
		assertEquals(204, wh.execute());
	}

	@Test
	void test20() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setUserName("test20");
		wh.setContent("test20");
		assertEquals(204, wh.execute());
	}

	@Test
	void test21() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setUserName("test21");
		wh.setTitle("test21");
		assertEquals(204, wh.execute());
	}

	@Test
	void test22() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAvatarUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setContent("test22");
		assertEquals(204, wh.execute());
	}

	@Test
	void test23() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setAvatarUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setTitle("test23");
		assertEquals(204, wh.execute());
	}

	@Test
	void test24() throws IOException {
		UnspecifiedException e = assertThrows(UnspecifiedException.class, () -> {
			Webhooks wh = new Webhooks("");
			wh.setContent("test24");
			wh.execute();
		});
		assertEquals("API_URL: 値が未指定です。", e.getMessage());
	}

	@Test
	void test25() throws IOException {
		PatternNotMatchException e = assertThrows(PatternNotMatchException.class, () -> {
			Webhooks wh = new Webhooks(API);
			wh.setTitle("test25");
			wh.setColor("-1");
			wh.execute();
		});
		assertEquals("COLOR_PATTERN: マッチするパターンがありませんでした。", e.getMessage());
	}

	@Test
	void test26() throws IOException {
		PatternNotMatchException e = assertThrows(PatternNotMatchException.class, () -> {
			Webhooks wh = new Webhooks(API);
			wh.setTitle("test26");
			wh.setColor("16777216");
			wh.execute();
		});
		assertEquals("COLOR_PATTERN: マッチするパターンがありませんでした。", e.getMessage());
	}

	@Test
	void test27() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setTitle("test27");
		wh.setColor("0");
		assertEquals(204, wh.execute());
	}

	@Test
	void test28() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setTitle("test28");
		wh.setColor("16777215");
		assertEquals(204, wh.execute());
	}

	@Test
	void test29() throws IOException {
		PatternNotMatchException e = assertThrows(PatternNotMatchException.class, () -> {
			Webhooks wh = new Webhooks(API);
			wh.setTitle("test29");
			wh.setColor("-1, -1, -1");
			wh.execute();
		});
		assertEquals("COLOR_PATTERN: マッチするパターンがありませんでした。", e.getMessage());
	}

	@Test
	void test30() throws IOException {
		PatternNotMatchException e = assertThrows(PatternNotMatchException.class, () -> {
			Webhooks wh = new Webhooks(API);
			wh.setTitle("test30");
			wh.setColor("256, 256, 256");
			wh.execute();
		});
		assertEquals("COLOR_PATTERN: マッチするパターンがありませんでした。", e.getMessage());
	}

	@Test
	void test31() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setTitle("test31");
		wh.setColor("0, 0, 0");
		assertEquals(204, wh.execute());
	}

	@Test
	void test32() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setTitle("test32");
		wh.setColor("255, 255, 255");
		assertEquals(204, wh.execute());
	}

	@Test
	void test33() throws IOException {
		Webhooks wh = new Webhooks(API);
		wh.setUserName("Noni");
		wh.setAvatarUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setContent("I made DiscordJavaHooks!");
		wh.setTitle("DiscordJavaHooks");
		wh.setDescription("DiscordWebhooks can be easily run in Java");
		wh.setUrl("https://javahooks.purunno2-serv.jp");
		wh.setColor("#5646cd");
		wh.setFooterText("© 2025 Noni.");
		wh.setFooterIconUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setImagelUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setThumbnailUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setAutherName("@purunno2");
		wh.setAutherUrl("https://x.com/purunno2");
		wh.setAutherIconUrl("https://avatars.githubusercontent.com/u/69942251?v=4");
		wh.setFields("使用言語", "Java", true);
		wh.setFields("公開プラットフォーム", "Github", true);
		assertEquals(204, wh.execute());
	}
}
