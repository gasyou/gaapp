package com.gasyou.gam.webproperty.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;

import com.gasyou.gam.common.search.BaseIndexer;
import com.gasyou.gam.common.search.Field;
import com.google.api.services.analytics.model.Webproperty;

public class WebpropertyIndexer extends BaseIndexer<Webproperty> {

	@Override
	protected void populateDocument(Document baseDoc, Webproperty asset) {
		baseDoc.add(new TextField(Field.TITLE.getFiledName(), asset.getName(), Store.YES));
	}

	@Override
	protected String getPrimaryKey(Webproperty object) {
		return object.getId();
	}
}
