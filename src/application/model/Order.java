package application.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
	
	private StringProperty menuItem;
	private	final StringProperty price;
	
	
	
	public Order(String itemOrder,String itemPrice) {
		this.menuItem = new SimpleStringProperty(itemOrder);
		this.price = new SimpleStringProperty(itemPrice);	
	}
	
	public void setPrice(String iPrice) {
		this.price.set(iPrice);
	}
	
	public double getPrice() {
		return Double.parseDouble(price.get());
	}
	
	public StringProperty priceProperty() {
		return price;
	}
	
	public void setMenuItem(String iOrder) {
		this.menuItem.set(iOrder);
	}
	
	public String getMenuItem() {
		return menuItem.get();
	}
	
	public StringProperty menuItemProperty() {
		return menuItem;
	}
}
