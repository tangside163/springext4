/**
 * tangsi	
 * 2014��3��29��
 */
package com.tangsi.user.dao.impl;

import com.tangsi.user.dao.LoggerDao;
import com.tangsi.user.pojo.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * 
 */
@Repository("loggerDaoImpl")
public class LoggerDaoImpl extends BaseDaoImpl implements LoggerDao {

	public List<Logger> findAll() {

		Query query = this.getSession().createQuery("from Logger");
		return query.list();
		
	}

	public Logger save(Logger logger) {
		
		 this.getSession().save(logger);
		 
		 return logger;
		
	}
	
}
