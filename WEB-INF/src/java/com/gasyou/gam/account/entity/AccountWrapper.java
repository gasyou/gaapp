package com.gasyou.gam.account.entity;

import com.google.api.services.analytics.model.Account;

public class AccountWrapper {
	private Account account = null;

	public AccountWrapper(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}
}
