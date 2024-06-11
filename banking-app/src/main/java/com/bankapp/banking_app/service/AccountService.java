package com.bankapp.banking_app.service;

import java.util.List;

import com.bankapp.banking_app.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long Id);
	
	AccountDto deposit(long id, double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
