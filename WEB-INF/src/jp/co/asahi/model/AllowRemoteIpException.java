package jp.co.asahi.model;

public class AllowRemoteIpException extends Exception {
	/**
	 * デフォルトのシリアルバージョン
	 */
	private static final long serialVersionUID = 1L;

	protected String message = "";

	public AllowRemoteIpException() {
	}

	public AllowRemoteIpException(Throwable e) {
		super(e);
	}

	public AllowRemoteIpException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public String toString() {
		return "AllowRemoteIp Exception: " + message;
	}
}