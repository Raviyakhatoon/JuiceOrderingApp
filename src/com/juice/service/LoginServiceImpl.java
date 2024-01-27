package com.juice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.juice.utility.DBConnection;

public class LoginServiceImpl  implements LoginService{
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;

	@Override
	public boolean customerLogin(String username, String password) {
		try {
			con=DBConnection.makeConnection();
			sql=" select * from customer where customerId=? && password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if(rs.next())
			return true;
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				rs.close();
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean adminLogin(String username, String password) {
		try {
			con=DBConnection.makeConnection();
			sql=" select * from admin where adminId=? &&  adminPassword=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if(rs.next())
			return true;
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				rs.close();
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean changeCustPassword(String username, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeAdminPassword(String username, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
