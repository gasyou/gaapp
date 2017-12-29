package com.gasyou.gam.common;
import java.util.HashMap;
import java.util.Map;

public class Row {
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 値を設定する.
	 * @param key キー 
	 * @param value 値
	 */
	public void put(String key, Object value) {
		data.put(key, value);
	}

	/**
	 * 値を取得する.
	 * @param key キー
	 * @param defaultValue 値が null の場合のデフォルト値
	 * @return 値
	 */
	public <T> T get(String key, T defaultValue) {
		T value = (T) data.get(key);

		if (value == null) {
			value = defaultValue;
		}
	
		return value;
	}
}
