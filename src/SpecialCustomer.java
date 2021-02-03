package Assignment2;

public class SpecialCustomer extends Customer{
	
	public SpecialCustomer(String name , String status) {
		super(name , status);
		this.deliveryCharges = 20;
		this.priceOff = 25;
	}
	
	public double getDeliveryCharges() {
		return deliveryCharges;
	}
	public int getPriceOff() {
		return priceOff;
	}
	
}
