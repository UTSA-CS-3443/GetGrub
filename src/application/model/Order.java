package application.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
	
	private StringProperty menuItem;
	private	final DoubleProperty price;
	
	
	public Order(String itemOrder,double itemPrice) {
		this.menuItem = new SimpleStringProperty(itemOrder);
		this.price = new SimpleDoubleProperty(itemPrice);
		
	}
	
	public void setPrice(double iPrice) {
		this.price.set(iPrice);
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public DoubleProperty priceProperty() {
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
