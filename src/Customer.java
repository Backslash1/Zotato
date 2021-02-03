package Assignment2;

import java.util.ArrayList;
import java.util.Scanner;

//import com.sun.jdi.connect.Connector.SelectedArgument;

public class Customer implements User{
	public ArrayList<Order> Orders_list = new ArrayList<Order>();
	
	private String name;
	private String status;
	private int CustomerIndex;
	private static int customerCounter = 0;
	private double customerBalance = 1000; //initially everyone starts with 1000 Rs
	private double rewards; //rewards of the customer
	
	public double deliveryCharges ;
	public int priceOff ; //only when bill >200Rs
	
	public Cart newCart; //This is the cart for a particular customer//Composition relation
	
	
	public static ArrayList<Customer> Customers_list = new ArrayList<Customer>();
	
	{
		Customers_list.add(this);
	}
	public Customer() {
		//default constructor for extended classes
	}
	public Customer(String name , String status) {
		this.setName(name);
		this.setStatus(status);
		this.setCustomerIndex(++customerCounter);
		newCart = new Cart(); //Cart object instantiated for every customer...later we will add in it
	}
	@Override
	public void login(User customer) {
		this.enterCustomerMenu2();
		
	}
	public void enterCustomerMenu() {

		Scanner input = new Scanner(System.in);
		int count = 0;
		int case1 = 0;
		while(true) {
			
			
			
			printOptionsMenu2();
			
			int menuOption = input.nextInt();
			
			switch(menuOption) {
			
			case 1:
				System.out.println("Choose Restaurant");
				
				printRestaurants();
				
				int restaurantNo = input.nextInt();
				Restaurant rest =  Restaurant.Restaurants_list.get(restaurantNo+1); //This is the restaurant

				System.out.println("Choose item by code");
				printItemsInrestaurant(rest);

				int selectItem = input.nextInt();//item id 

				System.out.println("Enter item quantity - ");
				int quantity = input.nextInt();
				//Cart object create
				//newCart = new Cart(Restaurant.FoodItems_list.get(selectItem), quantity,rest,this);
				//newCart.setFoodItem(Restaurant.FoodItems_list.get(selectItem));
				//newCart.setQuantity(quantity);
				//newCart.setRestaurant(rest);
				//newCart.setCustomer(this);
				newCart.addToCart(rest.FoodItems_list.get(selectItem),quantity,rest,this);
				//Add food item to cart //and add the total quantities
				System.out.println("Items added to cart");
					
				
				break;
			case 2:
				//cart checkout
				//Only order from single restaurant in single order
				System.out.println("Items in cart - ");
				
				newCart.printCartItems();
				
			}
			

		}
		
	}
	
