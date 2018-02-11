package com.gasyou.gam.common.search;

public enum Field {

	CLASS("class"),
	PK("primaryKey"),
	UUID("uuid"),
	TITLE("title");

	private String fieldName = null;

	private Field(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFiledName() {
		return this.fieldName;
	}
}
