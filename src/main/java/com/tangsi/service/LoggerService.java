/**
 * tangsi	
 * 2014年3月29日
 */
package com.tangsi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tangsi.dao.LoggerDao;
import com.tangsi.pojo.Logger;

/**
 * @author Administrator
 * 
 */
@Service("loggerService")
public class LoggerService {

	@Autowired
	private LoggerDao loggerDao;
	
	public Logger addLogger(Logger logger) {
		this.loggerDao.save(logger);
		System.out.println("日志主键"+logger.getId());
		return logger;//返回获得主键的日志记录
	}
	
}
