/**
 * tangsi	
 * 2014Äê3ÔÂ29ÈÕ
 */
package com.tangsi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tangsi.dao.LoggerDao;
import com.tangsi.pojo.Logger;

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
