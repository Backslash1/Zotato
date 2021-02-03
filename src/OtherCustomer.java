package Assignment2;

public class OtherCustomer extends Customer{
	
	public OtherCustomer(String name , String status) {
		super(name , status);
		this.deliveryCharges = 40;
		this.priceOff = 0;
	}
	
	public double getDeliveryCharges() {
		return deliveryCharges;
	}
	public int getPriceOff() {
		return priceOff;
	}
	
}
