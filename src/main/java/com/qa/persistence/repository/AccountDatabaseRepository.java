package com.qa.persistence.repository;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(TxType.SUPPORTS)
@Default
public class AccountDatabaseRepository implements AccountRepository {

	@Inject
	private JSONUtil json;

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public String getAllAccounts() {
		Query getAll = em.createQuery("SELECT a FROM Account a");

		Collection<Account> accounts = (Collection<Account>) getAll.getResultList();

		return json.getJSONForObject(accounts);

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
