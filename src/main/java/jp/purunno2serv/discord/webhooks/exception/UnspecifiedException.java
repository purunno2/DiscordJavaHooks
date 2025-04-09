package jp.purunno2serv.discord.webhooks.exception;

public class UnspecifiedException extends RuntimeException {

	public UnspecifiedException(String indexName) {
		super(indexName + ": 値が未指定です。");
	}

}