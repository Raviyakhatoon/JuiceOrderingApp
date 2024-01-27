package com.juice.ui;

import java.util.List;
import java.util.Scanner;

import com.juice.bean.User;
import com.juice.entity.Cart;
import com.juice.entity.Order;
import com.juice.entity.customer;
import com.juice.exceptions.CustomException;
import com.juice.service.*;
import com.juice.service.JuiceServiceImpl;
import com.juice.entity.Juice;
import com.juice.service.CartServiceImpl;
import com.juice.service.customerServiceImpl;

public class CartUi {

	public static void main(String[] args)  {
		String cartId;
		String juiceId;
		String customerId;
		Integer quantity;// the number of food items added to cart.
		double subTotal;// price* quantity
		double price;// food price
		Juice f;
		Boolean flag;
		int num = 2;
		int option;
		String ans;

		Scanner sc = new Scanner(System.in);
		Cart c = null;
		CartServiceImpl cimpl = new CartServiceImpl();
		List<Cart> clist = null;
		while (true) {
			System.out.println("\n\n*************** WELCOME TO JUICE ORDERING APP**********************");

			System.out.print("\nEnter  your  userName i.e your id:");
			String username = sc.nextLine().trim();
			System.out.print("Enter  your Password:");
			String password = sc.nextLine();
			LoginUI log = new LoginUI();
			User user = log.login(username, password);
			if ("customer".equals(user.getUserType())) {
				boolean logout = false;
				while (logout == false) {
					System.out.println("\nEnter  the number  as given  in option ...");
					System.out.println("Enter  1------>  Add to cart");
					System.out.println("Enter  2------>  Show my  cart");
					System.out.println("Enter  3------>  update  quantity of  Juice item ");
					System.out.println("Enter  4------>  Delete Juice item  from cart");
					System.out.println("Enter  5------>  Clear  my cart");
					System.out.println("Enter  6------>  see order menu");
					System.out.println("Enter  7------>  Logout");
					System.out.println("________________________________________________________________________________________________");
					option = sc.nextInt();
					sc.nextLine();
					switch (option) {
					case 1:
						int min = 200;
						int max = 400;
						int b = (int) (Math.random() * (max - min + 1) + min);
						cartId = "CA" + b;

						List<Juice> flist = new JuiceServiceImpl().showAllJuice();
						System.out.println("\njuice name and juice id show below");
						for (Juice f1 : flist) {
							System.out.println(f1.getJuiceName() + " : " + f1.getJuiceId());
						}
						System.out.println("\nEnter  the juice id");
						juiceId = sc.nextLine();
						customerId = user.getUserId();
						flag = cimpl.checkJuiceItem(customerId, juiceId);
						if (flag)
							System.out.println(
									"Item  was already  present in your cart  quantity has been  increased  by 1 ");
						else {
							quantity = 1;
							price = new JuiceServiceImpl().getJuiceById(juiceId).getPrice();
							subTotal = quantity * price;
							c = new Cart(cartId, juiceId, customerId, quantity, subTotal, price);
							flag = cimpl.addToCart(c);
							if (flag)
								System.out.println("Juice items added to cart succesfully..!");
							else
								System.out.println("error while added in juice items to cart..!");
						}
						break;
					case 2:
						customerId = user.getUserId();
						clist = cimpl.showMycart(customerId);
						if (clist != null && clist.isEmpty() != true) {
							System.out.println("****** your  cart  details*****");
							Double grandTotal = 0.0;
							for (Cart cart : clist) {
								System.out.println("cart id: " + cart.getCartId());
								System.out.println("Item added: " + cart.getF().getJuiceName());
								System.out.println("Price of single item: " + cart.getPrice());
								System.out.println("Quantity in cart: " + cart.getQuantity());
								System.out.println("subTotal: " + cart.getSubTotal());
								grandTotal += cart.getSubTotal();
								System.out.println("***----------------------------------------------------***");

							}
							System.out.println("Grand total=" + grandTotal);
							System.out.println("\nDo you want to go to order page ? Answer in yes or no...");
							ans = sc.nextLine();

							if (ans.equalsIgnoreCase("yes")) {
								new OrderUi().orderMenu(customerId, clist);

							} else if (ans.equalsIgnoreCase("no"))
								System.out.println("Thank you for you input. Continue browsing....");
							else
								System.out.println("Please answer in yes or no only!!Going back to main menu");

						} else
							System.out.println("your cart is empty.please  add to cart first");
						break;

					case 3:
						System.out.println(" Enter the cartId want to update:");
						cartId = sc.nextLine();
						c = cimpl.getCartById(cartId);
						if (c != null) {
							System.out.println("cart id: " + c.getCartId());
							System.out.println("Item added: " + c.getF().getJuiceName());
							System.out.println("Price of single item: " + c.getPrice());
							System.out.println("Quantity in cart: " + c.getQuantity());
							System.out.println("subTotal: " + c.getSubTotal());
							System.out.println("Enter new quantity:");
							quantity = sc.nextInt();
							sc.nextLine();
							if (quantity > 0) {
								flag = cimpl.updateQuantity(cartId, quantity);
								if (flag)
									System.out.println("quantity update succesfully");

								else
									System.out.println("error while changing ");

							} else
								System.out.println("please  give number greater than 0 for quantity");

						}

						break;

					case 4:
						System.out.print("Enter cartId of item to be deleted: ");
						cartId = sc.nextLine();

						System.out.println("Are you sure you want to delete this item?" + "\nAnswer in yes or no");
						ans = sc.nextLine();

						if (ans.equalsIgnoreCase("yes")) {

							flag = cimpl.deleteFoodItemFromcart(cartId);
							if (flag)
								System.out.println("Item deleted from cart successfully");
							else
								System.out.println("Error while deleting item.");
						} else if (ans.equalsIgnoreCase("no"))
							System.out.println("Thank you for input. Please continue browsing...");
						else
							System.out.println("Answer in yes or no only. Going back to main menu..");

						break;

					case 5:
						customerId = user.getUserId();

						System.out.println("Are you sure you want to clear your cart?" + "\nAnswer in yes or no");
						ans = sc.nextLine();

						if (ans.equalsIgnoreCase("yes")) {

							try {
								flag = cimpl.clearMyCart(customerId);
								if (flag)
									System.out.println("Your cart is now empty!!!");
								else
									throw new CustomException("No item added in cart..");
							} catch (CustomException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getMessage());
								
							}
								//System.out.println("Error while clearing cart.");
						} else if (ans.equalsIgnoreCase("no"))
							System.out.println("Thank you for input. Please continue browsing...");
						else
							System.out.println("Answer in yes or no only. Going back to main menu..");

						break;

					case 7:
						logout = true;
						System.out.println("Thank you  for visiting  our service .  Visit  again  soon!!!");
						break;
					case 6:
						customerId = user.getUserId();
						clist = cimpl.showMycart(customerId);
						new OrderUi().orderMenu(customerId, clist);

						break;

					default:
						System.out.println("Please give inputs as per the options given");
					}
				}

			}

			else if (("admin").equals(user.getUserType())) {
				System.out.println("you have  logged in as Admin-----*** ");
				boolean logout = false;
				while (logout == false) {
					System.out.println("_________________________________________________________________________________");
					
					System.out.println("Enter the number to open \n 1:cart"
							+ "\n 2:juice\n 3:customer mmenu");
					System.out.println("Enter 1 -----> Show cart options-----");
					System.out.println("Enter 2 -----> Show juice options-----");
					System.out.println("Enter 3------> Show customer option----");
					System.out.println("Enter 4 -----> Logout------------------");
					option = sc.nextInt();
					switch (option) {
					case 1:
						System.out.println("***********Cart Menu********************");
						logout = handleCartMenu(sc, cimpl);
						break;
					case 2:
						JuiceUi ju = new JuiceUi();
						logout = ju.handleJuice(sc);
						break;
					case 3:
						System.out.println("**********Customer Menu******************");
						logout = handleCustomerMenu(sc);
						
						break;
					case 4:
						logout = true;
						break;
					default:
						System.out.println("Please enter a valid option 1,2 or 3");
						break;
					}

				} // while ends
			} // else if ends

		} // outer while loops ends

	}// main method ends
	
	private static boolean handleCustomerMenu(Scanner sc) {
		System.out.println("Enter 1-----> Show all customer Details ");
		System.out.println("Enter 2------> Search customer  on the  basic of customerId");
		Integer option = sc.nextInt();
		sc.nextLine();
		switch(option) {
		case 1:
			customerUi.showAllCustomers();
			break;
		case 2:
			customerUi.showCustomerForId(sc);
			break;
			
		}

		return false;
	}
	private static boolean handleCartMenu(Scanner sc, CartServiceImpl cimpl) {
		String cartId;
		int option;
		Cart c;
		List<Cart> clist;
		boolean logout = false;
		System.out.println("\nEnter  the number  as given in option .. ");
		System.out.println("Enter  1---> Show  all cart items----***");
		System.out.println(" Enter 2---> Show cart  by  id----***");
		System.out.println("Enter  3--->logout---***");
		option = sc.nextInt();
		sc.nextLine();
		switch (option) {
		case 1:
			clist = cimpl.showAllCart();
			if (clist != null && clist.isEmpty() != true) {
				for (Cart cart : clist) {
					System.out.println("cart id: " + cart.getCartId());
					System.out.println("customer id :" + cart.getCustomerId());
					System.out.println("Item added: " + cart.getF().getJuiceName());
					System.out.println("Price of single item: " + cart.getPrice());
					System.out.println("Quantity in cart: " + cart.getQuantity());
					System.out.println("subTotal: " + cart.getSubTotal());
					//System.out.println("_______________________________________________");

				}

			} else
				System.out.println("The cart  is emplty .No data avilable at this moments");
			break;
		case 2:
			System.out.println(" Enter the cartId want to show:");
			cartId = sc.nextLine();
			c = cimpl.getCartById(cartId);
			if (c != null) {
		
				System.out.println("cart id: " + c.getCartId());
				System.out.println("customer id :" + c.getCustomerId());
				System.out.println("Item added: " + c.getF().getJuiceName());
				System.out.println("Price of single item: " + c.getPrice());
				System.out.println("Quantity in cart: " + c.getQuantity());
				System.out.println("subTotal: " + c.getSubTotal());
			} else
				System.out.println(" No cart with  this Id  Found......!pls search again");

			break;
		case 3:
			logout = true;
			System.out.println("Thank you  for visiting  our service *** Visit  again  Soon!!!");
			break;
		default:
			System.out.println("Please give inputs as per the options given");

		}// while ends
		return logout;
	}

}// class ends
