package com.gasyou.gam.profile.entity;

import com.google.api.services.analytics.model.Account;
import com.google.api.services.analytics.model.Profile;
import com.google.api.services.analytics.model.Webproperty;

public class ProfileWrapper implements Comparable<ProfileWrapper> {

	private Account account = null;

	private Webproperty webProperty = null;

	private Profile profile = null;

	public ProfileWrapper(Account account, Webproperty webProperty, Profile profile) {
		this.account = account;
		this.webProperty = webProperty;
		this.profile = profile;
	}

	public Account getAccount() {
		return account;
	}

	public Webproperty getWebProperty() {
		return webProperty;
	}

	public Profile getProfile() {
		return profile;
	}

	@Override
	public int compareTo(ProfileWrapper o) {

		// AccountId => WebPropertyId => ProfileId

		int v = this.account.getId().compareTo(o.getAccount().getId());

		if (v == 0) {
			v = this.getWebProperty().getId().compareTo(o.getWebProperty().getId());

			if (v == 0) {
				v = this.profile.getId().compareTo(o.getProfile().getId());
			}
		}

		return v;
	}

}
