package jp.purunno2serv.discord.webhooks.exception;

public class RequiredException extends RuntimeException {

	public RequiredException(String indexName) {
		super(indexName + ": 送信に必要な必須項目がセットされていません。");
	}

}