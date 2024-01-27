package com.juice.ui;

import com.juice.bean.User;
import com.juice.entity.Admin;
import com.juice.entity.customer;
import com.juice.exceptions.CustomException;
import com.juice.service.AdminServiceImpl;
import com.juice.service.LoginServiceImpl;
import com.juice.service.customerServiceImpl;

public class LoginUI {
	public User login(String username, String password) {
		try {
			User user = new User();
			LoginServiceImpl limpl = new LoginServiceImpl();
			if (limpl.customerLogin(username, password)) {
				user.setUserType("customer");

			} else if (limpl.adminLogin(username, password)) {
				user.setUserType("admin");

			} else {
				throw new CustomException("username or password is  given  is wrong . please  login agian");
			}
			if (user.getUserType().equals("customer")) {
				customer cust = new customerServiceImpl().showCustomerById(username);
				user.setUserId(cust.getCustomerId());

				System.out.println("welcome to our  website" + " " + cust.getCustomerName());
			} else if (user.getUserType().equals("admin")) {
				Admin ad = new AdminServiceImpl().showAdminById(username);
				user.setUserId(ad.getAdminId());
				
				
				System.out.println("\nWELCOME "+" "+ad.getAdminUser());

			}
			return user;
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}
