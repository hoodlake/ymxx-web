package com.ymxx.jweb.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ymxx.jweb.exception.YmxxException;

public abstract class YMXXBaseDao {

	@Autowired
	protected SessionFactory sessionFactory;
	/**
	 * 获取当前session
	 * @return
	 */
	protected Session getCurrentSession(){
		
		return this.sessionFactory.getCurrentSession();
	}
	/**
	 * 打开一个新的session
	 * @return
	 */
	protected Session openSession(){
		
		return this.sessionFactory.openSession();
	}
	
	/**
	 * @param entity
	 */
	protected void save(Object entity){
		
		if(entity == null){
			
			throw new YmxxException("Can't not persistence a null entity");
		}
		Session session = this.openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
	}
	/**
	 * @param entity
	 */
	protected void delete(Class<?> claz,Serializable id){
		
		if(null == id){
			
			return;
		}
		
		Object entity = this.getEntity(claz, id);
		if(null == entity){
			
			return;
		}
		Session session = this.getCurrentSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();
	}
	protected Object getEntity(Class<?> claz,Serializable id){
		
		return this.openSession().get(claz, id);
	}
}
