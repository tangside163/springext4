package com.tangsi.user.dao;

import com.tangsi.user.pojo.Logger;

import java.util.List;

public abstract interface LoggerDao {

	public List<Logger> findAll();
	
	public Logger save(Logger logger);
	
}
