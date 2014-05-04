package com.tangsi.dao;

import com.tangsi.pojo.User;

public interface UserDao {
	

	public abstract User find(String name, String password);

	public abstract User save(User user);

	public abstract void delete(User user);

	public abstract User findByUsername(String username);

}