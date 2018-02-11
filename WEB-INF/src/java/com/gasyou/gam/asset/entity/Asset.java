package com.gasyou.gam.asset.entity;

import org.apache.lucene.document.Document;

import com.gasyou.gam.common.search.Field;

public class Asset {

	/**
	 * The type of an asset.
	 */
	private String type = null;

	/**
	 * The ID to specify an asset.
	 */
	private String primaryKey = null;

	/**
	 * The title of an asset.
	 */
	private String title = null;

	public Asset populate(Document doc) {

		this.type = doc.get(Field.CLASS.getFiledName());
		this.primaryKey = doc.get(Field.PK.getFiledName());
		this.title = doc.get(Field.TITLE.getFiledName());

		return this;
	}

	public String getType() {
		return this.type;
	}

	public String getPrimaryKey() {
		return this.primaryKey;
	}

	public String getTitle() {
		return this.title;
	}
}
