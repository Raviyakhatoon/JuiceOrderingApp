package com.juice.service;

import java.util.List;

import com.juice.entity.Cart;
import com.juice.entity.Juice;
import com.juice.entity.customer;

public interface customerService {
	 public boolean addcustomer(customer c);
	 public boolean deletecustomer(customer c);
	 public boolean updatecustomer(customer c);
	 public List<customer> showAllCustomer();
	 public 	customer showCustomerById(String customerId);
	 
	 
	 
	 

}
/*public List<Customer> showAllCustomer() {
		try{
			con=DBConnection.makeConnection();
			sql="select * from customers " ;
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			List<Customer> clist=new ArrayList<Customer>();
			while(rs.next()) {
				Customer c=new Customer();
				c.setcustomerid(rs.getString("customerid"));
				c.setcustomername(rs.getString("customername"));
				c.setcustomer_phone(rs.getString("customer_phone"));
				c.setcustomer_address(rs.getString("customer_address"));
				c.setdrop_address(rs.getString("drop_address"));
				c.setpayment(rs.getDouble("payment"));
				c.setfeedback(rs.getString("feedback"));

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
 
 * 
 */
