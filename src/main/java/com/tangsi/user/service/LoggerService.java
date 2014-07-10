/**
 * tangsi	
 * 2014��3��29��
 */
package com.tangsi.user.service;

import com.tangsi.user.dao.LoggerDao;
import com.tangsi.user.pojo.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		System.out.println("��־����"+logger.getId());
		return logger;//���ػ���������־��¼
	}
	
}
