package Assignment2;

public class FoodItem {
	private int uniqueId;
	private String name;
	private double price;
	private int quantity;
	private String category;
	private double offerOnItem; //percentage off on item
	private static int IDCounter = 0;
	public Restaurant restaurant;
	public FoodItem(String name,double price,int quantity,String category,double offer,Restaurant rest) {
		this.setUniqueId(++IDCounter);
		this.setName(name);
		this.setPrice(price);
		this.setQuantity(quantity);
		this.setCategory(category);
		this.setOfferOnItem(offer);
		this.restaurant = rest;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getOfferOnItem() {
		return offerOnItem;
	}
	public void setOfferOnItem(double offerOnItem) {
		this.offerOnItem = offerOnItem;
	}
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
}
