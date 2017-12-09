package common.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceConfig {

	//------------------------------
	// Property keys
	//------------------------------
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";

	/** 設定パラメーター */
	private Map<String, String> params = new HashMap<String, String>();

	/** Service 設定インスタンス */
	private static ServiceConfig instance = new ServiceConfig();


	/**
	 * Service 設定を取得する.
	 * @param initParams 初期化パラメーター
	 * @return Service 設定
	 */
	public static ServiceConfig initialize(Map<String, String> initParams) {
		instance.params = initParams;
		return instance;
	}

	/**
	 * Service 設定を取得する.
	 * @return Service 設定
	 */
	public static ServiceConfig getConfig() {
		return instance;
	}

	/**
	 * 設定パラメーターを取得する.
	 * @param key 設定パラメーターのキー
	 */
	public String get(String key) {
		return params.get(key).toString();
	}
}
