package com.bankapp.banking_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankapp.banking_app.dto.AccountDto;
import com.bankapp.banking_app.entity.Account;
import com.bankapp.banking_app.mapper.AccountMapper;
import com.bankapp.banking_app.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto getAccountById(Long Id) {
		Account account = accountRepository.findById(Id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		 List<Account>accounts =  accountRepository.findAll();
		 return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
		 		 .collect(Collectors.toList());
		
	}


	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Does Not Exist"));
		accountRepository.deleteById(id);
	}
	

}
