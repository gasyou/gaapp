package account.model;

import java.util.List;

import com.google.api.services.analytics.model.Account;

import common.model.AbstractModel;
import common.service.ServiceFactory;
import ga.service.GAService;

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