	public void enterCustomerMenu2() {
		boolean exitBool = false;
		int glob_rest_no = -1;
		Scanner in = new Scanner(System.in);
		printOptionsMenu1();
		int menuOption1 = in.nextInt();
		switch(menuOption1) {
		case 1:
			System.out.println("Choose Restaurant");
			printRestaurants();	
			int restaurant = in.nextInt();
			glob_rest_no = restaurant; //assigning glob
			Restaurant rest = Restaurant.Restaurants_list.get(restaurant-1);
			//System.out.println("Name of rest "+rest.getName());
			System.out.println("Choose item by code");
			printItemsInrestaurant(rest);
						
			int item = in.nextInt();
			System.out.println("Enter item quantity - ");
			int quantity = in.nextInt();
			//
			if(quantity <= rest.FoodItems_list.get(item-1).getQuantity()) {
				//System.out.println("Quantity change");
				int have = rest.FoodItems_list.get(item-1).getQuantity();
				//System.out.println("Already quantity "+have);
				rest.FoodItems_list.get(item-1).setQuantity(have - quantity);
				newCart.addToCart(rest.FoodItems_list.get(item-1),quantity,rest,this);
			}
			else {
				System.out.println("Quantity exceeded");
				break;
			}
			//
			//newCart.addToCart(rest.FoodItems_list.get(item-1),quantity,rest,this);
			System.out.println("Item added to cart");
			break;
		case 2:
			//check out cart before even choosing restaurant
			//kind of redundant....or we will technically never reach here
			break;
		case 3:
			System.out.println("Reward won - "+ this.rewards);
			break;
		case 4:
			//print recent orders
			printRecentOrders();
			break;
		case 5:
			//exit
			exitBool = true;
			break;
		}
		if(exitBool == true) {
			return;
		}
		Restaurant rest = Restaurant.Restaurants_list.get(glob_rest_no-1);
		while(true) {
			printOptionsMenu2();
			int menuOption = in.nextInt();
			switch(menuOption) {
			case 1:
				System.out.println("Choose item by code");
//				Restaurant rest = Restaurant.Restaurants_list.get(glob_rest_no-1);
				printItemsInrestaurant(rest);

				int selectItem = in.nextInt();//item id 

				System.out.println("Enter item quantity - ");
				int quantity = in.nextInt();
				if(quantity <= rest.FoodItems_list.get(selectItem-1).getQuantity()) {
					//System.out.println("Quantity change");
					int have = rest.FoodItems_list.get(selectItem-1).getQuantity();
					//System.out.println("Already quantity "+have);
					rest.FoodItems_list.get(selectItem-1).setQuantity(have - quantity);
					newCart.addToCart(rest.FoodItems_list.get(selectItem-1),quantity,rest,this);
				}
				else {
					System.out.println("Quantity exceeded");
					break;
				}
				//Add food item to cart //and add the total quantities
				System.out.println("Items added to cart");
				//System.out.println("Customer price OFf "+this.priceOff);
				break;
			case 2:
				//checkout of cart
				System.out.println("Items in cart - ");
				newCart.printCartItems();
				//double finalBill = newCart.calculateOrderValue();
				double finalBill = getBill();
				//delivery charges not included
				System.out.println("Delivery charge - "+this.deliveryCharges +"/-");
				System.out.println("Total order value(including delivery charges) - "+(finalBill+this.deliveryCharges)+"/-");
				System.out.println("1) Proceed to checkout");
				int proceedToPay = in.nextInt();
				int reward2 = 0;
				finalBill += this.deliveryCharges;
				
				if(proceedToPay == 1) {
					
					while(true) {
						if(finalBill > this.customerBalance +this.getRewards()) {
							//delete item from
							newCart.printCartItems();
							System.out.println("Select to delete item");
							int delete = in.nextInt();
							
							newCart.CartFood_list.remove(delete-1);
							newCart.Quantity_list.remove(delete-1);
							newCart.RestaurantFrom_list.remove(delete-1);
							
							int a = rest.FoodItems_list.get(delete-1).getQuantity();
							rest.FoodItems_list.get(delete-1).setQuantity(newCart.Quantity_list.get(a+delete));
							System.out.println("Deleted item...");
							finalBill = this.deliveryCharges + getBill();
						}
						else {
							break;
						}
					}
					reward2 = rest.RewardYouGet*(int)(Math.floor(finalBill/rest.rewardLowerLimit));
					//System.out.println("Reward You Get "+rest.RewardYouGet);
					//System.out.println("Lower limit "+rest.rewardLowerLimit);
					//System.out.println("Reward "+this.getName()+" "+reward2);
					double finalBill2 = finalBill;
					
					this.setRewards(reward2); //update reward
					//update customer balance first reward
					if(this.getRewards() >= finalBill) {
						//System.out.println("Here");
						this.rewards = this.rewards - finalBill;
					}
					else if(finalBill< this.customerBalance+this.rewards) {
						finalBill -= this.rewards;
						this.customerBalance -= finalBill;
						this.rewards = 0;
					}
					//this.customerBalance -= finalBill;
					//System.out.println("Final bill " +finalBill2);
					this.setRewards(reward2);
					Restaurant.updateRestaurantReward(reward2);
					double comppppppp =(double)(finalBill2-this.deliveryCharges)/100;
					//System.out.println("Company ttt "+ comppppppp);
					//System.out.println("Company ka fayda "+ (1/100)*(finalBill - this.deliveryCharges));
					CompanyAccount.updateCompanyBalance(comppppppp);
					CompanyAccount.updateDeliveryCharges(this.deliveryCharges);
					System.out.println("Items brought successfully...");
					Order order = new Order();
					order.delivery = this.deliveryCharges;
					
					newCart = null; //set to null
					newCart = new Cart();
				}
				break;
			case 3:
				System.out.println("Rewards won - "+this.rewards);
				break;
			case 4:
				printRecentOrders();
				break;
			}
			if(menuOption == 5) {
				//if exiting then set cart to null
				newCart = null;
				break;
			}
		}
		
	}
	public void AllOrders() {
		for(int i =0; i<newCart.CartFood_list.size();i++) {
			Order order = new Order();
			order.restaurant = newCart.CartFood_list.get(i).restaurant;
			order.name = newCart.CartFood_list.get(i).getName();
			order.price = newCart.CartFood_list.get(i).getPrice();
			order.quantity = newCart.Quantity_list.get(i);
			order.delivery = this.deliveryCharges;
			Orders_list.add(order);
		}
	}
	public void printRecentOrders() {
		for(int i = 0 ; i <Orders_list.size();i++) {
			System.out.print("Brought item "+ Orders_list.get(i).name+" ");
			System.out.print("Quantity "+Orders_list.get(i).quantity+" ");
			System.out.print("Price "+Orders_list.get(i).price+" ");
			System.out.print("from Restaurant "+Orders_list.get(i).restaurant.getName()+" ");
			System.out.println("Delivery charge "+ Orders_list.get(i).delivery);
		}
	}
	public double getCustomerBalance() {
		return customerBalance;
	}
	public void setCustomerBalance(double customerBalance) {
		this.customerBalance = customerBalance;
	}
	public double getBill() {
		return newCart.calculateOrderValue();
	}
	public double getRewards() {
		return rewards;
	}
	public void setRewards(double rewards) {
		this.rewards += rewards;
	}
//	public int computeDeliveryCharge() {
//		return 0;
//	}
	public void printItemsInrestaurant(Restaurant rest){
		//System.out.println("Size "+rest.FoodItems_list.size());
		for(int i = 0 ; i < rest.FoodItems_list.size() ; i++) {
			//System.out.println("i "+i);
			//System.out.print(Integer.toString(rest.FoodItems_list.get(i).getUniqueId())+" ");
			System.out.print(i+1+" ");
			System.out.print(rest.getName()+" - "+ rest.FoodItems_list.get(i).getName()+" ");
			System.out.print(rest.FoodItems_list.get(i).getQuantity()+" ");
			System.out.print(rest.FoodItems_list.get(i).getOfferOnItem()+"% off ");
			System.out.println(rest.FoodItems_list.get(i).getCategory());
		}
	}
	public void printOptionsMenu1() {
		System.out.println("Welcome "+this.getName());
		System.out.println("Customer Menu");
		System.out.println("1) Select Restaurant");
		System.out.println("2) Checkout Cart");
		System.out.println("3) Reward won");
		System.out.println("4) Print the recent orders");
		System.out.println("5) Exit");
	}
	public void printOptionsMenu2() {
		System.out.println("Welcome "+this.getName()+" "+this.getStatus()+" "+this.deliveryCharges);
		System.out.println("1) Search item");
		System.out.println("2) checkout cart");
		System.out.println("3) Reward won");
		System.out.println("4) print the recent orders");
		System.out.println("5) Exit");
	}
	public void printRestaurants() {
		for(int i = 0 ; i< Restaurant.Restaurants_list.size() ; i++) {
			System.out.print(""+Restaurant.Restaurants_list.get(i).getRestaurantIndex()+") ");
			//System.out.println(""+(i+1)+"");
			System.out.print(Restaurant.Restaurants_list.get(i).getName());
			System.out.println(" (" + Restaurant.Restaurants_list.get(i).getType()+")");
			
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCustomerIndex(int id) {
		this.CustomerIndex = id;
	}
	public int getCustomerIndex() {
		return this.CustomerIndex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static void printCustomers() {
		for(int i =0; i<Customer.Customers_list.size();i++) {
			System.out.print(Integer.toString(i+1)+") " + Customer.Customers_list.get(i).getName());
			System.out.println(" ("+Customer.Customers_list.get(i).getStatus()+")");
		}
	}
}
