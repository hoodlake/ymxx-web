package com.ymxx.jweb.action.security;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ymxx.jweb.persistence.account.User;
import com.ymxx.jweb.service.security.AccountService;

@Controller
@RequestMapping("/user")
public class UserAccessAction{

	private AccountService accountService = new AccountService();

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView doRegister(){
		
		User user = new User("张少举","aimuchun99");
		if(accountService.checkUser(user)){
			try{
				accountService.createUser(new User());
			}catch(Exception e){
				
				e.printStackTrace();
			}
			
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/createSuccess");
		mav.addObject("user",user);
		return mav;

	}
	

	
}
