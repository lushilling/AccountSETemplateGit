package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;

public class AccountServiceImp implements AccountService {

	@Inject
	private AccountRepository repo;
	
	public void setRepe(AccountRepository repo) {
		this.repo = repo;
	}

	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}
	

	@Override
	public String createAccount(String account) {
		if (account.contains(account)) {
			return "Can't add this account";
		}
		return repo.createAccount(account);
	}

	@Override
	public String deleteAccount(int accountNumber) {
		return repo.deleteAccount(accountNumber);
	}

	@Override
	public String updateAccount(int accountNumber, String account) {
		return repo.updateAccount(accountNumber, account);
	}

}