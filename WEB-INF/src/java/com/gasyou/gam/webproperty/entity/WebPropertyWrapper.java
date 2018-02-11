package com.gasyou.gam.webproperty.entity;

import com.google.api.services.analytics.model.Webproperty;

public class WebPropertyWrapper {
	private Webproperty webProperty = null;

	public WebPropertyWrapper(Webproperty webProperty) {
		this.webProperty = webProperty;
	}

	public Webproperty getWebProperty() {
		return this.webProperty;
	}

}
