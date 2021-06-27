package com.naturechoice.springmvceshopping.authentication;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naturechoice.springmvceshopping.dao.AccountDAO;
import com.naturechoice.springmvceshopping.entity.Account;
//MyDBAuthenticationService class is a custom  UserDetailsService which retrieves user details from database
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private AccountDAO accountDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(username);
        System.out.println("Account= " + account);
 
        if (account == null) {
            throw new UsernameNotFoundException("User " 
                    + username + " was not found in the database");
        }
 
        // get the user role like USER,ADMIN,..
        String role = account.getUserRole();
        
        //GrantedAuthority Represents an authority granted to an Authentication object. 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
 
        // ROLE_USER, ROLE_ADMIN
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
 
        grantList.add(authority);
 
        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
 
        UserDetails userDetails = (UserDetails) new User(account.getUserName(), 
                account.getPassword(), enabled, accountNonExpired, 
                credentialsNonExpired, accountNonLocked, grantList);
 
        return userDetails;
    }
 
}