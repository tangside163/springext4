package com.tangsi.controller;

import org.apache.log4j.Logger;
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

	@Autowired
	private UserService userService;

	@RequestMapping("/tologin")
	public String toLoginView() {
		return "login";
	}

	@Log("登陆")
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		User user = this.userService.findUser(username, password);
		if (user != null) {
			logger.info("��½�ɹ�");
			return "main";
		}
		return "redirect:/user/tologin";
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

}
