package com.juice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.juice.entity.Juice;
import com.juice.entity.customer;
//import com.food.utility.DBConnection;
import com.juice.utility.DBConnection;

public class customerServiceImpl  implements customerService{
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;

	@Override
	public boolean addcustomer(customer c) {
		
		try {
			con= DBConnection.makeConnection();
			sql="insert into customer values (?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,c.getCustomerId());//addcustomer(customer c);
			ps.setString(2,c.getCustomerName()); //getCustomerId
			ps.setString(3,c.getEmailId());
			
			ps.setString(4,c.getPassword());
			ps.setDouble(5,c.getContactNo());
			ps.setString (6,c.getAddress());
			ps.setString(7, c.getDropLocation());
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

	
	public customer showCustomerById(String customerId) {
		try {
			con= DBConnection.makeConnection();
			sql="select * from customer where customerId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, customerId);
			rs=ps.executeQuery();
			List<customer> clist=new ArrayList<customer>();
			if(rs.next()) {
				customer c= new customer();
				c.setCustomerId(rs.getString("customerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setEmailId(rs.getString("emailId"));
				c.setPassword(rs.getString("password"));
				c.setContactNo(rs.getLong("contactNo"));
				c.setAddress(rs.getString("address"));
				c.setDropLocation(rs.getString("dropLocation"));
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
	//@Override
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
						c.setPassword(rs.getString("password"));
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
		}

	@Override
	
		public boolean deletecustomer( customer c) {

			con=DBConnection.makeConnection();
			sql="delete from customer where customerid=?";
			try {
				
				ps=con.prepareStatement(sql);
				ps.setString(1, c.getCustomerId());
				
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
	public boolean updatecustomer(customer c) {
		try {
			con=DBConnection.makeConnection();
		
		sql="update customer set customerName=?, password=?,contactNo=?,Address=?,dropLocation=? where  customerId=? ";
		ps=con.prepareStatement(sql);
		ps.setString(6, c.getCustomerId());
		ps.setString(1, c.getCustomerName());
		ps.setString(2,c.getPassword());
		ps.setLong(3,c.getContactNo());
		ps.setString(4,c.getAddress());
		ps.setString(5,c.getDropLocation());
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
	