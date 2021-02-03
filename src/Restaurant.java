package Assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Restaurant implements User {
	private String name;
	private String type;
	private FoodItem food;
	private int RestaurantIndex;
	private static int RestaurantReward = 0; //
	
	public static int getRestaurantReward() {
		return RestaurantReward;
	}
	public static void updateRestaurantReward(int restaurantReward) {
		RestaurantReward += restaurantReward;
	}
	public double discountOnBill = 0;
	
	public boolean hasOptionForDiscountOnBill;//subclass will assign these values
	public int RestaurantDiscount ; 
	public int RewardYouGet ; // reward from the restaurant type
	public int rewardLowerLimit;
	
	private static int RestaurantCounter = 0;
	//public HashMap<Integer, FoodItem> FoodItems_list = new HashMap<>(); 
	
	public ArrayList<FoodItem> FoodItems_list = new ArrayList<FoodItem>();
	
	public static ArrayList<Restaurant> Restaurants_list = new ArrayList<Restaurant>();
	//public ArrayList<FoodItem> FoodItems_list = new ArrayList<FoodItem>();
	
	{
		Restaurants_list.add(this);
	}
	public Restaurant() {
		
	}
	public Restaurant(String name , String type) {
		this.setName(name);
		this.setType(type);
		this.setRestaurantIndex(++RestaurantCounter);
	}
	@Override
	public void login(User restaurant) {
		System.out.println("HI restaurant");
		//Restaurant rest = (Restaurant)restaurant;
		
		this.enterRestaurantMenu();
		
	}
	public void enterRestaurantMenu() {

		Scanner input = new Scanner(System.in);
		
		//Restaurant rest = (Restaurant)restaurant;
	
		while(true) {
			
			System.out.println("Welcome "+this.getName());
			System.out.println("1) Add item");
			System.out.println("2) Edit item");
			System.out.println("3) Print Rewards");
			System.out.println("4) Discount on bill value");
			System.out.println("5) Exit");
			
			int menuItem = input.nextInt();
			input.nextLine();

			switch(menuItem) {
			
			case 1:
				//System.out.println("Restaurant reward you get "+this.RewardYouGet);
				System.out.println("Enter food item details");
				System.out.println("Food name:");
				String item_name = input.nextLine();

				System.out.println("Item price:");
				double item_price = input.nextDouble();
				System.out.println("item quantity:");
				int item_quantity = input.nextInt();
				System.out.println("Item category:");
				String item_category = input.next();
				System.out.println("Offer:");
				double offer_percentage = input.nextDouble();
				//Food item object ---Composition relation
				FoodItem newFoodItem = new FoodItem(item_name,item_price,item_quantity,item_category,offer_percentage,this);
				//FoodItems_list.add(newFoodItem); //list of all the food items separately
				//FoodItems_list.put(newFoodItem.getUniqueId(), newFoodItem);
				FoodItems_list.add(newFoodItem);
				System.out.print(Integer.toString(newFoodItem.getUniqueId())+" ");
				System.out.print(newFoodItem.getName()+" ");
				System.out.print(Double.toString(newFoodItem.getPrice())+" ");
				System.out.print(Integer.toString(newFoodItem.getQuantity())+" ");
				System.out.print(Double.toString(newFoodItem.getOfferOnItem())+"%"+" off"+" ");
				System.out.println(newFoodItem.getCategory());
				
				break;
			case 2:
				if(FoodItems_list.size() == 0) {
					System.out.println("No food items to edit...");
					continue;
				}
				System.out.println("Choose item by code");
				printFoodDetails(this.getName());

				int code_id = input.nextInt()-1;// -1 for index in array
				input.nextLine(); //for consuming \n
				System.out.println("Choose an attribute to edit:");
				printEditMenu();
				int menuOption = input.nextInt();
				input.nextLine();
				if(menuOption == 1) {
					System.out.println("Enter the new name - ");
					String newName = input.nextLine();
					FoodItems_list.get(code_id).setName(newName);
				}
				else if(menuOption == 2) {
					System.out.println("Enter the new price - ");
					double newPrice = input.nextDouble();
					FoodItems_list.get(code_id).setPrice(newPrice);
				}
				else if(menuOption == 3) {
					System.out.println("Enter the new number of quantities - ");
					int newQuantity = input.nextInt();
					FoodItems_list.get(code_id).setQuantity(newQuantity);
				}
				else if(menuOption ==  4) {
					System.out.println("Enter the new category - ");
					String newCategory = input.next();
					FoodItems_list.get(code_id).setCategory(newCategory);
				}
				else if(menuOption == 5) {
					System.out.println("Enter the new offer - ");
					double newOffer = input.nextDouble();
					FoodItems_list.get(code_id).setOfferOnItem(newOffer);
				}
				break;
			case 3:
				System.out.println("Reward points : " +RestaurantReward);
				break;
			case 4:
				//discount on bill value
				double discountInput = 0;
				if(this.hasOptionForDiscountOnBill) {
					System.out.println("Offer on bill value - ");
					discountInput = input.nextDouble();
				}
				else {
					System.out.println("Discount option not available...");
				}
				discountOnBill = discountInput;
				break;
			}
			if(menuItem == 5) {
				break;
			}
		}
	}
	private void printFoodDetails(String name) {
		for(int i =0;i<FoodItems_list.size();i++) {
			//System.out.print(Integer.toString(FoodItems_list.get(i+1).getUniqueId())+" ");
			System.out.print(i+1+" ");
			System.out.print(name+" - "+ FoodItems_list.get(i).getName()+" "+FoodItems_list.get(i).getPrice());
			System.out.print(FoodItems_list.get(i).getQuantity()+" ");
			System.out.print(FoodItems_list.get(i).getOfferOnItem()+"% off ");
			System.out.println(FoodItems_list.get(i).getCategory());
		}
	}
	private static void printEditMenu() {
		System.out.println("1) Name");
		System.out.println("2) Price");
		System.out.println("3) Quantity");
		System.out.println("4) Category");
		System.out.println("5) Offer");
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRestaurantIndex(int id) {
		this.RestaurantIndex = id;
	}
	public int getRestaurantIndex() {
		return this.RestaurantIndex;
	}
	public int getCounter() {
		return Restaurant.RestaurantCounter;
	}
	public FoodItem getFood() {
		return food;
	}
	public void setFood(FoodItem food) {
		this.food = food;
	}
	public static void printRestaurants() {
		
		for(int i =0; i<Restaurant.Restaurants_list.size();i++) {
			System.out.print(Integer.toString(i+1)+") " + Restaurant.Restaurants_list.get(i).getName());
			System.out.println(" ("+Restaurant.Restaurants_list.get(i).getType()+")");
		}
		
	}
}
