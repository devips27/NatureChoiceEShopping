package com.naturechoice.springmvceshopping.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.naturechoice.springmvceshopping.dao.AccountDAO;
import com.naturechoice.springmvceshopping.entity.Account;
 
//Transactional for Hibernate
@Component
@Transactional
public class AccountDAOImpl implements AccountDAO {
 
 @Autowired
 private SessionFactory sessionFactory;

 @Override
 public Account findAccount(String userName ) {
     Session session = sessionFactory.getCurrentSession();
     Criteria crit = session.createCriteria(Account.class);
     crit.add(Restrictions.eq("userName", userName));
     return (Account) crit.uniqueResult();
 }
 
 @Override
 public void registerSave(String user,String pwd,String role){
 	try {
 		Session session = sessionFactory.getCurrentSession();
 		Account acc = new Account();
 		acc.setUserName(user);
 		acc.setPassword(pwd);
 		acc.setActive(true);
 		acc.setUserRole(role);
 		session.persist(acc);
		} catch (Exception e) {
			e.printStackTrace();
		}
 }

}