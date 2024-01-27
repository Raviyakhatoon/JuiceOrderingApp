package com.juice.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.juice.entity.Juice;
import com.juice.service.JuiceServiceImpl;

public class JuiceUi {

	// public static void main(String[] args) {
	String juiceName;
	String juiceId;
	String category;
	double price;
	Integer quantityInStock;
	Boolean flag = null;
	Juice j = null;
	String ans;
	List<Juice> flist = null;
	JuiceServiceImpl jimpl = new JuiceServiceImpl();
	public boolean handleJuice(Scanner sc) {
		System.out.println("************ JUICE PAGE*************");
		System.out.println("Enter the number as given in menu");
		System.out.println("Enter 1 -----> Add Juice");
		System.out.println("Enter 2 -----> update  Juice items ");
		System.out.println("Enter 3 -----> Delete  Juice items ");
		System.out.println("Enter 4 -----> Show all Juice items ");
		System.out.println("Enter 5------> serch Juice items  on the  basic of juice Id");
		System.out.println("Enter 6-------> logout");

		Integer option = sc.nextInt();
		sc.nextLine();
		boolean logout = false;
		switch (option) {
		case 1:
			int min = 10;
			int max = 400;
			int b = (int) (Math.random() * (max - min + 1) + min);

			juiceId = "F" + b;
			System.out.println("Enter the  juice name :");
			juiceName = sc.nextLine();
			System.out.println(
					"Enter the category of the Juice:\n1: Fresh Juice\n2: Healthy juice\n3: Chocolate Shake\n4: Falooda\n5: Special Blossom");

			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				category = "Fresh Juice";
				break;
			case 2:
				category = "Healthy juice";
				break;
			case 3:
				category = "Chocolate Shake";
				break;
			case 4:
				category = "Falooda";
				break;
			case 5:
				category = "Special Blossom";
				break;
			default:
				System.out.println("please enter only those number as given in the category menu ");
				category = "data unavailabe";
			}
			System.out.println("Enter  price:");
			price = sc.nextDouble();
			sc.nextLine();

			System.out.println("Quantity ");
			quantityInStock = sc.nextInt();
			sc.nextLine();

			j = new Juice(juiceId, juiceName, category, quantityInStock, price);
			flag = jimpl.addJuice(j);
			if (flag)
				System.out.println(" Juice  detais  added to database successfully ");
			else
				System.out.println(" Error  while adding juice details ");

			break;

		case 2:
			System.out.println("Enter the ID of juice which  you want to  update....... ");
			juiceId = sc.nextLine();
			j = jimpl.getJuiceById(juiceId);
			if (j != null) {
				System.out.println(j);
				System.out.println("Are you sure  you want to update  this juice  items:"

						+ "\nAnswer in yes or No");
				ans = sc.next();
				sc.nextLine();
				if (ans.equalsIgnoreCase("yes")) {
					System.out.println("What do you want to update ");
					System.out.println(" Enter a----> Update name ");
					System.out.println("Enter b---> Update category");
					System.out.println("Enter c---> Update Price");
					System.out.println("Enter d ---> Update Quantity");

					char choice2 = sc.next().toLowerCase().charAt(0);
					sc.nextLine();
					switch (choice2) {
					case 'a':
						System.out.println("Enter Name:");
						juiceName = sc.nextLine();
						j.setJuiceName(juiceName);
						flag = jimpl.updatejuice(j);
						if (flag)
							System.out.println("juice Name updated succesfully  ");
						else
							System.out.println("Error while updating name");
						break;
					case 'b':
						System.out.println(
								"Enter the category of the Juice:\n1: Fresh Juice\n2: Healthy juice\n3: Chocolate Shake\n4: Falooda\n5: Special Blossom");
						choice = sc.nextInt();
						sc.nextLine();
						switch (choice) {
						case 1:
							category = "Fresh Juice";
							break;
						case 2:
							category = "Healthy juice";
							break;
						case 3:
							category = "Chocolate Shakee";
							break;
						case 4:
							category = "Falooda";
							break;
						case 5:
							category = "Special Blossom";
							break;
						default:
							System.out.println("please enter only those number as given in the category menu ");
							category = "data unavailabe";
						}
						j.setCategory(category);
						flag = jimpl.updatejuice(j);
						if (flag)
							System.out.println(" juice category Updated succesfully !!!");
						else
							System.out.println("Error while updating  juice category !!!");

						break;

					case 'c':

						System.out.print("Enter price: ");
						price = sc.nextDouble();
						sc.nextLine();

						j.setPrice(price);
						flag = jimpl.updatejuice(j);

						if (flag)
							System.out.println("juice price updated successfully!!");
						else
							System.out.println("Error while updating juice price!!");
						break;

					case 'd':
						System.out.println("Quantity in stock:");
						quantityInStock = sc.nextInt();
						sc.nextLine();
						j.setQuantityInStock(quantityInStock);
						flag = jimpl.updatejuice(j);
						if (flag)
							System.out.println("juice quantity  Updated succesfully !!!");
						else
							System.out.println("Error while quantity  juice category !!!");

						break;
					default:
						System.out.println(" Please Enter the characters  as mention in Update Menu");
						break;
					}
				}
			}
			break;
		case 3:
			System.out.println("Enter the juice Id you want to delete");
			juiceId = sc.nextLine();

			System.out.println("Are you sure you want to delete this juice item??" + "\n plz Answer in YES or NO");

			ans = sc.next();
			sc.nextLine();
			if (ans.equalsIgnoreCase("yes")) {
				flag = jimpl.deleteJuice(juiceId);

				if (flag)
					System.out.println(" juice  deleted successfully");

				else
					System.out.println("error while deleting the food");
			} else if (ans.equalsIgnoreCase("no"))
				System.out.println("Thank you for input. Please continue browsing...");
			else
				System.out.println("Answer in yes or no only. Going back to main menu..");

			break;

		case 4:
			flist = jimpl.showAllJuice();
			if (flist != null && flist.isEmpty() != true) {
				Iterator<Juice> it = flist.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
					System.out.println("\n");
				}

			} else
				System.out.println("no data for juice deatils at this moment..");
			break;

