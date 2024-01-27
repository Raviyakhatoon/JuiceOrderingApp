package com.juice.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection makeConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Juiceproject","root", "root");
		}
		catch(Exception e ) {
			e.printStackTrace();
			
		}
		return con;
	}
	public static void main(String[] args) {
		Connection con= makeConnection();
		
		if(con!=null)
			System.out.println("successful connected ");
		else
			System.out.println("no");	
	}
	
}





