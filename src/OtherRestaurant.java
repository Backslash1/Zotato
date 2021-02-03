package Assignment2;


public class OtherRestaurant extends Restaurant{
	public OtherRestaurant(String name , String type) {
		super(name,type);
		this.hasOptionForDiscountOnBill = false;
		this.RestaurantDiscount = 0;
		this.RewardYouGet = 5; //5 points per 100/- spent
		this.rewardLowerLimit = 100;
	}
}
