package account.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.entity.Account;
import account.service.AccountService;
import common.servlet.AbstractServlet;

public class SearchAccountServlet extends AbstractServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String id = req.getParameter("accountId");

			List<Account> accounts = null;

			if (id != null && id.trim().length() > 0) {
				// Get an account specified by ID.
				accounts = new AccountService().search(id);
			} else {
				// Get all accounts from GA.
				accounts = new AccountService().search();
			}

			req.setAttribute("id", id);
			req.setAttribute("accounts", accounts);

			setForward(req, resp, "/WEB-INF/jsp/searchAccount.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
