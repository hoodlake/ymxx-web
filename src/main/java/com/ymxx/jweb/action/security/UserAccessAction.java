package com.ymxx.jweb.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymxx.jweb.persistence.account.User;
import com.ymxx.jweb.service.security.AccountService;

//TODO debug jetty
//TODO ajax support
//TODO jetty hot deploy
@Controller
@RequestMapping("/user")
public class UserAccessAction{

	private AccountService accountService = new AccountService();

	@RequestMapping
	public @ResponseBody void doRegister(@RequestBody User user,
			HttpServletResponse response)throws Exception{

		if(accountService.checkUser(user)){
			try{
				accountService.createUser(user);
				response.getWriter().write("{\"success\"}");
			}catch(Exception e){
				
				response.getWriter().write("{\"fail\"}");
			}
		}
	}
	@RequestMapping(value="/toRegister")
	public String toRegisterPage(){
		
		return "register";
	}

	
}
