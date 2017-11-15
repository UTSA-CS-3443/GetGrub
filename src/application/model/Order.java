package application.model;

public class Order {
	private double price;
	private String orderItem;

	public Order(double itemPrice, String itemOrder) {
		this.price = itemPrice;
		this.orderItem = itemOrder;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getItem() {
		return this.orderItem;
	}
}
