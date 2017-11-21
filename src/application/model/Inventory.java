package application.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {
	
	//public displayInv(){
	//}
	//public orderItem(deliveries){}
	//public updateInv(){}
		
		private StringProperty menuItem;
		private	final DoubleProperty amount;
		
		
		
		public Inventory(String itemOrder,double itemAmount) {
			this.menuItem = new SimpleStringProperty(itemOrder);
			this.amount = new SimpleDoubleProperty(itemAmount);
			
		}
		
		public void setAmount(double iAmount) {
			this.amount.set(iAmount);
		}
		
		public double getAmount() {
			return amount.get();
		}
		
		public DoubleProperty amountProperty() {
			return amount;
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
