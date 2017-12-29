package com.gasyou.gam.account.model;

import java.util.List;

import com.gasyou.gam.common.model.AbstractModel;
import com.gasyou.gam.common.service.ServiceFactory;
import com.gasyou.gam.ga.service.GAService;
import com.google.api.services.analytics.model.Account;

public class AccountListModel extends AbstractModel {

	@Override
	public String action() throws Exception {

//		String id = req.getParameter("accountId");

		GAService service = ServiceFactory.getService(GAService.class);
		List<Account> accounts = service.getAccounts();

//		req.setAttribute("id", id);
		req.setAttribute("accounts", accounts);

		return "success";
	}
}
