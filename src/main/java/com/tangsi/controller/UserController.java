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

	@Log
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		User user = this.userService.findUser(username, password);
		if (user != null) {
			logger.info("µÇÂ½³É¹¦");
			return "main";
		}
		return "redirect:/user/tologin";
	}

	@RequestMapping("/toregiste")
	public String toRegiste() {
		return "register";
	}
	
	
	@Log
	@RequestMapping("/registe")
	public String saveUser(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty())  {
			
			User user = new User();
			user.setName("tangdu");
			user.setPassword("123");
			this.userService.saveUser(user);
		}
		
		return "redirect:/user/tologin";
		
	}

}
