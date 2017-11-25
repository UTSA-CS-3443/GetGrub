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

public class Inventory {
	
	//public displayInv(){
	//}
	//public orderItem(deliveries){}
	//public updateInv(){}
		
	private StringProperty menuItem;
	private DoubleProperty amount;
		
	public Inventory() {
			
	}
		
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
		
	public void updateInvetory(String item, double val) {
		String fileName = "./src/application/data/Invetory.txt";    
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
            Files.write(Paths.get("./src/application/data/Invetory.txt"), newLines, StandardCharsets.UTF_8);
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
}
