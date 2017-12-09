package account.service;

import java.util.ArrayList;
import java.util.List;

import account.entity.Account;

public class AccountService {

	// Dummy
	public List<Account> search() {
		List<Account> l = new ArrayList<Account>();

		// start Dummy code.
		l.add(create("sfdsa1", "dfdsafsda1"));
		l.add(create("sfdsa2", "dfdsafsda2"));
		l.add(create("sfdsa3", "dfdsafsda3"));
		return l;
	}

	// Dummy
	public List<Account> search(String id) {
		List<Account> l = search();
		List<Account> ret = new ArrayList<Account>();
		for (Account acct: l) {
			if (acct.getId().equals(id)) {
				ret.add(acct);
				break;
			}
		}
		return ret;
	}

	/**
	 * Create an account instance.
	 * @param id Account's ID
	 * @param name Account's Name
	 * @return An Account instance.
	 */
	private Account create(String id, String name) {
		Account acct = new Account();
		acct.setId(id);
		acct.setName(name);
		return acct;
	}
}
