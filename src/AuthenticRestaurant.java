package Assignment2;

public class AuthenticRestaurant extends Restaurant{
//	public boolean hasOptionForDiscountOnBill = true;
//	public int RestaurantDiscount = 50; //if bill > 100
	public AuthenticRestaurant(String name , String type){
		super(name,type);
		this.hasOptionForDiscountOnBill = true;
		this.RestaurantDiscount = 50;
		this.RewardYouGet = 25; //25 points per 200/- spent
		this.rewardLowerLimit = 200;
	}

}
