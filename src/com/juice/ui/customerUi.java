package com.juice.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.juice.entity.Juice;
import com.juice.entity.customer;

import com.juice.service.customerServiceImpl;
import com.juice.service.customerServiceImpl;

public class customerUi {
	public static void main(String[] args) {
		String customerId;
		String customerName;
		String emailId;
		String password;
		Long contactNo;
		String address;
		String dropLocation;
		Boolean flag;
		customer f = null;
		customerServiceImpl cimpl = new customerServiceImpl();

		List<customer> clist = null;

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(" Enter 1 --->  Add customer  ");
			System.out.println("Enter 2 -----> update  customer ");
			System.out.println("Enter 3 -----> Delete  customer  ");
			System.out.println("Enter 4 -----> Show all customer ");
			System.out.println("Enter 5------> serch customer   on the  basic of customerId");
			Integer option = sc.nextInt();
			sc.nextLine();
			switch (option) {
			/*
			 * System.out.println("Enter   your emailId :"); String email=sc.nextLine();
			 * String letter =email.substring(0,2);
			 * 
			 * Integer i = email.length(); foodId="F"+letter+i;
			 */
			case 1:

				// System.out.println("enter customer Id:");
				// customerId= sc.nextLine();
				System.out.println("Enter customer Name:");
				customerName = sc.nextLine();
				String id = customerName.substring(0, 2);
				Integer i = customerName.length();
				customerId = "c" + id + i;
				System.out.println(" Enter   EmailId: ");
				emailId = sc.nextLine();
				System.out.println("Enter  passsword");
				password = sc.nextLine();
				System.out.println("Enter contact No: ");
				contactNo = sc.nextLong();
				sc.nextLine();
				System.out.println(" Enter Address");
				address = sc.nextLine();
				System.out.println(" Enter  DropLocation");
				dropLocation = sc.nextLine();

				f = new customer(customerId, customerName, emailId, password, contactNo, address, dropLocation);
				flag = cimpl.addcustomer(f);
				if (flag)
					System.out.println("sucessfuly added ");
				else
					System.out.println(" error while add the customer detail");

				break;
			case 2:
				/*
				 * System.out.println("Enter the id of customer you want to  update ");
				 * customerId=sc.nextLine(); f=cimpl.showCustomerById(customerId); if(f!=null) {
				 * System.out.println(f);
				 * System.out.println("Are you sure  you want to update  this customer items:"
				 * 
				 * +"\nAnswer in yes or No"); String ans= sc.next(); sc.nextLine();
				 * if(ans.equalsIgnoreCase("yes")) {
				 * System.out.println("What do you want to update ");
				 * System.out.println(" Enter a----> Update name ");
				 * System.out.println("Enter b ---> Update emailId");
				 * System.out.println("Enter c---> Update password");
				 * System.out.println("Enter d---> Update contactNo");
				 * System.out.println("Enter e ---> Update Address");
				 * System.out.println("Enter f ---> Update DropLocation");
				 * 
				 * char choice2=sc.next() .toLowerCase().charAt(0); sc.nextLine();
				 * 
				 * switch(choice2) { case 'a': System.out.println("Enter Name:");
				 * customerName=sc.nextLine(); f.setCustomerName(customerName); flag
				 * =cimpl.updatecustomer(f); if(flag)
				 * System.out.println("customer Name updated succesfully  "); else
				 * System.out.println("Error while updating name"); break; }
				 * 
				 * }
				 */
				break;
			case 3:
				System.out.println("Enter the customerid you want to delete");
				customerId = sc.nextLine();
				f = cimpl.showCustomerById(customerId);
				if (f != null) {
					System.out.println("**Customer  Details**");
					System.out.println(f);

					System.out
							.println("Are you sure you want to delete this Customer item??" + "\nAnswer in yes or no");

					String ans = sc.next();
					sc.nextLine();
					if (ans.equalsIgnoreCase("yes")) {
						flag = cimpl.deletecustomer(f);

						if (flag) {
							System.out.println("Customer deleted successfully");
						} else
							System.out.println("error while deleting the Customer");

					} else if (ans.equalsIgnoreCase("no"))
						System.out.println("Please answer in yes and no only!! back to the main menu..");

				} else
					System.out.println(" No Customer item with this id is found !!!");

				break;
			case 4:
				showAllCustomers();
				break;
			case 5:
				showCustomerForId(sc);

				// System.out.println("");

				break;

			}
		}
	}

	public static void showCustomerForId(Scanner sc) {
		customerServiceImpl cimpl=new customerServiceImpl();
		
		String customerId;
		customer f;
		System.out.println("Enter the Id of the customer want to serch :");
		customerId = sc.nextLine();
		// sc.nextLine();

		f = cimpl.showCustomerById(customerId);
		if (f != null) {
			System.out.println("customer Id:" + f.getCustomerId());
			System.out.println("customer Name:" + f.getCustomerName());
			System.out.println("customer Email:" + f.getEmailId());
			System.out.println("customer Password:" + f.getPassword());
			System.out.println("customer contact Number:" + f.getContactNo());
			System.out.println("customer Address:" + f.getAddress());
			System.out.println("customer Droplocation:" + f.getDropLocation());

			// flag=cimpl.showCustomerById(customerId);
		} else {
			System.out.println("error");

		}
	}

	public static void showAllCustomers() {
		customerServiceImpl cimpl= new customerServiceImpl();
		
		List<customer> clist;
		clist = cimpl.showAllCustomer();
		if (clist != null && clist.isEmpty() != true) {
			Iterator<customer> it = clist.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
				System.out.println(
						"_________________________________________________________________________________\n");
			}

		} else
			System.out.println("no data for food deatils at this moment..");
	}

}
