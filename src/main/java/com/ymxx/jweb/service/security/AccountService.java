package com.ymxx.jweb.service.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymxx.jweb.component.Pagination;
import com.ymxx.jweb.dao.security.UserDao;
import com.ymxx.jweb.persistence.account.User;

@Service("accountService")
@Transactional("transactionManager-hibernate")
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
	/**
	 * @param user
	 * @throws Exception
	 */
	public void createUser(User user)throws Exception{
		
		
		System.out.println("create success："+user.toString());
	}
	/**
	 * @param seconds
	 */
	public void wait(int seconds){
		
		try {
			Thread.sleep(1000*seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public Pagination<User> getUserList(Pagination<User> pg) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "操");
		return userDao.queryUser(pg,map);
		
	}
	
}
