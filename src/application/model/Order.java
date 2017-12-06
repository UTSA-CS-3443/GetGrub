package application.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** holds an order item's name and price
 * 
 * @author John McClure
 *
 */
public class Order {
	
	private StringProperty menuItem;
	private	final StringProperty price;
	
	
	/**
	 * constructor
	 * @param itemOrder item name
	 * @param itemPrice item price
	 */
	public Order(String itemOrder,String itemPrice) {
		this.menuItem = new SimpleStringProperty(itemOrder);
		this.price = new SimpleStringProperty(itemPrice);	
	}
	
	/**
	 * set price for item
	 * @param iPrice item price
	 */
	public void setPrice(String iPrice) {
		this.price.set(iPrice);
	}
	
	/** 
	 * get price for item
	 * @return item price
	 */
	public double getPrice() {
		return Double.parseDouble(price.get());
	}
	
	/** 
	 * get price for item in StringProperty form
	 * @return item price
	 */
	public StringProperty priceProperty() {
		return price;
	}
	
	/**
	 * set item name
	 * @param iOrder item name
	 */
	public void setMenuItem(String iOrder) {
		this.menuItem.set(iOrder);
	}
	
	/**
	 * get item name
	 * @return item name
	 */
	public String getMenuItem() {
		return menuItem.get();
	}
	
	/**
	 * get item name in StringProperty form
	 * @return item name
	 */
	public StringProperty menuItemProperty() {
		return menuItem;
	}
}