		case 5:
			System.out.println(" Enter the juice Id want to show:");
			juiceId = sc.nextLine();
			j = jimpl.getJuiceById(juiceId);
			if (j != null) {
				System.out.println("juice id: " + j.getJuiceId());
				System.out.println("juice Name :" + j.getJuiceName());

				System.out.println("Price of  juice item: " + j.getPrice());
				System.out.println("Quantity in stock: " + j.getQuantityInStock());

			} else
				System.out.println(" No juice with  this Id  Found ");

			break;
		case 6:
			logout = true;

			break;
		// break;
		default:
			System.out.println("plz enter only those number  as mention in starting");

		}
		return logout;

	}

}

/*
 * public class JuiceUi {
 * 
 * public static void main(String[] args) {
 * 
 * 
 * String juiceName; String juiceId; String category; double price; Integer
 * quantityInStock; Boolean flag = null; Juice j= null; String ans;
 * 
 * List<Juice> flist=null;
 * 
 * 
 * 
 * JuiceServiceImpl jimpl= new JuiceServiceImpl();
 * 
 * 
 * Scanner sc = new Scanner(System.in);
 * System.out.println("************ JUICE PAGE**************"); while(true) {
 * System.out.println("Enter the number as given in menu");
 * System.out.println("Enter 1 -----> Add Juice");
 * System.out.println("Enter 2 -----> update  Juice items ");
 * System.out.println("Enter 3 -----> Delete  Juice items ");
 * System.out.println("Enter 4 -----> Show all Juice items "); System.out.
 * println("Enter 5------> serch Juice items  on the  basic of category");
 * Integer option = sc.nextInt(); sc.nextLine(); switch(option) { case 1: int
 * min = 10; int max = 400; int b = (int)(Math.random()*(max-min+1)+min);
 * 
 * juiceId="F"+b; System.out.println("Enter the  juice name :");
 * juiceName=sc.nextLine(); System.out.
 * println("Enter the category of the Juice:\n1: Fresh Juice\n2: Healthy juice\n3: Chocolate Shake\n4: Falooda\n5: Special Blossom"
 * );
 * 
 * int choice =sc.nextInt(); sc.nextLine(); switch(choice) { case 1:
 * category="Fresh Juice"; break; case 2 : category="Healthy juice"; break; case
 * 3: category="Chocolate Shake"; break; case 4 : category="Falooda"; break;
 * case 5: category="Special Blossom"; break; default: System.out.
 * println("please enter only those number as given in the category menu ");
 * category="data unavailabe"; } System.out.println("Enter  price:");
 * price=sc.nextDouble(); sc.nextLine();
 * 
 * System.out.println("Quantity "); quantityInStock=sc.nextInt(); sc.nextLine();
 * 
 * j=new Juice(juiceId,juiceName, category,price,quantityInStock);
 * flag=jimpl.addJuice(j); if (flag)
 * System.out.println(" Juice  detais  added to database successfully "); else
 * System.out.println(" Error  while adding juice details ");
 * 
 * break;
 * 
 * 
 * case 2:
 * System.out.println("Enter the ID of juice which  you want to  update....... "
 * ); juiceId=sc.nextLine(); j=jimpl.getJuiceById(juiceId); if(j!=null) {
 * System.out.println(j);
 * System.out.println("Are you sure  you want to update  this juice  items:"
 * 
 * +"\nAnswer in yes or No"); ans= sc.next(); sc.nextLine();
 * if(ans.equalsIgnoreCase("yes")) {
 * System.out.println("What do you want to update ");
 * System.out.println(" Enter a----> Update name ");
 * 
 * System.out.println("Enter b---> Update category");
 * System.out.println("Enter c---> Update Price");
 * System.out.println("Enter d ---> Update Quantity");
 * 
 * char choice2=sc.next() .toLowerCase().charAt(0); sc.nextLine();
 * switch(choice2) { case 'a': System.out.println("Enter Name:");
 * juiceName=sc.nextLine(); j.setJuiceName(juiceName); flag
 * =jimpl.updatejuice(j); if(flag)
 * System.out.println("Food Name updated succesfully  "); else
 * System.out.println("Error while updating name"); break;
 * 
 * break; case 'b': System.out.
 * println("Enter the category of the Juice:\n1: *Fresh Juice*\n2: *Healthy juice*\n3: *Chocolate Shake*\n4: *Falooda*\n5: *Special Blossom*"
 * ); choice =sc.nextInt(); sc.nextLine(); switch(choice) { case 1:
 * category="*Fresh Juice*"; break; case 2 : category="*Healthy juice*"; break;
 * case 3: category="*Chocolate Shakee*"; break; case 4 : category="*Falooda*";
 * break; case 5: category="Special Blossom"; break; default: System.out.
 * println("please enter only those number as given in the category menu ");
 * category="data unavailabe"; } j.setCategory(category);
 * flag=jimpl.updatejuice(j); if(flag)
 * System.out.println(" juice category Updated succesfully !!!"); else
 * System.out.println("Error while updating  juice category !!!");
 * 
 * break;
 * 
 * case 'c':
 * 
 * System.out.print("Enter price: "); price=sc.nextDouble(); sc.nextLine();
 * 
 * j.setPrice(price); flag=jimpl.updatejuice(j);
 * 
 * if(flag) System.out.println("juice price updated successfully!!"); else
 * System.out.println("Error while updating juice price!!"); break;
 * 
 * 
 * 
 * 
 * case 'd': System.out.println("Quantity in stock:");
 * quantityInStock=sc.nextInt(); sc.nextLine();
 * j.setQuantityInStock(quantityInStock); flag=jimpl.updatejuice(j); if(flag)
 * System.out.println("juice quantity  Updated succesfully !!!"); else
 * System.out.println("Error while quantity  juice category !!!");
 * 
 * 
 * break; default
 * :System.out.println(" Please Enter the characters  as mention in Update Menu"
 * );
 * 
 * 
 * 
 * break; case 3: System.out.println("Enter the juice Id you want to delete");
 * juiceId=sc.nextLine();
 * 
 * 
 * System.out.println("Are you sure you want to delete this juice item??"
 * +"\n plz Answer in YES or NO");
 * 
 * ans=sc.next(); sc.nextLine(); if(ans.equalsIgnoreCase("yes")) {
 * flag=jimpl.deleteJuice(juiceId);
 * 
 * if(flag) System.out.println(" juice  deleted successfully");
 * 
 * else System.out.println("error while deleting the food"); } else
 * if(ans.equalsIgnoreCase("no"))
 * System.out.println("Thank you for input. Please continue browsing..."); else
 * System.out.println("Answer in yes or no only. Going back to main menu..");
 * 
 * 
 * 
 * break;
 * 
 * 
 * case 4: flist =jimpl.showAllJuice(); if(flist!=null &&
 * flist.isEmpty()!=true){ Iterator<Juice> it =flist.iterator();
 * while(it.hasNext()) { System.out.println(it.next()); System.out.println(
 * "***********__________________________________________*********\n"); }
 * 
 * } else System.out.println("no data for juice deatils at this moment..");
 * break;
 * 
 * case 5: System.out.println(" Enter the juice Id want to show:");
 * juiceId=sc.nextLine(); j=jimpl.getJuiceById(juiceId); if(j!=null) {
 * System.out.println("juice id: "+j.getJuiceId());
 * System.out.println("juice Name :"+j.getJuiceName());
 * 
 * System.out.println("Price of  juice item: "+j.getPrice());
 * System.out.println("Quantity in stock: "+j.getQuantityInStock());
 * 
 * } else System.out.println(" No juice with  this Id  Found ");
 * 
 * break;
 * 
 * default:
 * System.out.println("plz enter only those number  as mention in starting");
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 * }
 * 
 * } }
 */
