package com.ymxx.jweb.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ymxx.jweb.component.Pagination;
import com.ymxx.jweb.exception.YmxxException;

public class YMXXBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> tClass;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public YMXXBaseDao(){
		
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		tClass = (Class<T>)params[0];
	}
	/**
	 * 获取当前session
	 * @return
	 */
	protected Session getCurrentSession(){
		
		return this.sessionFactory.getCurrentSession();
	}
	/**
	 * 打开一个新的session,此方法打开的Session不受Spring切面事务管理
	 * @return
	 */
	@Deprecated
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
		getCurrentSession().save(entity);
	}
	/**
	 * @param entity
	 */
	protected void delete(Object entity){
		
		getCurrentSession().delete(entity);
		
	}
	/**
	 * @param claz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T get(Serializable id){
		
		if(id == null){
			throw new YmxxException("None entity exists with a null uuid");
		}	
		return (T)getCurrentSession().get(tClass, id);
	}
	
	/**
	 * @param entities
	 */
	protected void batchAdd(List<?> entities){
		
		Session session = getCurrentSession();
		for(int i = 0 ; i < entities.size() ; i++){
			
			session.save(entities.get(i));
			if(i%20 == 0){
				session.flush();
			}
		}
		session.flush();
	}
	/**
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> query(String hql){
		
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		
		List<T> list = query.list();
		
		return list;
	}
	/**
	 * @param hql
	 * @param hqlCount
	 * @param pg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Pagination<T> query(String hql,String hqlCount,Pagination<T> pg){
		
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		Query queryCount = session.createQuery("select count(*) "+hqlCount);
		
		//查询结果集
		query.setFirstResult((pg.getPageNo()-1)*pg.getPageSize());
		query.setMaxResults(pg.getPageSize());		
		List<T> list = query.list();
		//获取总记录数
		Long total = (Long)queryCount.uniqueResult();
		
		pg.setPageItems(list);
		pg.setTotalCount(total);
		return pg;
	}
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public Query createQuery(String hql,Object ...params){
		
		Query query = this.getCurrentSession().createQuery(hql);
		if(params != null && params.length != 0){
			for(int i = 0 ; i < params.length ; i++){
				
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	public Query createQuery(String hql,Pagination<T> pg,Object ...params){
		
		Query query = this.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < params.length ; i++){
			
			query.setParameter(i, params[i]);
		}
		query.setFirstResult((pg.getPageNo()-1)*pg.getPageSize());
		query.setMaxResults(pg.getPageSize());
		return query;
	}
}
