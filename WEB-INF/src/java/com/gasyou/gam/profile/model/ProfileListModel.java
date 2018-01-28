package com.gasyou.gam.profile.model;

import java.util.ArrayList;
import java.util.List;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.ga.service.GAService;
import com.gasyou.gam.profile.entity.ProfileWrapper;
import com.google.api.services.analytics.model.Account;
import com.google.api.services.analytics.model.Profile;
import com.google.api.services.analytics.model.Webproperty;

public class ProfileListModel extends AbstractModel {

	@Override
	public String action() throws Exception {

		List<ProfileWrapper> results = new ArrayList<ProfileWrapper>();

		GAService service = ServiceFactory.getService(GAService.class);
		List<Account> accounts = service.getAccounts();

		for (Account account: accounts) {
			List<Webproperty> webProperties = service.getWebproperties(account.getId());
			for (Webproperty webProperty: webProperties) {
				List<Profile> profiles = service.getProfiles(webProperty.getAccountId(), webProperty.getId());
				for (Profile profile: profiles) {
					results.add(new ProfileWrapper(account, webProperty, profile));
				}
			}
		}

		req.setAttribute("profiles", results);

		return "success";
	}
}
