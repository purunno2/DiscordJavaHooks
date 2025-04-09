package jp.purunno2serv.discord.webhooks.main;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.purunno2serv.discord.webhooks.dto.Fields;
import jp.purunno2serv.discord.webhooks.dto.JsonItems;
import jp.purunno2serv.discord.webhooks.exception.RequiredException;
import jp.purunno2serv.discord.webhooks.exception.UnspecifiedException;
import jp.purunno2serv.discord.webhooks.util.CheckUtil;
import jp.purunno2serv.discord.webhooks.values.JsonBuilder;
import jp.purunno2serv.discord.webhooks.values.JsonContextName;

/**
 * DiscordAPIを用いて、メッセージを送信するクラス<br>
 * インスタンスを作成して、引数にDiscordAPIのURLを指定<br>
 * ※必須項目：Content（チャット本文）
 */
public class Webhooks {

	private String apiUrl = null;

	public Webhooks(String apiUrl) {
		if (!CheckUtil.checkEmpty(apiUrl)) {
			throw new UnspecifiedException("API_URL");
		}

		this.apiUrl = apiUrl;
	}

	JsonItems jsonItemsDto = new JsonItems();

	List<Fields> fieldsList;
	Fields fieldsDto;

	boolean embeded = false;
	boolean requiredContent = false;
	boolean requiredEmbeds = false;
	boolean requiredAuther = false;

	/**
	 * ユーザー名を設定
	 * @param userName
	 */
	public void setUserName(String userName) {
		jsonItemsDto.setUserName(userName);
	}

	/**
	 * ユーザーアイコンを設定（URL）
	 * @param avatarUrl
	 */
	public void setAvatarUrl(String avatarUrl) {
		jsonItemsDto.setAvatarUrl(avatarUrl);
	}

	/**
	 * 【必須】チャット本文を設定
	 * @param content
	 */
	public void setContent(String content) {
		jsonItemsDto.setContent(content);
		requiredContent = true;
	}

