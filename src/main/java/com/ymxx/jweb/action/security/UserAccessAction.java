package com.ymxx.jweb.action.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymxx.jweb.component.Msg;
import com.ymxx.jweb.component.Pagination;
import com.ymxx.jweb.persistence.account.User;
import com.ymxx.jweb.service.security.AccountService;

@Controller
@RequestMapping("/account") 
public class UserAccessAction{

	@Autowired
	private AccountService accountService;

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
	public @ResponseBody void doRegister(final HttpServletRequest request,
				HttpServletResponse response)
			throws Exception{

		response.setContentType("application/json");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new User(name,password);
		request.getSession().setAttribute("process",0);
		try{
			accountService.register(user);
			
			new Thread(){
				
				public void run(){
					while(true){
						HttpSession session = request.getSession();
						if(session == null){//说明当前request请求已经处理完毕了
							System.out.println("线程结束");
							break;
						}
						Integer process = (Integer)session.
								getAttribute("process");
						Integer count = 0;
						if(null == process){
							request.getSession().setAttribute("process",count);
						}else{
							
							count = (Integer)request.getSession().getAttribute("process");
							count +=3;
							request.getSession().setAttribute("process",count);
						}
						try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
						if(count > 100){
							System.out.println("线程结束");
							break;
						}
					}
				}
			}.start();
			System.out.println("启动进度条线程成功，v      等待30s");
			accountService.wait(30);
			response.getWriter().write(new Msg(true, "用户创建成功").toJson());
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().write(new Msg(false,e.getMessage()).toJson());
		}
		
	}
	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getCount")
	public @ResponseBody void getCount(HttpServletRequest request,
				HttpServletResponse response) 
			throws IOException{
		
		Integer count = (Integer)request.getSession().getAttribute("process");
		if(count%10 == 0){
			Pagination<User> pg = accountService.getUserList(new Pagination<User>(1,2));
			System.out.println(pg.toTableJson());
		}
		
		response.getWriter().write("{\"count\":"+count+"}");
	}
}
