package com.gasyou.gam.account.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

import com.gasyou.gam.common.search.BaseIndexer;
import com.gasyou.gam.common.search.Field;
import com.google.api.services.analytics.model.Account;

public class AccountIndexer extends BaseIndexer<Account> {

	@Override
	protected void populateDocument(Document baseDoc, Account asset) {
		baseDoc.add(new TextField(Field.TITLE.getFiledName(), asset.getName(), Store.YES));
	}

	@Override
	protected String getPrimaryKey(Account object) {
		return object.getId();
	}
}
