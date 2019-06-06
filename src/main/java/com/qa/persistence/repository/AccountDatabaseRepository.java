package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(TxType.SUPPORTS)
@Default
public class AccountDatabaseRepository implements AccountRepository {

	private JSONUtil json;

	@PersistenceContext(unitName = "Primary")
	private EntityManager em;

	@Transactional(TxType.SUPPORTS)
	public String getAllAccounts() {
		TypedQuery<Account> getAll = em.createQuery("SELECT a FROM Account a", Account.class);
		return getAll.toString();

	}

	@Transactional(TxType.REQUIRED)
	public String createAccount(String account) {
		em.persist(account);
		return json.returnMessage("Account successfuly created");

	}

	@Transactional(TxType.REQUIRED)
	public String deleteAccount(int accountNumber) {
		em.remove(accountNumber);
		return json.returnMessage("Account successfully removed");

	}

	@Transactional(TxType.REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account newAccount = json.getObjectForJSON(account, Account.class);
		Account oldAccount = em.find(Account.class, accountNumber);
		oldAccount = newAccount;
		em.getTransaction().begin();
		em.detach(oldAccount);
		em.merge(newAccount);
		em.getTransaction().commit();

		return json.returnMessage("Account successfully updated");

	}

}
