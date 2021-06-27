package com.naturechoice.springmvceshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.naturechoice.springmvceshopping.dao.AccountDAO;
import com.naturechoice.springmvceshopping.entity.Account;

@Component
public class AccountService {
	
		
		@Autowired
	    private AccountDAO accountDAO;
		@Transactional
		public Account findAccount(String userName ) {
			return accountDAO.findAccount(userName);
		}
		@Transactional
		public void registerSave(String user, String pwd, String role) {
			accountDAO.registerSave(user, pwd, role);
		}
}
