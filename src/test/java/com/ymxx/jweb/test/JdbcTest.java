package com.ymxx.jweb.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {

	public static void main(String[] args) throws Exception {
		
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://127.0.0.1/";   
		String user = "root";   
		String psw = "aimuchun99";   
		Connection con = DriverManager.getConnection(url,user,psw);  
		if(con == null){
			
			System.out.println("null");
		}else
		System.out.println(con);
	}
}
