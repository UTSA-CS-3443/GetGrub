package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.MainPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import application.model.Order;

public class OrderController implements EventHandler<ActionEvent> {
//	@FXML
	private ArrayList<Order> currentOrders;
	
	/**
	 * constructor sets up a Calculator
	 */
	public OrderController() {
		super();
		this.currentOrders = new ArrayList<Order>();
		
	}

	public void returnHomeButton(ActionEvent event) throws IOException
	{	
		Stage window = (Stage)((Button)event.getSource()).getScene().getWindow();
		Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/GetGrubMain.fxml"));
		Scene viewScene = new Scene(viewParent);
		window.setScene(viewScene);
	}
	
	public void AddFoodToTable(ActionEvent event) {
		Button b = (Button)event.getSource();
		
		String str = b.getText();
		if(str.equals("Crunchy Taco W/Beef")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
			
		}
		else if(str.equals("Soft Taco W/Beef")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
		}
		else if(str.equals("Soft Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
		}
		else if(str.equals("Crunchy Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
		}
		else if(str.equals("Beef Burrito")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
		}
		else if(str.equals("Chicken Burrito")) {
			System.out.println(str);
			Order newItem = new Order(5.00,str);
		}
		else {
			System.out.println(str+"Item not found");
		}
	}

	public void AddDrinkToTable(ActionEvent event) {
		Button b = (Button)event.getSource();
		String str = b.getText();
		if(str.equals("Soft Drink")) {
			System.out.println(str);
		}
		else if(str.equals("Water")) {
			System.out.println(str);
		}
		else if(str.equals("Tea")) {
			System.out.println(str);
		}
		else if(str.equals("Lemonade")) {
			System.out.println(str);
		}
		else {
			System.out.println("Item not found");
		}
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
