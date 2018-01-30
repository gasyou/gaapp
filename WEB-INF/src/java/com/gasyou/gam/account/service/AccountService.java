package com.gasyou.gam.account.service;

import java.util.ArrayList;
import java.util.List;

import com.gasyou.gam.account.entity.AccountWrapper;

public class AccountService {

	// Dummy
	public List<AccountWrapper> search() {
		List<AccountWrapper> l = new ArrayList<AccountWrapper>();

		// start Dummy code.
		l.add(create("sfdsa1", "dfdsafsda1"));
		l.add(create("sfdsa2", "dfdsafsda2"));
		l.add(create("sfdsa3", "dfdsafsda3"));
		return l;
	}

	// Dummy
	public List<AccountWrapper> search(String id) {
		List<AccountWrapper> l = search();
		List<AccountWrapper> ret = new ArrayList<AccountWrapper>();
//		for (AccountWrapper acct: l) {
//			if (acct.getId().equals(id)) {
//				ret.add(acct);
//				break;
//			}
//		}
		return ret;
	}

	/**
	 * Create an account instance.
	 * @param id Account's ID
	 * @param name Account's Name
	 * @return An Account instance.
	 */
	private AccountWrapper create(String id, String name) {
		AccountWrapper acct = new AccountWrapper(null);
		return acct;
	}
}
