package common.model;

/**
 * 遷移先情報
 */
public class ForwardInfo {

	/** 遷移先情報 */
	private String forwardPath = null;

	/** リダイレクト */
	private boolean redirect = false;

	public ForwardInfo(String forwardPath, boolean redirect) {
		this.forwardPath = forwardPath;
		this.redirect = redirect;
	}

	public String getForwardPath() {
		return forwardPath;
	}

	public boolean isRedirect() {
		return redirect;
	}
}
