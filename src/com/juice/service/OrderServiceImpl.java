package com.juice.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.juice.entity.Order;
import com.juice.utility.DBConnection;

public class OrderServiceImpl implements OrderService {
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;

	@Override
	public Order placeOrder(Order o) {
		
		
		try {
			con=DBConnection.makeConnection();
			sql="insert into  Orders values(?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,o.getOrderId());
			/*
			 * converting java LocalDate type  to sql  Date type 
			 * 
			 */
			ps.setDate(2,Date.valueOf(o.getOrderDate()));
			ps.setString(3,o.getDropLocation());
			/*
			 * 
			 * Converting  java  LocalDateTime  type  to sql  Timestamp type  to  store date  and time 
			 */
			ps.setTimestamp(4, Timestamp.valueOf(o.getExpectedDelivery()));
			ps.setString(5,o.getCustomerId());
			ps.setDouble(6, o.getBillingAmount());
			ps.setString(7,o.getStatus());
			int i=ps.executeUpdate();
			if(i>0) {
				new  CartServiceImpl().clearMyCart(o.getCustomerId());
				return o;
			}
			
			
		}
	
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			ps.close();

			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return null;
	}
	

	@Override
	public boolean cancleOrder(String orderId) {
		try {
			con=DBConnection.makeConnection();
			sql=" update orders set status='cancelled' where orderId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,orderId);
			int i=ps.executeUpdate();
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
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return false;
	}

	@Override
	public List<Order> showMyOrderHistory(String customerId) {
		
		try {
			con=DBConnection.makeConnection();
			sql="select *from  orders  where customerId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,customerId);
			rs=ps.executeQuery();
			List<Order> olist=new ArrayList<>();
			while(rs.next()) {
				Order o= new Order();
				o.setBillingAmount(rs.getDouble("billingAmount"));
				o.setCustomerId(rs.getString("customerId"));
				o.setDropLocation(rs.getString("dropLocation"));
				o.setExpectedDelivery(rs.getTimestamp("expectedDelivery").toLocalDateTime());
				o.setOrderDate(rs.getDate("orderDate").toLocalDate());
				o.setOrderId(rs.getString("orderId"));
				o.setStatus(rs.getString("status"));
				olist.add(o);
				
			}
			return olist;
			
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
		
		return null;
	}

	@Override
	public Order showOrderById(String orderid) {
		try {
			sql="select * from orders where orderId=?";
			
			con=DBConnection.makeConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1,orderid);
			rs=ps.executeQuery();
			if(rs.next()) {
				Order o= new Order();
				o.setBillingAmount(rs.getDouble("billingAmount"));
				o.setCustomerId(rs.getString("customerId"));
				o.setDropLocation(rs.getString("dropLocation"));
				o.setExpectedDelivery(rs.getTimestamp("expectedDelivery").toLocalDateTime());
				o.setOrderDate(rs.getDate("orderDate").toLocalDate());
				o.setOrderId(rs.getString("orderId"));
				o.setStatus(rs.getString("status"));
				return o;
				
				
			}
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
		
		
		return null;
	}

}
