package com.juice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.juice.entity.Cart;
import com.juice.entity.Juice;
import com.juice.entity.customer;
import com.juice.utility.DBConnection;

public class CartServiceImpl implements CartService {
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;
	public boolean addToCart(Cart c) {
		con=DBConnection.makeConnection();
		sql="insert  into cart values (?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,c.getCartId());
			ps.setString(2,c.getjuiceId());
			
			ps.setInt(3,c.getQuantity());
			
			ps.setDouble(4,c.getSubTotal());
			ps.setDouble(5,c.getPrice());
			ps.setString(6,c.getCustomerId());
			
		int i =ps.executeUpdate();
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
	public boolean updateQuantity(String cartId, Integer quantity) {
		try {
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

	public boolean deleteFoodItemFromcart(String cartId) {
		try {
			con=DBConnection.makeConnection();
			sql="delete from cart where  cartId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, cartId);
			int i =ps.executeUpdate();
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

	public List<Cart> showMycart(String customerId) {
		try {
			con=DBConnection.makeConnection();
			sql="select *  from cart where  customerId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,customerId);
			rs=ps.executeQuery();
			List<Cart> clist=new ArrayList<Cart>();
			while(rs.next()) {
				Cart c =new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setCustomerId(rs.getString("customerId"));
				c.setjuiceId(rs.getString("juiceId"));
				c.setQuantity(rs.getInt("quantity"));
				c.setPrice(rs.getDouble("price"));
				c.setSubTotal(rs.getDouble("subTotal"));
				Juice f =new JuiceServiceImpl().getJuiceById(rs.getString("juiceId"));
				c.setF(f);
				clist.add(c);	
			}
			return clist;
			
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
	public List<Cart> showAllCart() {
		try {
			con=DBConnection.makeConnection();
			sql="select * from  cart";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			List<Cart> clist=new ArrayList<Cart>();
			while(rs.next()) {
				Cart c =new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setjuiceId(rs.getString("customerId"));
				c.setCustomerId(rs.getString("juiceId"));
				c.setQuantity(rs.getInt("quantity"));
				c.setPrice(rs.getDouble("price"));
				c.setSubTotal(rs.getDouble("subTotal"));
			Juice f =new JuiceServiceImpl().getJuiceById(rs.getString("juiceId"));
				c.setF(f);
				clist.add(c);	
			}
			return clist;
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
	public Cart getCartById(String cartId) {
		try {
			con=DBConnection.makeConnection();
			sql="select *  from cart where cartId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,cartId);
			rs=ps.executeQuery();
			if(rs.next()) {
				Cart c =new Cart();
				c.setCartId(rs.getString("cartId"));
				c.setjuiceId(rs.getString("juiceId"));
				c.setCustomerId(rs.getString("customerId"));
				c.setQuantity(rs.getInt("quantity"));
				c.setPrice(rs.getDouble("price"));
				c.setSubTotal(rs.getDouble("subTotal"));
				Juice f =new JuiceServiceImpl().getJuiceById(rs.getString("juiceId"));
				c.setF(f);
				return c;	
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
	@Override
	public boolean clearMyCart(String customerId) {
		
		try {
			con=DBConnection.makeConnection();
			sql="delete from cart where customerId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,customerId);
			//System.out.println(ps.toString());
			int i= ps.executeUpdate();
			
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
	@Override
	public boolean checkJuiceItem(String customerId, String juiceId) {
		try {
			con=DBConnection.makeConnection();
			
			sql="select * from  cart where  customerId=? && juiceId=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, customerId);
			ps.setString(2, juiceId);
			rs=ps.executeQuery();
			if(rs.next()){
				int quantity =rs.getInt("quantity");
				quantity++;
				String  cartId=rs.getString("cartId");
				boolean  flag =updateQuantity(cartId,quantity);
				return flag;
				
				
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
		
		return false ;
	}
	
}
