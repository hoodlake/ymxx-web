package com.ymxx.jweb.persistence.account;

public class User {

	private String uuid;
	private String name;
	private String email;
	private String password;
	
	
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
