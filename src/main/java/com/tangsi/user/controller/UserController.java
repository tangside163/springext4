package com.tangsi.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tangsi.user.log.annotation.Log;
import com.tangsi.user.pojo.User;
import com.tangsi.user.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private static final String REMEMBER_YES = "yes";
	
	/**
	 * 允许最大密码输入错误数，默认为3，超过了则锁定用户
	 */
	private static final int MAX_ERROR_TIMES = 3;
	
	/**
	 * 解除锁定的间隔时间,默认10分钟
	 */
	private static final long UNLOCK_INTERVAL_TIME = 10*60*1000;
	
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
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password,HttpServletRequest request,HttpServletResponse response) {
		
		
    	User user1 = this.userService.getUserByUsername(username);
		if(user1 == null) {
			request.setAttribute("usernameMsg", "该用户不存在");
			return "forward:/user/tologin";
		}else {
			
			if(user1.getErrorTimes() == MAX_ERROR_TIMES && user1.getLockedAt() != 0 &&
						(System.currentTimeMillis() - user1.getLockedAt() <= UNLOCK_INTERVAL_TIME)) {
				request.setAttribute("usernameMsg", "该用户已被锁定，请联系管理员");
				return "forward:/user/tologin";
			}
			
			User user2 = this.userService.findUser(username, password);
			if(user2 == null) {
				int errorTimes = user1.getErrorTimes();//密码错误次数
				
				if(errorTimes < MAX_ERROR_TIMES) {
					errorTimes++;
				}
				if(errorTimes == MAX_ERROR_TIMES) {
					request.setAttribute("pwdMsg", "密码3次错误,该用户已被锁定");
				}else {
					request.setAttribute("pwdMsg", "密码错误");
				}
				
				user1.setLockedAt(System.currentTimeMillis());
				user1.setErrorTimes(errorTimes);
				this.userService.update(user1);
				
				return "forward:/user/tologin";
			}else {
				logger.info("登录成功");
				//登录成功要解除锁定
				if(user1.getErrorTimes() != 0 && user1.getLockedAt() != 0) {
					long lockedAt = user1.getLockedAt();
					//10分钟之后解锁
					if(System.currentTimeMillis() - lockedAt > UNLOCK_INTERVAL_TIME) {
						user1.setErrorTimes(0);
						user1.setLockedAt(0);
						this.userService.update(user1);
					}
					
				}
				
			}
		}
    	
		//登录成功
		 Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
	        token.setRememberMe(true);
	        try {
	        	subject.login(token);
	            return "main";
	        }catch (AuthenticationException e) {
	            logger.error("登录失败,错误信息:"+e);
	            token.clear();
	            return "redirect:/user/tologin";
	        }
		
	}

	@RequestMapping("/toregiste")
	public String toRegiste() {
		return "registe";
	}

    @RequestMapping("registe")
	public String saveRegiste() {
        return null;
    }


	@Log("注册")
	@RequestMapping("/registe/save")
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

    @RequiresRoles("superadmin")
	@RequestMapping("/test")
	public String test() {
        return "register";
	}

    @RequestMapping("/notauthrized")
    public String notauthrizedView() {
        return "notauthrized";
    }


    /**
     * 对于与shiro整合，基于注解的角色赋权，
     * 如果用户权限不足，捕捉UnauthorizedException，转发到权限不足页面
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView model = new ModelAndView();
        model.addObject("exception",e);
        model.setViewName("notauthrized");
        return model;
    }

}
