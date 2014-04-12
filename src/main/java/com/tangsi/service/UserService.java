package com.tangsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangsi.dao.UserDao;
import com.tangsi.pojo.User;

@Service("userService")
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User findUser(String name, String password) {
		return this.userDao.find(name, password);
	}
	
	public User saveUser(User user ) {
		return this.userDao.save(user);
	}
	
	public void deleteUser(User user) {
		this.userDao.delete(user);
	}
	
}
