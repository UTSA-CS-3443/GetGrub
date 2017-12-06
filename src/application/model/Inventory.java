package application.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class used to hold the current stock for an inventory item
 * @author Tristan Zaleski 
 *
 */
public class Inventory {
	
	//public displayInv(){
	//}
	//public orderItem(deliveries){}
	//public updateInv(){}
		
	private StringProperty menuItem;
	private DoubleProperty amount;
		
	public Inventory() {
			
	}
	
	/**
	 * constructor
	 * @param itemOrder item name
	 * @param itemAmount item amount
	 */
	public Inventory(String itemOrder,double itemAmount) {
		this.menuItem = new SimpleStringProperty(itemOrder);
		this.amount = new SimpleDoubleProperty(itemAmount);
			
	}
	
	/**
	 * sets stock amount
	 * @param iAmount stock amount
	 */
	public void setAmount(double iAmount) {
		this.amount.set(iAmount);
	}
		
	/**
	 * get stock amount
	 * @return stock amount
	 */
	public double getAmount() {
		return amount.get();
	}
		
	/**
	 * get stock amount in DoubleProperty form
	 * @return stock amount
	 */
	public DoubleProperty amountProperty() {
		return amount;
	}
		
	/** 
	 * set menu item
	 * 
	 * @param iOrder item name
	 */
	public void setMenuItem(String iOrder) {
		this.menuItem.set(iOrder);
	}
	
	/**
	 * returns item name
	 * @return item name
	 */
	public String getMenuItem() {
		return menuItem.get();
	}
		
	
	/**
	 * returns item name in StringProperty form
	 * @return item name
	 */
	public StringProperty menuItemProperty() {
		return menuItem;
	}
		
	
	/**
	 * updates the Inventory.txt file
	 * @param item item name
	 * @param val item amount
	 */
	public void updateInventory(String item, double val) {
		String fileName = "./src/application/data/Inventory.txt";    
	    String line = null;    
	        
	    try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            ArrayList<String> itemList = new ArrayList<String>();
            ArrayList<Double> amountList = new ArrayList<Double>();
            ArrayList<String> newLines = new ArrayList<String>();
        	
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" = ");
               
            	
            	String a = values[0];
            	double b = Double.parseDouble(values[1]);
            	itemList.add(a);
            	amountList.add(b);	
	            }
            bufferedReader.close();
            //Runs through incomeList and expenseList to create a list of profits
            
            int q = 0;
            for(String i: itemList) {
            	if(i.equals(item)) {
            		amountList.set(q, (amountList.get(q)+val));
            		
            	}
            	newLines.add(i+" = "+Double.toString(amountList.get(q)));
            	q++;
            }
            Files.write(Paths.get("./src/application/data/Inventory.txt"), newLines, StandardCharsets.UTF_8);
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
}
