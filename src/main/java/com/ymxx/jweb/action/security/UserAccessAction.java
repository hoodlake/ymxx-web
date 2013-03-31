package com.ymxx.jweb.action.security;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymxx.jweb.persistence.account.User;
import com.ymxx.jweb.service.security.AccountService;

//TODO debug jetty
//TODO ajax support
//TODO jetty hot deploy
@Controller
@RequestMapping("/sercurity")
public class UserAccessAction{

	private AccountService accountService = new AccountService();

	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		
		return "security/register";
	}
	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> doRegister(@RequestBody User user)
			throws Exception{

		if(!accountService.checkRepeatUser(user)){
			try{
				
				accountService.createUser(user);
			
			}catch(Exception e){
				
				return Collections.singletonMap("repeat", "repeat");
			}
		}
		return Collections.singletonMap("uuid", user.getUuid());
	}
}
