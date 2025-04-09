package jp.purunno2serv.discord.webhooks.exception;

public class PatternNotMatchException extends RuntimeException {

	public PatternNotMatchException(String indexName) {
		super(indexName + ": マッチするパターンがありませんでした。");
	}

}