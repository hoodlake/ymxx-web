package com.ymxx.jweb.persistence.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Entity
@Table(name="ymxx_user")
public class User {  

	/**
	 * 唯一标识符
	 */
	@Id
	@Column(name="UUID")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String uuid;
	/**
	 * 真实姓名
	 */
	@Column(name="NAME")
	private String name;
	
	/**
	 * 身份证号
	 */
	@Column(name="PESONID")
	private String personId;
	
	
	
	/**
	 * 别名
	 */
	@Column(name="NICKNAME")
	private String nickname;
	/**
	 * 电子邮箱
	 */
	@Column(name="EMAIL")
	private String email;
	/**
	 * 用户密码
	 */
	@Column(name="PASSWORD")
	private String password; 
	
	/**
	 * 出生日期
	 */
	@Column(name="BIRTH")
	@Temporal(TemporalType.DATE)
	private Date birth;
	
	/**
	 * 状态
	 */
	@Column(name="STATUS")
	public Integer status;
	
	public User() {
		
		super();
	}
	
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User(String uuid, String name, String password) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.password = password;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
	
	
}
