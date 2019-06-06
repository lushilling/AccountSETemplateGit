package com.qa.persistence.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(TxType.SUPPORTS)
public class AccountDatabaseRepository {

	private JSONUtil json;

	@PersistenceContext(unitName = "Primary")
	private EntityManager em;

	@Transactional(TxType.SUPPORTS)
	public List<Account> getAllAccounts() {
		TypedQuery<Account> getAll = em.createQuery("SELECT a FROM Account a", Account.class);
		return getAll.getResultList();

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

	public Account findAccount(int id) {
		return em.find(Account.class, id);
	}

	@Transactional(TxType.REQUIRED)
	public String updateAccount(int accountNumber, String account) {
		Account account1 = json.getObjectForJSON(account, Account.class);
		Account updateAccount1 = findAccount(accountNumber);
		updateAccount1 = account1;
		em.getTransaction().begin();
		em.merge(updateAccount1);
		em.getTransaction().commit();

		return json.returnMessage("Account successfully updated");

	}

}
