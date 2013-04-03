package com.ymxx.jweb.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymxx.jweb.dao.security.UserDao;
import com.ymxx.jweb.persistence.account.User;

@Service("accountService")
public class AccountService {

	@Autowired
	private UserDao userDao;
	public void register(User user)throws Exception{
		
		userDao.saveUser(user);
		//发送邮件
		System.out.println("Sending Email...");
	}
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean checkRepeatUser(User user) { 
		
		if(user.getName() == null || user.getPassword() == null){
			
			return false;
		}
		if("张少举".equals(user.getName()) 
				&& "aimuchun99".equals(user.getPassword())){
			
			return true;
		}
		return false;
	}
	public void createUser(User user)throws Exception{
		
		System.out.println("create success："+user.toString());
	}
}
