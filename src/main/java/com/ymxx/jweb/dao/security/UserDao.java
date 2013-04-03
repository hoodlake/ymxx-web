package com.ymxx.jweb.dao.security;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ymxx.jweb.component.Pagination;
import com.ymxx.jweb.dao.YMXXBaseDao;
import com.ymxx.jweb.persistence.account.User;

@Repository("userDao")
public class UserDao extends YMXXBaseDao<User>{

	private static String SELECT_USER = "from User user"; 
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
		
		return (User)this.get(uuid);
	}
	/**
	 * @param uuid
	 */
	public void delUser(String uuid){
		
		User user =  this.getUserByUuid(uuid);
		this.delete(user);
	}
	/**
	 * @param pg
	 * @return
	 */
	public Pagination<User> queryUser(Pagination<User> pg){
		
		String hql = SELECT_USER;
		String hqlCount = hql;
		return this.query(hql, hqlCount, pg);
	}
	/**
	 * 动态查询，使用Criteria接口比较方便，无需拼接SQL语句
	 * @param pg
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pagination<User> queryUser(Pagination<User> pg,Map<String,Object> params){
		
		Session session = this.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		Criteria criteriaCount = session.createCriteria(User.class);
		
		//	criteria.setFetchMode("btCorp", FetchMode.JOIN).setFetchMode("currency", FetchMode.JOIN);
		//分页
		criteria.setFirstResult((pg.getPageNo() - 1) * pg.getPageSize())
				.setMaxResults(pg.getPageSize());
		//绑定动态查询参数
		Object name = params.get("name");
		if(name != null){
			
			criteria.add(Restrictions.like("name", "%"+name+"%"));
			criteriaCount.add(Restrictions.like("name", "%"+name+"%"));
		}
		pg.setPageItems(criteria.list());
		Long totalCount = ((Long) criteriaCount.setProjection(
				Projections.rowCount()).uniqueResult()).longValue();
		pg.setTotalCount(totalCount);
		return pg;
		
	}
}
