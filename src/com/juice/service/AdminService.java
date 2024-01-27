package com.juice.service;

import java.util.List;

import com.juice.bean.User;
import com.juice.entity.Admin;

public interface AdminService {
	public boolean addAdmin(Admin c);

	public boolean deleteAdmin(Admin c);

	public boolean updateAdmin(Admin c);

	//public List<User> showAllCustomer();

	public Admin showAdminById(String aId);

}
/*
 * public List<Customer> showAllCustomer() { try{
 * con=DBConnection.makeConnection(); sql="select * from customers " ;
 * ps=con.prepareStatement(sql); rs=ps.executeQuery(); List<Customer> clist=new
 * ArrayList<Customer>(); while(rs.next()) { Customer c=new Customer();
 * c.setcustomerid(rs.getString("customerid"));
 * c.setcustomername(rs.getString("customername"));
 * c.setcustomer_phone(rs.getString("customer_phone"));
 * c.setcustomer_address(rs.getString("customer_address"));
 * c.setdrop_address(rs.getString("drop_address"));
 * c.setpayment(rs.getDouble("payment"));
 * c.setfeedback(rs.getString("feedback"));
 * 
 * clist.add(c);
 * 
 * 
 * } return clist;
 * 
 * } catch(Exception e) { e.printStackTrace(); } finally { try { ps.close();
 * con.close(); }catch(SQLException e) {
 * 
 * e.printStackTrace(); } } return null; }
 * 
 * 
 */
