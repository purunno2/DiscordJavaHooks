package jp.purunno2serv.discord.webhooks.dto;

public class Fields {

	String name = null;
	String value = null;
	boolean inline = true;

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setInline(boolean inline) {
		this.inline = inline;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean getInline() {
		return inline;
	}
}