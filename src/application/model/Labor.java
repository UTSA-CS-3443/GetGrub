package application.model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Labor {	
		private StringProperty fName;
		private StringProperty lName;
		private StringProperty paymentType;
		private StringProperty payment;
		private StringProperty hours;
		
		public Labor() {
			
		}
		
		public Labor(String firstN,String lastN, String paymentT, String payment, String hrs) {
			this.fName = new SimpleStringProperty(firstN);
			this.lName = new SimpleStringProperty(lastN);
			this.paymentType = new SimpleStringProperty(paymentT);
			this.payment = new SimpleStringProperty(payment);
			this.hours = new SimpleStringProperty(hrs);
		}
		
		public void setFName(String x) {
			this.fName.set(x);
		}
		
		public String getFName() {
			return fName.get();
		}
		
		public StringProperty fNameProperty() {
			return fName;
		}
		
		public void setLName(String x) {
			this.lName.set(x);
		}
		
		public String getLName() {
			return lName.get();
		}
		
		public StringProperty lNameProperty() {
			return lName;
		}
		
		public void setPaymentType(String x) {
			this.paymentType.set(x);
		}
		
		public String getPaymentType() {
			return paymentType.get();
		}
		
		public StringProperty paymentTypeProperty() {
			return paymentType;
		}
		
		public void setPayment(String x) {
			this.payment.set(x);
		}
		
		public double getPayment() {
			return Double.parseDouble(payment.get());
		}
		
		public StringProperty paymentProperty() {
			return payment;
		}
		
		public void setHours(String x) {
			this.hours.set(x);
		}
		
		public double getHours() {
			return Double.parseDouble(hours.get());
		}
		
		public StringProperty hoursProperty() {
			return hours;
		}
		
		public double payEmployees(String x) {
			String line = null; 
			double total = 0.00;
			try {
	            FileReader fileReader = new FileReader(x);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	           
	            while((line = bufferedReader.readLine()) != null) {
	            	System.out.println(line);
	            	List<String> splitLine = Arrays.asList(line.split(", "));
	            	System.out.println(splitLine.get(2));
	            	if(splitLine.get(3).equals("Hourly")) {
	            		total+= Double.parseDouble(splitLine.get(4)) * Double.parseDouble(splitLine.get(5));
		            	System.out.println(total);
	            	}
	            	else if(splitLine.get(3).equals("Annually")) {
	            		total+= Double.parseDouble(splitLine.get(4))/52;
		            	System.out.println(total);
	            	}
	            	
	            }
	            bufferedReader.close();
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + x + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + x + "'");                  
	        }
			return total;
			
		
	
	}
}
