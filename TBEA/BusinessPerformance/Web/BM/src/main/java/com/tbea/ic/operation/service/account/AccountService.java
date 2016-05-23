package com.tbea.ic.operation.service.account;

public interface AccountService {

	public boolean resetpassword(String userName, String oldPassword,
			String newPassword);
	
	public boolean validateAccount(String userName);

}
