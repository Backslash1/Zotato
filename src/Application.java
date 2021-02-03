package Assignment2;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Hard coded-data
		Scanner input = new Scanner(System.in);
		String Restaurant_names[] = {"Shah","Ravi's","The Chinese","Wang's","Paradise"};
		String Restaurant_types[] = {"Authentic","Others","Authentic","Fast Food","Others"};
		for(int p=0;p<5 ;p++) {
			
			if(Restaurant_types[p].equals("Authentic")) {
				new AuthenticRestaurant(Restaurant_names[p],Restaurant_types[p]);
			}
			else if(Restaurant_types[p].equals("Fast Food")) {
				new FastFoodRestaurant(Restaurant_names[p],Restaurant_types[p]);
			}
			else {
				new OtherRestaurant(Restaurant_names[p],Restaurant_types[p]);
			}
			//new Restaurant(Restaurant_names[p],Restaurant_types[p]);//set its parameters
		}
		
		String Customer_names[] = {"Ram","Sam","Tim","Kim","Jim"};
		String Customer_status[] = {"Elite","Elite","Special","Normal","Normal"};
		for(int u=0;u<5;u++) {
			if(Customer_status[u].equals("Elite")){
				new EliteCustomer(Customer_names[u],Customer_status[u]);
			}
			else if(Customer_status[u].equals("Special")) {
				new SpecialCustomer(Customer_names[u],Customer_status[u]);
			}
			else {
				new OtherCustomer(Customer_names[u],Customer_status[u]); //Normal customer
			}
			//new Customer(Customer_names[u],Customer_status[u]);
		}
		//AuthenticRestaurant obj = new AuthenticRestaurant();
		//Restaurant obj2 = new Restaurant();
		
		//
		while(true) {
			printMenu();
			int menuInput = input.nextInt();
			if(menuInput == 5) {
				break;
			}
			switch(menuInput) {
			
			case 1 : case 2: //both customer and restaurant have same way of login in
				
				User login_user = null; //Common object for both
				
				if(menuInput == 1) {
					System.out.println("Choose Restaurant");
					Restaurant.printRestaurants();
					int restaurantInput = input.nextInt();
					login_user = Restaurant.Restaurants_list.get(restaurantInput-1);
				}
				else {
					System.out.println("Choose User");
					Customer.printCustomers();
					int customerInput = input.nextInt();
					login_user = Customer.Customers_list.get(customerInput-1);
				}
				
				login_user.login(login_user);
				
				break;
			case 3:
				//user details
				System.out.println("1) Customer List");
				System.out.println("2) Restaurant List");
				int menu = input.nextInt();
				if(menu == 1) {
					Customer.printCustomers();
					int customerInput = input.nextInt();
					System.out.print(Customer.Customers_list.get(customerInput-1).getName()+" ");
					System.out.print(" ("+ Customer.Customers_list.get(customerInput-1).getStatus()+")");
					System.out.println(Customer.Customers_list.get(customerInput-1).getCustomerBalance()+Customer.Customers_list.get(customerInput-1).getRewards() +"/-");
					
				}
				else {
					Restaurant.printRestaurants();
					int rI = input.nextInt();
					System.out.print(Restaurant.Restaurants_list.get(rI-1).getName()+" (");
					System.out.print(Restaurant.Restaurants_list.get(rI-1).getType()+") ");
					System.out.println("Rewards " + Restaurant.getRestaurantReward());
				}
				break;
			case 4:
				//Company details
				System.out.print("Total Company balance - INR ");
				System.out.print(CompanyAccount.getCompanyBalance()+"/-");
				System.out.print("Total Delivery Charges Collected - INR ");
				System.out.println(CompanyAccount.getDeliveryCharges()+"/-");
				break;
				
			}
			
		}
		
	}
	public static void printMenu() {
		System.out.println("Welcome to Zotato:");
		System.out.println("1) Enter as Restaurant Owner");
		System.out.println("2) Enter as Customer");
		System.out.println("3) Check User Details");
		System.out.println("4) Company Account details");
		System.out.println("5) Exit");
	}

}
