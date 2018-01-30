package com.gasyou.gam.webproperty.entity;

import org.apache.lucene.document.Document;

import com.gasyou.gam.common.search.SearchableAsset;
import com.google.api.services.analytics.model.Webproperty;

public class WebPropertyWrapper implements SearchableAsset {
	private Webproperty webProperty = null;

	public Webproperty getWebProperty() {
		return this.webProperty;
	}

	@Override
	public Document getDocument() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
