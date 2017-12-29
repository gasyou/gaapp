package com.gasyou.gam.common.model;

import java.io.Serializable;

/**
 * ログイン情報.
 * ログイン時に生成されセッションに格納される.
 */
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginId = null;;

	public static final String SESSION_KEY = LoginInfo.class.getName();

	public LoginInfo(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginId() {
		return loginId;
	}
}
