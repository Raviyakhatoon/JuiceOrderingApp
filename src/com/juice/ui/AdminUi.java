package com.juice.ui;

import java.util.Scanner;

import com.juice.entity.Admin;
import com.juice.entity.customer;
import com.juice.service.AdminServiceImpl;

public class AdminUi {
	public static void  main(String[] args) {
	String adminId;
	String adminUser;
    String adminPassword;
    Admin a=null;
    AdminServiceImpl aimpl= new AdminServiceImpl();
    boolean flag;
	 while (true){
		 Scanner sc = new Scanner(System.in);
	    System.out.println("Enter 1-----> add Admin");
		System.out.println("Enter 2 -----> update  Admin ");
		System.out.println("Enter 3 -----> Delete  Admin ");
		System.out.println("Enter 4 -----> update Admin ");
	
		Integer option = sc.nextInt();
		sc.nextLine();
		switch(option) {
		case 1:
			System.out.println("Enter Admin Name:");
			adminUser = sc.nextLine();
			 String id=adminUser.substring(0,2);
			 Integer i= adminUser.length();
			 adminId="c"+id+i;
			 //System.out.println(" Enter   Admin userId: ");
			 //adminId =sc.nextLine();
			 System.out.println("Enter  passsword");
			 adminPassword=sc.nextLine();
			 
			 a=new Admin( adminId,  adminUser, adminPassword) ;
			 flag= aimpl.addAdmin(a);
			 if(flag)
				 System.out.println("sucessfuly added ");
			   else
				   System.out.println(" error while add the customer detail");
			
			break;
		case 2:
			break;
		case 3:
			break;
			
			

		}
}
}
}