package com.ymxx.jweb.service.security;

import com.ymxx.jweb.persistence.account.User;

public class AccountService {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean checkUser(User user) {
		
		if(user.getName() == null || user.getPassword() == null){
			
			return false;
		}
		if("张少举".equals(user.getName()) && "aimuchun99".equals(user.getPassword())){
			
			return true;
		}
		return false;
	}
	public void createUser(User user)throws Exception{
		
		System.out.println("创建用户成功："+user.toString());
	}
}
