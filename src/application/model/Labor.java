package application.model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Class used to store information about employees and applicants
 * 
 * @author John McClure
 *
 */
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
		
		/**
		 * constructor for applicant
		 * @param lastN last name
		 * @param firstN first name
		 * @param rol role
		 * @param exp experience
		 * @param edu education
		 */
		public Labor (String lastN, String firstN, String rol, String exp, String edu)
		{
			this.fName = new SimpleStringProperty(firstN);
			this.lName = new SimpleStringProperty(lastN);
			this.role = new SimpleStringProperty(rol);
			this.experience = new SimpleStringProperty(exp);
			this.education = new SimpleStringProperty(edu);
		}
		
		/**
		 * constructor for employee
		 * @param lastN last name
		 * @param firstN first name
		 * @param rol role
		 * @param paymentT payment type
		 * @param payment payment
		 * @param hrs hours
		 */
		public Labor(String lastN, String firstN, String rol, String paymentT, String payment, String hrs) {
			this.fName = new SimpleStringProperty(firstN);
			this.lName = new SimpleStringProperty(lastN);
			this.role = new SimpleStringProperty(rol);
			this.paymentType = new SimpleStringProperty(paymentT);
			this.payment = new SimpleStringProperty(payment);
			this.hours = new SimpleStringProperty(hrs);
		}
		
		/**
		 * change applicant to employee
		 * @param pType payment type
		 * @param pay payment
		 * @param hrs hours
		 */
		public void hired(String pType, String pay, String hrs) {
			
		}
		
		/**
		 * set first name
		 * @param x first name
		 */
		public void setFName(String x) {
			this.fName.set(x);
		}
		
		/**
		 * get first name
		 * @return first name
		 */
		public String getFName() {
			return fName.get();
		}
		
		/**
		 * get first name in StringProperty form
		 * @return
		 */
		public StringProperty fNameProperty() {
			return fName;
		}
		
		/**
		 * set last name 
		 * @param x last name
		 */
		public void setLName(String x) {
			this.lName.set(x);
		}
		
		/**
		 * get last name
		 * @return last name
		 */
		public String getLName() {
			return lName.get();
		}
		
		/**
		 * get last name in StringProperty form
		 * @return last name
		 */
		public StringProperty lNameProperty() {
			return lName;
		}
		
		/**
		 * set role
		 * @param x role
		 */
		public void setRole(String x) {
			this.role.set(x);
		}
		
		/**
		 * get role
		 * @return role
		 */
		public String getRole() {
			return role.get();
		}
		
		/**
		 * get role in StringProperty form
		 * @return role
		 */
		public StringProperty roleProperty() {
			return role;
		}
		
		/**
		 * set payment type
		 * @param x payment type
		 */
		public void setPaymentType(String x) {
			this.paymentType = new SimpleStringProperty(x);
		}
		
		/**
		 * get payment type
		 * @return payment type
		 */
		public String getPaymentType() {
			return paymentType.get();
		}
		
		/** 
		 * get payment type in StringProperty form
		 * @return payment type
		 */
		public StringProperty paymentTypeProperty() {
			return paymentType;
		}
		
		/**
		 * set payment
		 * @param x payment
		 */
		public void setPayment(String x) {
			this.payment = new SimpleStringProperty(x);
		}
		
		/**
		 * get payment
		 * @return payment
		 */
		public double getPayment() {
			return Double.parseDouble(payment.get());
		}
		
		/**
		 * get payment in StringProperty form
		 * @return payment
		 */
		public StringProperty paymentProperty() {
			return payment;
		}
		
		/**
		 * set hours
		 * @param x hours
		 */
		public void setHours(String x) {
			this.hours = new SimpleStringProperty(x);
		}
		
		/**
		 * get hours
		 * @return hours
		 */
		public double getHours() {
			return Double.parseDouble(hours.get());
		}
		
		/**
		 * get hours in StringProperty form
		 * @return
		 */
		public StringProperty hoursProperty() {
			return hours;
		}
		
		/**
		 * set experience
		 * @param x experience
		 */
		public void setExperience(String x) {
			this.experience.set(x);
		}
		
		/**
		 * get experience
		 * @return experience
		 */
		public String getExperience() {
			return experience.get();
		}
		
		/**
		 * get experience in StringProperty form
		 * @return experience
		 */
		public StringProperty experienceProperty() {
			return experience;
		}
		
		/**
		 * set education
		 * @param x education
		 */
		public void setEducation(String x) {
			this.education.set(x);
		}
		
		/**
		 * get education
		 * @return education
		 */
		public String getEducation() {
			return education.get();
		}
		
		/**
		 * get education in StringProperty form
		 * @return education
		 */
		public StringProperty educationProperty() {
			return education;
		}
		
		/**
		 * calculates the total expense for all employees
		 * @param x file name
		 * @return employees total compensation
		 */
		public double payEmployees(String x) {
			String line = null; 
			double total = 0.00;
			try {
	            FileReader fileReader = new FileReader(x);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	           
	            while((line = bufferedReader.readLine()) != null) {
	            	//System.out.println(line);
	            	List<String> splitLine = Arrays.asList(line.split(", "));
	            	//System.out.println(splitLine.get(2));
	            	if(splitLine.get(3).equals("Hourly")) {
	            		total+= Double.parseDouble(splitLine.get(4)) * Double.parseDouble(splitLine.get(5));
		            	//System.out.println(total);
	            	}
	            	else if(splitLine.get(3).equals("Annually")) {
	            		total+= Double.parseDouble(splitLine.get(4))/52;
		            	//System.out.println(total);
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
