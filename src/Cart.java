package Assignment2;

import java.util.ArrayList;

public class Cart {
	public FoodItem foodItem;
	public int quantity;
	public ArrayList<FoodItem> CartFood_list = new ArrayList<FoodItem>();
	public ArrayList<Restaurant> RestaurantFrom_list = new ArrayList<Restaurant>();
	public ArrayList<Integer> Quantity_list = new ArrayList<Integer>();
	
	
	public Restaurant restaurant ;
	public Customer customer;
	public Cart(FoodItem item , int quantity , Restaurant rest,Customer customer) {
//		this.foodItem = item;
//		this.quantity = quantity;
//		this.CartFood_list.add(foodItem); //adding every food to cart
//		this.RestaurantFrom_list.add(rest);//adding every rest to cart alternate
//		this.Quantity_list.add(quantity);//adding alternate quantities
//		
//		this.restaurant = rest;
//		this.customer = customer;
	}
	public Cart() {
		
	}
	
	public void printCartItems() {
		for(int i = 0 ; i <CartFood_list.size() ;i++) {
			//System.out.print(CartFood_list.get(i).getUniqueId()+" - ");
			System.out.print(i+1+" - ");
			System.out.print(CartFood_list.get(i).restaurant.getName()+" - ");
			System.out.print(CartFood_list.get(i).getName()+" - ");
			System.out.print(CartFood_list.get(i).getPrice()+" - ");
			System.out.print(Quantity_list.get(i)+" - ");
			System.out.println(CartFood_list.get(i).getOfferOnItem()+"% off");
			
		}
	}
	public void empty() {
		this.CartFood_list = null;
		this.Quantity_list = null;
		this.RestaurantFrom_list = null;
		this.foodItem = null;
		this.customer = null;
		this.quantity = 0;
	}
	public void addToCart(FoodItem item , int quantity , Restaurant rest,Customer customer) {
		this.foodItem = item;
		this.quantity = quantity;
		this.CartFood_list.add(foodItem); //adding every food to cart
		this.RestaurantFrom_list.add(rest);//adding every rest to cart alternate
		this.Quantity_list.add(quantity);//adding alternate quantities
		
		this.restaurant = rest;
		this.customer = customer;
	}
	
	public double calculateOrderValue() {
		//System.out.println("name of customer "+customer.getName());
		//System.out.println("price off "+customer.priceOff);
		//System.out.println();
		double OriginalBill = totalBillWithoutDisc();
		double sumOfindividualFoodDiscount = getIndividualItemDiscount();
		OriginalBill -= sumOfindividualFoodDiscount;
		OriginalBill -= restBillDiscount(OriginalBill);
		if(OriginalBill >= 100) {
			//If authentic then it will subtract 
			OriginalBill -= RestaurantFrom_list.get(0).RestaurantDiscount;
		}
		
		if(OriginalBill > 200) {
			OriginalBill -= customer.priceOff;
		}
		//System.out.println("Total after discounts "+OriginalBill);
		//System.out.println("Delivery charges "+customer.deliveryCharges);
		return OriginalBill;
	}
	public double getIndividualItemDiscount() {
		double counter = 0;
		for(int i = 0 ; i<CartFood_list.size();i++) {
			counter += foodDiscount(i);
		}
		return counter;
	}
	public double totalBillWithoutDisc() {
		double counter = 0;
		for(int i = 0 ; i<CartFood_list.size() ;i++) {
			counter += CartFood_list.get(i).getPrice()*Quantity_list.get(i);
		}
		System.out.println("Tot Bill "+counter);
		//foodDiscount(0);
		//restBillDiscount(0, counter -foodDiscount(0));
		return counter;
	}
	public double foodDiscount(int i) {
		double a = CartFood_list.get(i).getPrice()*Quantity_list.get(i);
		double fraction = CartFood_list.get(i).getOfferOnItem()/100;
		double discount = fraction*a;
		System.out.println("Food dicount "+discount);
		return discount;
	}
	public double restBillDiscount( double bill) {
		//RestaurantFrom_list.get(i);
		//This discount is entered by user
		double a = Restaurant.Restaurants_list.get(0).discountOnBill;
		
		System.out.println("Rest bill discount "+(a/100)*bill);
		return (a/100)*bill;
	}
//	public double restaurantSpecificDiscount(int i) {
//		//It is just for authentic rest == 50
//		//int a = Restaurant.Restaurants_list.get(i).RestaurantDiscount;
//		double a = RestaurantFrom_list.get(0).RestaurantDiscount;
//		return a;
//	}
//	public double getDiscounts() {
//		//item dicount
//		//apply rest discount(percentage)
//		//apply rest discount(50/- for authentic)
//		//apply customer disc(depends on customer category)
//		//determine reward point
//		//add delivery charge
//		double discount = 0;
//		CartFood_list.get(0).getOfferOnItem();
//		int a = RestaurantFrom_list.get(0).RestaurantDiscount;
//		int b = customer.priceOff;
//		discount = discount + (CartFood_list.get(0).getOfferOnItem()/100)*CartFood_list.get(0).getPrice()*Quantity_list.get(0);
//		discount = discount + (a/100)*discount;
//		
//		return discount;
//	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int calculatePreValue() {
		return 0;
	}

}
