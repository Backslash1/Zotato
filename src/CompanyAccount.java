package Assignment2;

public class CompanyAccount {
	private static double deliveryCharges = 0;
	private static double companyBalance = 0;
	
	public static double getDeliveryCharges() {
		return deliveryCharges;
	}
	public static void updateDeliveryCharges(double deliveryCharges) {
		CompanyAccount.deliveryCharges += deliveryCharges;
	}
	public static double getCompanyBalance() {
		return companyBalance;
	}
	public static void updateCompanyBalance(double companyBalance) {
		CompanyAccount.companyBalance += companyBalance;
	}
	
}
