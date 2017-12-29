package com.gasyou.gam.user.entity;

public class User {

	private int id = 0;
	private String loginId = null;
	private String password = null;
	private String name = null;
	private int updCnt = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpdCnt() {
		return updCnt;
	}

	public void setUpdCnt(int updCnt) {
		this.updCnt = updCnt;
	}
}
