package com.juice.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.juice.entity.Cart;
import com.juice.entity.Order;
import com.juice.entity.customer;
import com.juice.service.OrderServiceImpl;
import com.juice.service.customerServiceImpl;

public class OrderUi {
	private String orderId;
	private LocalDate orderDate;
	private String dropLocation;
	private LocalDateTime expectedDelivery;
	private String customerid;
	private Double billingAmount;
	private String status;
	boolean flag;
	
	Order o=null;
	OrderServiceImpl oimpl= new OrderServiceImpl();
	List<Order>  olist=null;
	
	
	public  void orderMenu(String customerId,List<Cart> clist) {
		customer cust =new customerServiceImpl().showCustomerById(customerId);
		System.out.println("welcome  to  the Order  page "+cust.getCustomerName()+" !!!");
		int option;
		String ans;
		Scanner sc=new Scanner(System.in);
		Double grandTotal= 0.0;
		if(clist!=null &&  clist.isEmpty()!=true) {
		   for(Cart cart:clist) {
			   System.out.println("cart id: "+cart.getCartId());
			   System.out.println("Item added: "+cart.getF().getJuiceName());
			   System.out.println("Price of single item: "+cart.getPrice());
			   System.out.println("Quantity in cart: "+cart.getQuantity());
			   System.out.println("subTotal: "+cart.getSubTotal());
			   grandTotal+=cart.getSubTotal();
		   
			   System.out.println("____________");
	   
		   }
		   System.out.println("Grand total="+grandTotal);
		}else
		{
			System.out.println("Your cart is empty and so you  will not able to place otder ");
			System.out.println("Do you  want  to continue with the order menu");
			ans=sc.nextLine();
			if(ans.equals("no"))
				return;
			
			else if(ans.equals("yes"))
				System.out.println("Thankyou for input. Please do not place order."+"\nYou can check other options");
			else
			{
				System.out.println("Please enter yes or no only....Returning to main menu");
				return;
			}
			
		}
		   
		
		while (true) {
			System.out.println("Enter 1---> Place Order");
			System.out.println("Enter 2---> cancle my Order ");
			System.out.println("Enter 3---> Show my order History ");
			System.out.println("Enter 4---> show order by Id ");
			System.out.println("Enter 5 ---> Going back to menu");
			
			option=sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				int min = 500;  
				int max = 1000; 				
				int b = (int)(Math.random()*(max-min+1)+min);

				orderId="OR"+b;
				
				orderDate=LocalDate.now();
				System.out.println("Do you want to  deliver  at the  below  given  address?");
				System.out.println(cust.getAddress());
				System.out.println("\nAnswer  in yes or no ");
				ans=sc.nextLine();
				if(ans.equalsIgnoreCase("yes")) {
					dropLocation=cust.getAddress();
					
				}
				else if(ans.equalsIgnoreCase("no")) {
					System.out.println("Enter  your  drop location :-");
				dropLocation=sc.nextLine();
			}
				
				else
					System.out.println("Please answer in yes or no only!!Going back to main menu");
			   				    
	   expectedDelivery=LocalDateTime.now().plusHours(1);
	   billingAmount=grandTotal;
	   status="Processing...";
	   o=new Order( orderId,orderDate, dropLocation,expectedDelivery,customerId,billingAmount,status);
	   Order order=oimpl.placeOrder(o);
	   if(order!=null) {
		   System.out.println("your  order  has been placed .Details given below....");
		   System.out.println("Order number :"+order.getOrderId());
		   System.out.println("DropLocation:"+order.getDropLocation());
		   DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
		   
		   LocalDateTime delivery=order.getExpectedDelivery();
		   System.out.println("Delivery by :"+delivery.format(pattern));
		   System.out.println("Billing amount: "+order.getBillingAmount());
		   System.out.println("status:"+order.getStatus());
		   return;
		   
	   }
	   else 
		   System.out.println("Erro while  placing Order");
	  
				break;
			case 2:
				System.out.print("Enter  the Order id  to be canclled:");
				orderId=sc.nextLine();
				flag=oimpl.cancleOrder(orderId);
				if(flag)
					System.out.println("your order has been cancelled");
				else
					System.out.println("Error while  cancelling  your orde. tyr again");
				break;
				
			case 3:
				olist=oimpl.showMyOrderHistory(customerId);
				if(olist!=null&& olist.isEmpty()!=true) {
					for(Order o1:olist) {
						  // System.out.println("your  order  has been placed .Details given below....");
						   System.out.println("Order number :"+o1.getOrderId());
						   System.out.println("DropLocation:"+o1.getDropLocation());
						   DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
						   
						   LocalDateTime delivery=o1.getExpectedDelivery();
						   System.out.println("Delivery by :"+delivery.format(pattern));
						   System.out.println("Billing Amount:"+o1.getBillingAmount());
						   System.out.println("Status: "+o1.getStatus());
						   System.out.println("__________________________________-");
						   
					}
						   
					
				}
				break;
				
			case 4:
				System.out.print("Enter the order  Id :");
				orderId=sc.nextLine();
				o=oimpl.showOrderById(orderId);
				if(o!=null) {
					System.out.println("Order number :"+o.getOrderId());
					   System.out.println("DropLocation:"+o.getDropLocation());
					   DateTimeFormatter pattern=DateTimeFormatter.ofPattern("dd-LLL-yyyy HH:mm");
					   
					   LocalDateTime delivery=o.getExpectedDelivery();
					   System.out.println("Delivery by :"+delivery.format(pattern));
					   System.out.println("Billing Amount:"+o.getBillingAmount());
					   System.out.println("Status: "+o.getStatus());
					   System.out.println("__________________________________-");
					
				}
				else
					System.out.print("no order id is found");
			
				break;
			case 5:
				System.out.println("\nGoing back to  main menu:");
				
				return;
				
				default: System.out.println("please enter the number  as give in menu");
				
				
			}
			
		}
		
	}

}
