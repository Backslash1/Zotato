package Assignment2;

public class EliteCustomer extends Customer {
//	private final int deliveryCharges = 0;
//	private final int priceOff = 50; //only when bill >200Rs

	public EliteCustomer(String name , String status) {
		super(name , status);
		this.deliveryCharges = 0;
		this.priceOff = 50;
	}
	
	public double getDeliveryCharges() {
		return deliveryCharges;
	}
	public int getPriceOff() {
		return priceOff;
	}
	
}
