package com.gasyou.gam.account.entity;

import org.apache.lucene.document.Document;

import com.gasyou.gam.common.search.SearchableAsset;
import com.google.api.services.analytics.model.Account;

public class AccountWrapper implements SearchableAsset {
	private Account account = null;

	public AccountWrapper(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}

	@Override
	public Document getDocument() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
