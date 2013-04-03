package com.ymxx.jweb.dao.security;

import org.springframework.stereotype.Repository;

import com.ymxx.jweb.dao.YMXXBaseDao;
import com.ymxx.jweb.persistence.account.User;

@Repository("userDao")
public class UserDao extends YMXXBaseDao{

	/**
	 * @param user
	 * @throws Exception
	 */
	public void saveUser(User user)throws Exception{
		
		this.save(user);
	}
	
	/**
	 * @param uuid
	 * @return
	 */
	public User getUserByUuid(String uuid){
		
		if(uuid == null){
			
			return null;
		}
		
		return (User)this.getEntity(User.class, uuid);
	}
	/**
	 * @param uuid
	 */
	public void delUser(String uuid){
		
		if(uuid == null){
			
			return;
		}
		this.delete(User.class, uuid);
	}
}
