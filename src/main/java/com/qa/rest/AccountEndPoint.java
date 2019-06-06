package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountService;

@Path("/account")
public class AccountEndPoint {

	@Inject
	private AccountService service;

	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String createAccount(String account) {
		return service.createAccount(account);
	}

	@Path("/deleteAccount")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("accountNumber") int accountNumber) {
		return service.deleteAccount(accountNumber);
	}

	@Path("/updateAccount")

	public String updateAccount(int accountNumber, String account) {
		return service.updateAccount(accountNumber, account);
	}

	public void setService(AccountService service) {
		this.service = service;
	}
}
