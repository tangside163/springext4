package com.tangsi.user.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseDaoImpl {

	@Autowired
	protected SessionFactory sessionFactory;
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
}
