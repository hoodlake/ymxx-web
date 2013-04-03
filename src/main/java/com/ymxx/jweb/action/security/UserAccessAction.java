package com.ymxx.jweb.action.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.ymxx.jweb.persistence.account.User;
import com.ymxx.jweb.service.security.AccountService;

//TODO debug jetty
//TODO ajax support
//TODO jetty hot deploy
@Controller
@RequestMapping("/account") 
public class UserAccessAction{

	private AccountService accountService = new AccountService();

	@RequestMapping(method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		
		model.addAttribute(new User());
		return "account/toRegister";
	}
	/**
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void doRegister(HttpServletRequest request,HttpServletResponse response)
			throws Exception{

		String name = WebUtils.findParameterValue(request, "name");
		String password = request.getParameter("password");
		
		System.out.println(request.getContentType());
		User user = new User(name,password);
		System.out.println(user.toString());
		if(!accountService.checkRepeatUser(user)){
			try{
				
				accountService.createUser(user); 
				response.getWriter().write("{\"succ  ess\"}");
			}catch(Exception e){
				
				System.out.println(user.toString()+"fsdfdsf");
				response.getWriter().write("{\"err  or\"}");
			}
		}
		
	}
}
