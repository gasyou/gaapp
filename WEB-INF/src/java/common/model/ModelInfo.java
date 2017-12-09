package common.model;

import java.util.HashMap;
import java.util.Map;

public class ModelInfo {

	/** 実行 Model クラス */
	private String className = null;

	/** 遷移先設定 */
	private Map<String, ForwardInfo> forwards = new HashMap<String, ForwardInfo>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * 遷移先を取得する.
	 * @param forward フォワードキー
	 * @return 遷移先
	 */
	public ForwardInfo getForward(String forward) {
		return forwards.get(forward);
	}

	/**
	 * 遷移先を追加する.
	 * @param forward フォワードキー
	 * @param forwardInfo 遷移情報
	 */
	void addForward(String forward, ForwardInfo forwardInfo) {
		forwards.put(forward, forwardInfo);
	}
}
