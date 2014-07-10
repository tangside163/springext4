package com.tangsi.user.dao;

import com.tangsi.user.pojo.User;

public interface UserDao {
	

	public abstract User find(String name, String password);

	public abstract User save(User user);

	public abstract void delete(User user);

	public abstract User findByUsername(String username);

	public abstract void update(User user);

}