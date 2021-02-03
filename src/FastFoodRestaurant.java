package Assignment2;

public class FastFoodRestaurant extends Restaurant{
	
	public FastFoodRestaurant(String name , String type){
		super(name,type);
		this.hasOptionForDiscountOnBill = true;
		this.RestaurantDiscount = 0; //we doesn't have any explicit discount offer but we input its percentage 
		this.RewardYouGet = 10; //10 points per 150/- spent
		this.rewardLowerLimit = 150;
	}
}