	/**
	 * 埋め込みのタイトルを設定
	 * @param title
	 */
	public void setTitle(String title) {
		jsonItemsDto.setTitle(title);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みの概要文を設定
	 * @param description
	 */
	public void setDescription(String description) {
		jsonItemsDto.setDescription(description);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みのウェブサイトを設定（URL)
	 * @param url
	 */
	public void setUrl(String url) {
		jsonItemsDto.setUrl(url);
		embeded = true;
	}

	/**
	 * 埋め込みの色を設定
	 * 指定形式：
	 *  RGB（例：54,200,235）
	 *  16進数（例：#055df7 #F26E02）
	 *  10進数（例：16751796）
	 * @param color
	 */
	public void setColor(String color) {
		jsonItemsDto.setColor(color);
		embeded = true;
	}

	/**
	 * 埋め込みの画像を設定（URL）
	 * @param imageUrl
	 */
	public void setImagelUrl(String imageUrl) {
		jsonItemsDto.setImageUrl(imageUrl);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みのサムネイルを設定（URL）
	 * @param thumbnailUrl
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		jsonItemsDto.setThumbnailUrl(thumbnailUrl);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みの著者名を設定
	 * @param autherName
	 */
	public void setAutherName(String autherName) {
		jsonItemsDto.setAutherName(autherName);
		embeded = true;

		if (CheckUtil.checkEmpty(jsonItemsDto.getAutherUrl())) {
			requiredAuther = true;
		}
	}

	/**
	 * 埋め込みの著者のウェブサイトを設定（URL）
	 * @param autherUrl
	 */
	public void setAutherUrl(String autherUrl) {
		jsonItemsDto.setAutherUrl(autherUrl);
		embeded = true;

		if (CheckUtil.checkEmpty(jsonItemsDto.getAutherName())) {
			requiredAuther = true;
		}
	}

	/**
	 * 埋め込みの著者のアイコンを設定（URL）
	 * @param autherIconUrl
	 */
	public void setAutherIconUrl(String autherIconUrl) {
		jsonItemsDto.setAutherIconUrl(autherIconUrl);
		embeded = true;
	}

	/**
	 * 埋め込みのフィールドを設定
	 * @param fieldsName
	 * @param fieldsValue
	 * @param fieldsInline
	 */
	public void setFields(String fieldsName, String fieldsValue, boolean fieldsInline) {
		fieldsList = new ArrayList<Fields>();
		fieldsDto = new Fields();

		fieldsDto.setName(fieldsName);
		fieldsDto.setValue(fieldsValue);
		if (fieldsInline) {
			fieldsDto.setInline(true);
		} else {
			fieldsDto.setInline(false);
		}

		fieldsList.add(fieldsDto);
		jsonItemsDto.setFields(fieldsList);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みのフッター文を設定
	 * @param footerText
	 */
	public void setFooterText(String footerText) {
		jsonItemsDto.setFooterText(footerText);
		embeded = true;
		requiredEmbeds = true;
	}

	/**
	 * 埋め込みのフッターアイコンを設定
	 * @param footerIconUrl
	 */
	public void setFooterIconUrl(String footerIconUrl) {
		jsonItemsDto.setFooterIconUrl(footerIconUrl);
		embeded = true;
	}

	/**
	 * Webhooksを実行
	 * @return HTTPステータス
	 * @throws IOException
	 */
	public int execute() throws IOException {
		if (!validate()) {
			throw new RequiredException("VALIDATE");
		}

		URL url = new URL(apiUrl);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("POST");
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		http.connect();

		// 書き込み
		PrintStream ps = new PrintStream(http.getOutputStream());
		ps.print(jsonBuild());
		ps.close();

		// 送信成功:204
		return http.getResponseCode();
	}

	private String jsonBuild() {

		String json = "";

		json += JsonBuilder.CURLY_BRACKETS_LEFT;

		if (CheckUtil.checkEmpty(jsonItemsDto.getUserName())) {
			json += jsonContext(JsonContextName.USERNAME, jsonItemsDto.getUserName());
			json += JsonBuilder.COMMA;
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getAvatarUrl())) {
			json += jsonContext(JsonContextName.AVATAR_URL, jsonItemsDto.getAvatarUrl());
			json += JsonBuilder.COMMA;
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getContent())) {
			json += jsonContext(JsonContextName.CONTENT, jsonItemsDto.getContent());
			json += JsonBuilder.COMMA;
		}

		if (embeded) {
			json += JsonBuilder.DBL_QTTN + JsonContextName.EMBEDS + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
					+ JsonBuilder.SQUARE_BRACKETS_LEFT;
			json += JsonBuilder.CURLY_BRACKETS_LEFT;

			if (CheckUtil.checkEmpty(jsonItemsDto.getTitle())) {
				json += jsonContext(JsonContextName.TITLE, jsonItemsDto.getTitle());
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getDescription())) {
				json += jsonContext(JsonContextName.DESCRIPTION, jsonItemsDto.getDescription());
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getUrl())) {
				json += jsonContext(JsonContextName.URL, jsonItemsDto.getUrl());
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getColor())) {
				json += jsonContext(JsonContextName.COLOR, jsonItemsDto.getColor());
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getFooterText())) {
				json += JsonBuilder.DBL_QTTN + JsonContextName.FOOTER + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
						+ JsonBuilder.CURLY_BRACKETS_LEFT;

				json += jsonContext(JsonContextName.TEXT, jsonItemsDto.getFooterText());

				json += JsonBuilder.COMMA;

				if (CheckUtil.checkEmpty(jsonItemsDto.getFooterIconUrl())) {
					json += jsonContext(JsonContextName.ICON_URL, jsonItemsDto.getFooterIconUrl());
				}

				json += JsonBuilder.CURLY_BRACKETS_RIGHT;
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getThumbnailUrl())) {
				json += JsonBuilder.DBL_QTTN + JsonContextName.THUMBNAIL + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
						+ JsonBuilder.CURLY_BRACKETS_LEFT;
				json += jsonContext(JsonContextName.URL, jsonItemsDto.getThumbnailUrl());
				json += JsonBuilder.CURLY_BRACKETS_RIGHT;
				json += JsonBuilder.COMMA;
			}

			if (requiredAuther) {
				json += JsonBuilder.DBL_QTTN + JsonContextName.AUTHER + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
						+ JsonBuilder.CURLY_BRACKETS_LEFT;

				json += jsonContext(JsonContextName.NAME, jsonItemsDto.getAutherName());
				json += JsonBuilder.COMMA;

				json += jsonContext(JsonContextName.URL, jsonItemsDto.getAutherUrl());
				json += JsonBuilder.COMMA;

				if (CheckUtil.checkEmpty(jsonItemsDto.getAutherIconUrl())) {
					json += jsonContext(JsonContextName.ICON_URL, jsonItemsDto.getAutherIconUrl());
				}

				json += JsonBuilder.CURLY_BRACKETS_RIGHT;
				json += JsonBuilder.COMMA;
			}

			if (CheckUtil.checkEmpty(jsonItemsDto.getImageUrl())) {
				json += JsonBuilder.DBL_QTTN + JsonContextName.IMAGE + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
						+ JsonBuilder.CURLY_BRACKETS_LEFT;
				json += jsonContext(JsonContextName.URL, jsonItemsDto.getImageUrl());
				json += JsonBuilder.CURLY_BRACKETS_RIGHT;
				json += JsonBuilder.COMMA;
			}

			if (jsonItemsDto.getFields().size() != 0) {
				json += JsonBuilder.DBL_QTTN + JsonContextName.FIELDS + JsonBuilder.DBL_QTTN + JsonBuilder.COLON
						+ JsonBuilder.SQUARE_BRACKETS_LEFT;

				for (int i = 0; jsonItemsDto.getFields().size() > i; i++) {
					json += JsonBuilder.CURLY_BRACKETS_LEFT;
					json += jsonContext(JsonContextName.NAME, jsonItemsDto.getFields().get(i).getName());
					json += JsonBuilder.COMMA;
					json += jsonContext(JsonContextName.VALUE, jsonItemsDto.getFields().get(i).getValue());
					json += JsonBuilder.COMMA;

					json += JsonBuilder.DBL_QTTN + JsonContextName.INLINE + JsonBuilder.DBL_QTTN + JsonBuilder.COLON;
					if (jsonItemsDto.getFields().get(i).getInline()) {
						json += "true";
					} else {
						json += "false";
					}

					json += JsonBuilder.CURLY_BRACKETS_RIGHT;

					if (jsonItemsDto.getFields().size() - 1 > i) {
						json += JsonBuilder.COMMA;
					}
				}

				json += JsonBuilder.SQUARE_BRACKETS_RIGHT;
			}

			json += JsonBuilder.CURLY_BRACKETS_RIGHT;
			json += JsonBuilder.SQUARE_BRACKETS_RIGHT;
		}

		json += JsonBuilder.CURLY_BRACKETS_RIGHT;

		json = json.replace(JsonBuilder.COMMA + JsonBuilder.CURLY_BRACKETS_RIGHT, JsonBuilder.CURLY_BRACKETS_RIGHT);

		return json;
	}

	private String jsonContext(String ctxName, String ctxValue) {
		return JsonBuilder.DBL_QTTN + ctxName + JsonBuilder.DBL_QTTN + JsonBuilder.COLON + JsonBuilder.DBL_QTTN
				+ ctxValue + JsonBuilder.DBL_QTTN;
	}

	private boolean validate() {
		if (CheckUtil.checkEmpty(jsonItemsDto.getUserName())) {
			if (!requiredContent && !requiredEmbeds) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getAvatarUrl())) {
			if (!requiredContent && !requiredEmbeds) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getUrl())) {
			if (!requiredEmbeds) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getColor())) {
			if (!requiredEmbeds) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getFooterIconUrl())) {
			if (!CheckUtil.checkEmpty(jsonItemsDto.getFooterText())) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getAutherName())) {
			if (!requiredAuther) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getAutherUrl())) {
			if (!requiredAuther) {
				return false;
			}
		}

		if (CheckUtil.checkEmpty(jsonItemsDto.getAutherIconUrl())) {
			if (!requiredAuther) {
				return false;
			}
		}

		return true;
	}
}
