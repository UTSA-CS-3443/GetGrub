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
		private StringProperty role;
		private StringProperty paymentType;
		private StringProperty payment;
		private StringProperty hours;
		private StringProperty experience;
		private StringProperty education;
		
		public Labor() {
			
		}
		
		public Labor (String firstN, String lastN, String rol, String exp, String edu)
		{
			this.fName = new SimpleStringProperty(firstN);
			this.lName = new SimpleStringProperty(lastN);
			this.role = new SimpleStringProperty(rol);
			this.experience = new SimpleStringProperty(exp);
			this.education = new SimpleStringProperty(edu);
			
		}
		
		public Labor(String firstN,String lastN, String rol, String paymentT, String payment, String hrs) {
			this.fName = new SimpleStringProperty(firstN);
			this.lName = new SimpleStringProperty(lastN);
			this.role = new SimpleStringProperty(rol);
			this.paymentType = new SimpleStringProperty(paymentT);
			this.payment = new SimpleStringProperty(payment);
			this.hours = new SimpleStringProperty(hrs);
		}
		
		public void hired(String pType, String pay, String hrs) {
			
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
		
		public void setRole(String x) {
			this.role.set(x);
		}
		
		public String getRole() {
			return role.get();
		}
		
		public StringProperty roleProperty() {
			return role;
		}
		
		public void setPaymentType(String x) {
			this.paymentType = new SimpleStringProperty(x);
		}
		
		public String getPaymentType() {
			return paymentType.get();
		}
		
		public StringProperty paymentTypeProperty() {
			return paymentType;
		}
		
		public void setPayment(String x) {
			this.payment = new SimpleStringProperty(x);
		}
		
		public double getPayment() {
			return Double.parseDouble(payment.get());
		}
		
		public StringProperty paymentProperty() {
			return payment;
		}
		
		public void setHours(String x) {
			this.hours = new SimpleStringProperty(x);
		}
		
		public double getHours() {
			return Double.parseDouble(hours.get());
		}
		
		public StringProperty hoursProperty() {
			return hours;
		}
		
		public void setExperience(String x) {
			this.experience.set(x);
		}
		
		public String getExperience() {
			return experience.get();
		}
		
		public StringProperty experienceProperty() {
			return experience;
		}
		
		public void setEducation(String x) {
			this.education.set(x);
		}
		
		public String getEducation() {
			return education.get();
		}
		
		public StringProperty educationProperty() {
			return education;
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
