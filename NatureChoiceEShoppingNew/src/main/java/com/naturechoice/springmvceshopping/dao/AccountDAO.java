package com.naturechoice.springmvceshopping.dao;
 
import org.springframework.stereotype.Component;

import com.naturechoice.springmvceshopping.entity.Account;
@Component
public interface AccountDAO {
 
    
    public Account findAccount(String userName );

	void registerSave(String user, String pwd, String role);
    
}