package com.juice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.juice.entity.Admin;
//import com.food.utility.DBConnection;
import com.juice.utility.DBConnection;

public class AdminServiceImpl  implements AdminService{
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;

	@Override
	public boolean addAdmin(Admin a) {
		
		try {
			con= DBConnection.makeConnection();
			sql="insert into admin values (?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,a.getAdminId());//addcustomer(customer c);
			ps.setString(3,a.getAdminUser());
			ps.setString(2,a.getAdminPassword()); //getCustomerId
			 int i = ps.executeUpdate();
			 if(i>0)
				 return true;
		}
		catch(Exception e ) {
			e.printStackTrace();
				
		}
		finally {
			try {
				ps.close();
				con.close();
			}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return false;
	}

	
	public Admin showAdminById(String adminId) {
		try {
			con= DBConnection.makeConnection();
			sql="select * from admin where adminId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, adminId);
			rs=ps.executeQuery();
			List<Admin> clist=new ArrayList<Admin>();
			if(rs.next()) {
				Admin c= new Admin();
				c.setAdminId(rs.getString("adminId"));
				c.setAdminUser(rs.getString("adminUser"));
				c.setAdminPassword(rs.getString("adminPassword"));
			
				return  c ;
			}
		
		}
			 
		catch(Exception e ) {
			e.printStackTrace();
				
		}
		finally {
			try {
				ps.close();
				con.close();
			}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
	
		return null;
	}
	/*@Override
			public List<customer> showAllCustomer() {
				try{
					con=DBConnection.makeConnection();
					sql="select * from customer";
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					List<customer> clist=new ArrayList<customer>();
					while(rs.next()) {
						customer c= new customer();
						c.setCustomerId(rs.getString("customerId"));
						c.setCustomerName(rs.getString("customerName"));
						c.setEmailId(rs.getString("emailId"));
						c.setPasssword(rs.getString("passsword"));
						c.setContactNo(rs.getLong("contactNo"));
						c.setAddress(rs.getString("address"));
						c.setDropLocation(rs.getString("dropLocation"));
					   clist.add(c);
					}
					
					return clist;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						ps.close();
						con.close();
					}catch(SQLException e) {
						
						e.printStackTrace();
					}
				}
				
				return null;
		}*/

	@Override
	
		public boolean deleteAdmin(Admin a) {

			con=DBConnection.makeConnection();
			sql="delete from admin where  adminId=?";
			try {
				
				ps=con.prepareStatement(sql);
				ps.setString(1,a.getAdminId() );
				
				int i=ps.executeUpdate();
				if(i>0)
					return true;
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			finally {
				try {
					ps.close();
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return false;
	}


	@Override
	public boolean updateAdmin(Admin a) {
		try {
			con=DBConnection.makeConnection();
		
		sql="update admin set adminUser=?,adminPassword=? where  adminId=? ";
		ps=con.prepareStatement(sql);
		ps.setString(3, a.getAdminId());
		ps.setString(1, a.getAdminUser());
		ps.setString(2,a.getAdminPassword());
		
		int i = ps.executeUpdate();
		 if(i>0)
			 return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				ps.close();
				con.close();
			}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		

		
		
		return false;
	}
	
}
/*sql= "UPDATE food_6370 set foodName=?, type=? , category =? ,price=? , quantityInStock=? where  foodId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(6,f.getFoodId());
			ps.setString(1,f.getFoodName());
			ps.setString(2,f.getType());
			
			ps.setString(3,f.getCategory());
			ps.setDouble(4,f.getPrice());
			ps.setInt(5,f.getQuantityInStock());
			 int i = ps.executeUpdate();
			 if(i>0)
 * 
 * 
 * 
 * try {
			con=DBConnection.makeConnection();
			sql="update cart set quantity=? where  cartId=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,quantity);
			ps.setString(2,cartId);
			int i =ps.executeUpdate();
			if(i>0) {
				sql="update cart set subtotal=price*quantity where  cartId=?";
				ps=con.prepareStatement(sql);
				ps.setString(1,cartId);
				i=ps.executeUpdate();
				if(i>0)
					return true;
					}
					}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				ps.close();
				con.close();
			}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		return false;
	}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
	