package com.tangsi.dao;

import java.util.List;

import com.tangsi.pojo.Logger;

public abstract interface LoggerDao {

	public List<Logger> findAll();
	
	public Logger save(Logger logger);
	
}
