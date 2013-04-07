package com.ymxx.jweb.component;

public class Msg {

	private Boolean status = false;
	private String msg = "success";
	
	public Msg(Boolean status, String msg) {
		super(); 
		this.status = status;
		this.msg = msg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toJson(){
		
		String json = "{\"status\":"+this.status+",\"msg\":\""+msg+"\"}";
		return json;
	}
}
