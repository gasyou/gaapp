package com.gasyou.gam.profile.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

import com.gasyou.gam.common.search.BaseIndexer;
import com.gasyou.gam.common.search.Field;
import com.google.api.services.analytics.model.Profile;

public class ProfileIndexer extends BaseIndexer<Profile> {

	@Override
	protected void populateDocument(Document baseDoc, Profile object) {
		baseDoc.add(new TextField(Field.TITLE.getFiledName(), object.getName(), Store.YES));
	}

	@Override
	protected String getPrimaryKey(Profile object) {
		return object.getId();
	}
}
