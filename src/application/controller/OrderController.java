package application.controller;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import application.model.Order;

public class OrderController implements EventHandler<ActionEvent> {
	
	
	// TABLE
	@FXML
	private TableView<Order> orderTable;
	
	@FXML
	private TableColumn<Order, String> menuItem;
	
	@FXML
	private TableColumn<Order, Double> price;
	
	
	
	// LABELS
	@FXML
	private Label taxLabel;
	
	@FXML 
	private Label totalLabel;
	
	@FXML 
	private Label subTotalLabel;
	
	
	
	//Local class variables
	private double beef = 0;
	private double chicken = 0;
	private double hardShell = 0;
	private double tortilla = 0;
	private double lettuce = 0;
	private double cheese = 0;
	private double tea = 0;
	private double lemons = 0;
	
	
	//Used for table and order total calculations
	private ArrayList<Order> currentOrders;
	private final ObservableList<Order> orders = FXCollections.observableArrayList();
	
	
	//Tax amount based on Texas
	private final double taxAmount = .0825;
	

	public OrderController() {
		super();
		this.currentOrders = new ArrayList<Order>();		
	}
	
	@FXML
	public void initialize() {
		menuItem.setCellValueFactory(cellData -> cellData.getValue().menuItemProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
		orderTable.setItems(getOrderData());
		orderTable.setPlaceholder(new Label(""));
		
	}
	
	public ObservableList<Order> getOrderData(){
		return this.orders;
	}
	
	public void returnHomeButton(ActionEvent event) throws IOException
	{	
		Stage window = (Stage)((Button)event.getSource()).getScene().getWindow();
		Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/GetGrubMain.fxml"));
		Scene viewScene = new Scene(viewParent);
		window.setScene(viewScene);
	}
	
	
	public void addFoodToTable(ActionEvent event) {
		Button b = (Button)event.getSource();
		
		String str = b.getText();
		if(str.equals("Crunchy Taco W/Beef")) {
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.beef += .25;
			this.hardShell += 1;
			this.lettuce += .1;
			this.cheese += .1;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug				System.out.print(newItem.getPrice());
//Debug			System.out.println();	
			
		}
		
		else if(str.equals("Soft Taco W/Beef")) {
			System.out.println(str);
			Order newItem = new Order(str,2.00);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.beef += .25;
			this.tortilla += 1;
			this.lettuce += .1;
			this.cheese += .1;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Soft Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(str,2.00);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.chicken += .25;
			this.tortilla += 1;
			this.lettuce += .1;
			this.cheese += .1;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Crunchy Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.chicken += .25;
			this.hardShell += 1;
			this.lettuce += .1;
			this.cheese += .1;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Beef Burrito")) {
			System.out.println(str);
			Order newItem = new Order(str,2.99);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.beef += .5;
			this.tortilla += 1;
			
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Chicken Burrito")) {
			System.out.println(str);
			Order newItem = new Order(str,2.79);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.chicken += .5;
			this.tortilla += 1;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else {
			System.out.println(str+"Item not found");
		}
		
	}

	public void addDrinkToTable(ActionEvent event) {
		Button b = (Button)event.getSource();
		String str = b.getText();
		if(str.equals("Soft Drink")) {
			System.out.println(str);
			Order newItem = new Order(str,1.75);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Water")) {
			System.out.println(str);
			Order newItem = new Order(str,.25);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Tea")) {
			System.out.println(str);
			Order newItem = new Order(str,1.00);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.tea += .21;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();
			
		}
		
		else if(str.equals("Lemonade")) {
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			currentOrders.add(newItem);
			orders.add(newItem);
			updateTotalLabels();
			this.lemons += 1.5;
			
//Debug			System.out.print(newItem.getMenuItem());
//Debug			System.out.print(" ");
//Debug			System.out.print(newItem.getPrice());
//Debug			System.out.println();	
			
		}
		
		else {
			
			System.out.println(str+"Item not found");
			
		}
			
	}
	
	public void updateTotalLabels() {
		double totalPrice = 0;
		double taxValue;
		
		for(Order i: currentOrders) {
			totalPrice += i.getPrice();
		}
		
		taxValue = totalPrice*this.taxAmount;
		
		this.subTotalLabel.setText(String.format("%.2f", (totalPrice)));
		this.taxLabel.setText(String.format("%.2f", taxValue));
		this.totalLabel.setText(String.format("%.2f", (totalPrice+taxValue)));
		
	}
	
	public void placeOrder(ActionEvent event) {
		double totalPrice = 0;
		double taxValue;
		
		
		for(Order i: currentOrders) {
			totalPrice += i.getPrice();
		}
		
		taxValue = totalPrice*this.taxAmount;
		
		System.out.println(String.format("%.2f", (totalPrice+taxValue)));
		System.out.println(String.format("Beef %.2f", (this.beef)));
		System.out.println(String.format("Chicken %.2f", (this.chicken)));
		System.out.println(String.format("Hard Shell %.2f", (this.hardShell)));
		System.out.println(String.format("Tortilla %.2f", (this.tortilla)));
		System.out.println(String.format("Lettuce %.2f", (this.lettuce)));
		System.out.println(String.format("Cheese %.2f", (this.cheese)));
		
		System.out.println(String.format("Tea %.2f", (this.tea)));
		System.out.println(String.format("Lemons %.2f", (this.lemons)));
		
		this.beef = 0;
		this.chicken = 0;
		this.hardShell = 0;
		this.tortilla = 0;
		this.lettuce = 0;
		this.cheese = 0;
		this.tea = 0;
		this.lemons = 0;
		
		double reset = 0;
		this.subTotalLabel.setText(String.format("%.2f", (reset)));
		this.taxLabel.setText(String.format("%.2f", reset));
		this.totalLabel.setText(String.format("%.2f", (reset)));
		
		orders.clear();
		currentOrders.clear();
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
