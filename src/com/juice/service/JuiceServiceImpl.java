package com.juice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.juice.entity.Juice;
import com.juice.entity.Juice;
import com.juice.entity.Juice;
import com.juice.utility.DBConnection;

public class JuiceServiceImpl  implements JuiceService{
	Connection con=null;
	String sql=null;
	PreparedStatement ps=null;
	ResultSet rs= null;


	@Override
	public boolean addJuice(Juice j) {
		try {
			con= DBConnection.makeConnection();
			sql="insert into juice values (?,?,?,?,?)";
			
			ps=con.prepareStatement(sql);
			ps.setString(1,j.getJuiceId());
			ps.setString(2,j.getJuiceName());
			ps.setString(3,j.getCategory());
			ps.setDouble(4,j.getPrice());
			ps.setInt(4,j.getQuantityInStock());
			ps.setDouble(5,j.getPrice());
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

	@Override
	public boolean deleteJuice( String juiceId) {
		try {
			
		con=DBConnection.makeConnection();
		sql="delete from juice  where  juiceId=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, juiceId);
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

	@Override
	public Juice getJuiceById(String juiceId) {
		con= DBConnection.makeConnection();
		sql= "select * from juice where  juiceId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,juiceId);
			
			
			rs=ps.executeQuery();
			if(rs.next()) {
				Juice j=new Juice();
				j.setJuiceId(rs.getString("juiceId"));
				j.setJuiceName(rs.getString("juiceName"));
				j.setCategory(rs.getString("category"));
				
				j.setPrice(rs.getDouble("price"));
				
				j.setQuantityInStock(rs.getInt("quantityInStock"));
				return j;
			
			}
		}
			catch (Exception e) {
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
	public List<Juice> showAllJuice() {
		try {
			con=DBConnection.makeConnection();
			sql= "select * from juice";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			List<Juice> flist=new ArrayList<Juice>();
			while(rs.next()) {
				Juice f=new Juice();
				f.setJuiceId(rs.getString("juiceId"));
				f.setJuiceName(rs.getString("juiceName"));
				f.setCategory(rs.getString("category"));
				
				f.setPrice(rs.getDouble("price"));
				
				f.setQuantityInStock(rs.getInt("quantityInStock"));
				flist.add(f);
			}
			return flist;
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
		
	
		return null;
	}

	@Override
	public List<Juice> getJuiceByCategory(String category) {
		
		return null;
	}

	@Override
	public Boolean updatejuice(Juice j) {
		con= DBConnection.makeConnection();
		sql= "UPDATE juice set JuiceName=?,category =?,quantityInStock=?,price=? where  juiceId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(5,j.getJuiceId());
			ps.setString(1,j.getJuiceName());
			
			
			ps.setString(2,j.getCategory());
			ps.setDouble(4,j.getPrice());
			ps.setInt(3,j.getQuantityInStock());
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
		
		return null;
	}

}
