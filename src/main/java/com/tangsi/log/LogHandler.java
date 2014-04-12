package com.tangsi.log;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tangsi.log.annotation.Log;
import com.tangsi.pojo.Logger;
import com.tangsi.service.LoggerService;

public class LogHandler {

	@Autowired
	private LoggerService loggerService;
	
	
	public void logBefore(JoinPoint joinPoint) {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		Method method = signature.getMethod();

		Log logAnno = method.getAnnotation(Log.class);

		if (logAnno != null) {
			Class<?> clazz = method.getDeclaringClass();
			String url = "";
			//类级别的url映射
			RequestMapping classrequestMapping = clazz
					.getAnnotation(RequestMapping.class);
			if(classrequestMapping != null ) {
				if(classrequestMapping.value().length == 0) {
					url += "/" +clazz.getName();  //
				}else {
					url += classrequestMapping.value()[0];
				}
			}
			//方法级别的url映射
			RequestMapping methodRequestMapping = method
					.getAnnotation(RequestMapping.class);
			
			if(methodRequestMapping != null ) {
				if(methodRequestMapping.value().length == 0) { //
					url += "/" + method.getName();
				}else {
					url +=methodRequestMapping.value()[0];
				}
			}
			
			
			Logger logger = new Logger();
			logger.setUrl(url);
			logger.setTime(System.currentTimeMillis());
			logger.setMessage(logAnno.value());
			this.loggerService.addLogger(logger);
			
		}

	}

}
