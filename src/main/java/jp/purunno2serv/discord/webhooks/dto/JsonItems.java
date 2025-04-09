package jp.purunno2serv.discord.webhooks.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.purunno2serv.discord.webhooks.exception.PatternNotMatchException;
import jp.purunno2serv.discord.webhooks.values.Color;

public class JsonItems {

	private String userName = null;
	private String avatarUrl = null;
	private String content = null;
	private String title = null;
	private String description = null;
	private String url = null;
	private String color = null;
	private String imageUrl = null;
	private String thumbnailUrl = null;
	private String autherName = null;
	private String autherUrl = null;
	private String autherIconUrl = null;
	private List<Fields> fields = new ArrayList<Fields>();
	private String footerText = null;
	private String footerIconUrl = null;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setColor(String color) {
		color = color.replaceAll("\s", "");

		String decColor = "";
		String tmp = "";

		Pattern rgb = Pattern.compile(Color.PATTERN_RGB);
		Pattern dec = Pattern.compile(Color.PATTERN_DEC);
		Pattern hex6 = Pattern.compile(Color.PATTERN_HEX6);

		Matcher matchRGB = rgb.matcher(color);
		Matcher matchDEC = dec.matcher(color);
		Matcher matchHEX6 = hex6.matcher(color);

		if (matchRGB.matches()) {
			String[] rgbArray = color.split(",");

			for (int i = 0; rgbArray.length > i; i++) {
				if (Integer.parseInt(rgbArray[i]) <= Color.RGB_MAX) {
					tmp += Integer.toHexString(Integer.parseInt(rgbArray[i]));
				} else {
					throw new PatternNotMatchException("COLOR_PATTERN");
				}
			}

			tmp = String.valueOf(Integer.parseInt(tmp, Color.HEX));
		} else if (matchHEX6.matches()) {
			tmp = String.valueOf(Integer.parseInt(color.replace("#", ""), Color.HEX));
		} else if (matchDEC.matches()) {
			if (Integer.parseInt(color) <= Color.DEC_MAX) {
				tmp = color;
			} else {
				throw new PatternNotMatchException("COLOR_PATTERN");
			}
		} else {
			throw new PatternNotMatchException("COLOR_PATTERN");
		}

		decColor = tmp;

		this.color = decColor;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}

	public void setAutherUrl(String autherUrl) {
		this.autherUrl = autherUrl;
	}

	public void setAutherIconUrl(String autherIconUrl) {
		this.autherIconUrl = autherIconUrl;
	}

	public void setFields(List<Fields> fields) {
		this.fields.addAll(fields);
	}

	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}

	public void setFooterIconUrl(String footerIconUrl) {
		this.footerIconUrl = footerIconUrl;
	}

	public String getUserName() {
		return userName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getColor() {
		return color;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public String getAutherName() {
		return autherName;
	}

	public String getAutherUrl() {
		return autherUrl;
	}

	public String getAutherIconUrl() {
		return autherIconUrl;
	}

	public List<Fields> getFields() {
		return fields;
	}

	public String getFooterText() {
		return footerText;
	}

	public String getFooterIconUrl() {
		return footerIconUrl;
	}
}
