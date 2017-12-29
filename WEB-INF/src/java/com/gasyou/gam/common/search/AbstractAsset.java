package com.gasyou.gam.common.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public abstract class AbstractAsset implements SearchableAsset {

	@Override
	public Document getDocument() {
		Document doc = new Document();

		doc.add(new StringField("className", this.getClassName(), Store.YES));
		doc.add(new StringField("primaryKey", this.getPrimaryKey(), Store.YES));
		doc.add(new TextField("fulltext", this.getFullText(), Store.YES));

		return getDocument(doc);
	}

//	@Override
//	public SearchableAsset setDocument(Document doc) {
//
//		String className = doc.get("className");
//
//		SearchableAsset sa = (SearchableAsset) Class.forName(className).newInstance();
//
//		return sa;
//	}

	/**
	 * @param doc Document
	 * @return Document
	 */
	public abstract Document getDocument(Document doc);

	/**
	 * @return concrete class name
	 */
	public abstract String getClassName();

	/**
	 * @return Primary Key
	 */
	public abstract String getPrimaryKey();

	/**
	 * @return The text to search by fulltext-search
	 */
	public abstract String getFullText();
}
