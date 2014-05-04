package com.tangsi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tangsi.log.annotation.Log;
import com.tangsi.pojo.User;
import com.tangsi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private static final String REMEMBER_YES = "yes";
	
	/**
	 * 自动登录的cookie最大保存时间,默认一个月
	 */
	private static final int REMEMBER_COOKIE_LIFETIME =  3600*24*30;
	
	/**
	 * cookie名称
	 */
	private static final String REMEMBER_COOKIE_NAME="tangsi";

	@Autowired
	private UserService userService;

	@RequestMapping("/tologin")
	public String toLoginView() {
		return "login";
	}

	/**
	 * 判断用户是否登陆
	 * @param username
	 * @param password
	 * @return
	 */
	@Log("登陆")
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
		User user = null;
		
		Cookie[] cookies = request.getCookies();
    	if(cookies != null && cookies.length > 0) {
    		for(Cookie cookie : cookies) {  //从cookie读取账户密码
    			if(REMEMBER_COOKIE_NAME.equals(cookie.getName())) {
    				 String content = cookie.getValue();
    				 username = content.split("_")[0];
    				 password = content.split("_")[1];
    			}
    		}
    	}
		
    	user = this.userService.getUserByUsername(username);
		if(user == null) {
			request.setAttribute("usernameMsg", "该用户不存在");
			return "forward:/user/tologin";
		}else {
			user = this.userService.findUser(username, password);
			if(user == null) {
				request.setAttribute("pwdMsg", "密码错误");
				return "forward:/user/tologin";
			}
		}
    	
		//登录成功
		 Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
	        token.setRememberMe(true);
	        try {
	        	subject.login(token);
	        	
	        	/*if(REMEMBER_YES.equals((String)request.getParameter("remember-me"))) {//记住我功能
	        		Cookie cookie = new Cookie(REMEMBER_COOKIE_NAME, username+"_"+password); //保存账户密码,应加密 ，以后改进
	        		cookie.setMaxAge(REMEMBER_COOKIE_LIFETIME);  //设置cookie生命周期为一个月
	        		cookie.setPath("/");
	        		response.addCookie(cookie);//将cookie写回客户端浏览器 
	        	}*/
	        	
	            return "main";
	        }catch (AuthenticationException e) {
	            logger.error("登录失败错误信息:"+e);
	            token.clear();
	            return "redirect:/user/tologin";
	        }
		
	}

	@RequestMapping("/toregiste")
	public String toRegiste() {
		return "register";
	}
	
	
	@Log("注册")
	@RequestMapping("/registe")
	public String saveUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty())  {
			
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			this.userService.saveUser(user);
		}
		
		return "redirect:/user/tologin";
		
	}
	
	@RequestMapping("/test")
	public String test() {
		return "register";
	}

}
