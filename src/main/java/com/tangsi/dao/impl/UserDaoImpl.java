package com.tangsi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tangsi.dao.UserDao;
import com.tangsi.pojo.User;

@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tangsi.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	public User find(String name, String password) {

		Query query = this.getSession().createQuery("from User where name=? and password=?");
		query.setString(0,name);
		query.setString(1, password);
		
		List<User> list = query.list();
		
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tangsi.dao.impl.UserDao#save(com.tangsi.pojo.User)
	 */
	public User save(User user) {
		this.getSession().save(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tangsi.dao.impl.UserDao#delete(com.tangsi.pojo.User)
	 */
	public void delete(User user) {
	}

	public User findByUsername(String username) {
		Query query = this.getSession().createQuery(" from User where name=?");
		query.setString(0, username);
		List<User> users = query.list();
		if(users != null && !users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	public void update(User user) {
		
		this.getSession().update(user);
		
	}

}
