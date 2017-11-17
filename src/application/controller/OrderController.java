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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import application.model.Order;

public class OrderController implements EventHandler<ActionEvent> {
	@FXML
	private TableView<Order> orderTable;
	
	@FXML
	private Label tax;
	
	@FXML
	private TableColumn<Order, String> menuItem;
	
	@FXML
	private TableColumn<Order, Double> price;
	
	
	private ArrayList<Order> currentOrders;
	private final ObservableList<Order> orders = FXCollections.observableArrayList();
	private final double taxAmmount = .0825;
	
	
	public OrderController() {
		super();
		System.out.println("HELLO");
		this.currentOrders = new ArrayList<Order>();
		
		//this.menuItem = new TableColumn<>("Item");
		//menuItem.setMinWidth(165);
		//this.menuItem.setCellValueFactory(new PropertyValueFactory<Order, String>("menuItem"));

		//this.price = new TableColumn<>("Price");
		//price.setMinWidth(160);
		//this.price.setCellValueFactory(new PropertyValueFactory<Order, Double>("price"));

		//orders.add(new Order("Crunchy Taco W/Beef",1.50));
		
		//orderTable = new TableView<>();
		//orderTable.setItems(getOrderData());
		//orderTable.getColumns().addAll(itemName,itemPrice);
		
	}
	
	@FXML
	public void initialize() {
		System.out.println("223");
		menuItem.setCellValueFactory(cellData -> cellData.getValue().menuItemProperty());
		price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
		orderTable.setItems(getOrderData());
		
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
			this.tax.setText("100");
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
			orders.add(new Order("Crunchy Taco W/Beef",1.50));
			
		}
		else if(str.equals("Soft Taco W/Beef")) {
			System.out.println(str);
			Order newItem = new Order(str,2.00);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
			
			
		}
		else if(str.equals("Soft Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(str,2.00);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Crunchy Taco W/Chicken")) {
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Beef Burrito")) {
			System.out.println(str);
			Order newItem = new Order(str,2.99);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Chicken Burrito")) {
			System.out.println(str);
			Order newItem = new Order(str,2.79);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
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
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Water")) {
			System.out.println(str);
			Order newItem = new Order(str,.25);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Tea")) {
			System.out.println(str);
			Order newItem = new Order(str,1.00);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else if(str.equals("Lemonade")) {
			System.out.println(str);
			Order newItem = new Order(str,1.50);
			System.out.print(newItem.getMenuItem());
			System.out.print(" ");
			System.out.print(newItem.getPrice());
			System.out.println();
			currentOrders.add(newItem);
		}
		else {
			System.out.println(str+"Item not found");
		}
	}
	
//	public void updateTotalLabels() {
//		double totalPrice = 0;
//		double taxAmmount;
//		for(Order i: currentOrders) {
//			totalPrice += i.getPrice();
//		}
//		taxAmmount = totalPrice*this.tax;
//	}
//	
//	
	public void placeOrder(ActionEvent event) {
		double totalPrice = 0;
		double taxAmmount;
		for(Order i: currentOrders) {
			totalPrice += i.getPrice();
		}
		taxAmmount = totalPrice*this.taxAmmount;
		System.out.println(String.format("%.2f", totalPrice));
		System.out.println(String.format("%.2f", taxAmmount));
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
