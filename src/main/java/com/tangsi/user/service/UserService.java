package com.tangsi.user.service;

import com.tangsi.user.dao.UserDao;
import com.tangsi.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/**
	 * 根据用户名获得用户信息
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username) {
		return this.userDao.findByUsername(username);
	}

	public void update(User user) {
		this.userDao.update(user);
	}
	
}
