package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.model.Inventory;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class InventoryController  implements EventHandler<ActionEvent> {
//	@FXML
	@FXML
	private Label beefStock;
	
	@FXML
	private Label chickenStock;
	
	@FXML
	private Label hardSStock;
	
	@FXML
	private Label tortillaStock;
	
	@FXML
	private Label lettuceStock;
	
	@FXML
	private Label cheeseStock;
	
	@FXML
	private Label teaStock;
	
	@FXML
	private Label lemonStock;
	
	@FXML
	private TableView<Inventory> deliveryTable;
	
	@FXML
	private TableColumn<Inventory, String> stockInfo;
	
	//@FXML
	private TableColumn<Inventory, Inventory> deliveryStatus;
	//private TableColumn deliveryStatus; 
	//private TableColumn<Inventory, Inventory> deliveryStatus; 
	
	
	private double beefT = 50.00;
	private double chickenT = 35.00;
	private double shellT = 15.00;
	private double tortillaT;
	private double lettuceT;
	private double cheeseT;
	private double teaT;
	private double lemonT;
	
	/**
	 * constructor sets up a Calculator
	 */

	private ArrayList<Inventory> currentInventories;
	private final ObservableList<Inventory> inventories = FXCollections.observableArrayList();
	
	public InventoryController() {
		super();
		currentInventories = new ArrayList<Inventory>();
	}

	@FXML
	public void initialize() {
		updateLabels();
		stockInfo.setCellValueFactory(cellData -> cellData.getValue().menuItemProperty());
		
		//deliveryStatus = new TableColumn("Status");
		

		
		deliveryStatus = new TableColumn<>("Status");
		deliveryStatus.setPrefWidth(126);
		deliveryStatus.setStyle( "-fx-alignment: CENTER-RIGHT;");
		deliveryStatus.setCellValueFactory(
		    param -> new ReadOnlyObjectWrapper<>(param.getValue())
		);
		deliveryStatus.setCellFactory(param -> new TableCell<Inventory, Inventory>() {
		    private final Button deleteButton = new Button("Arrived");

		    @Override
		    protected void updateItem(Inventory inventory, boolean empty) {
		        super.updateItem(inventory, empty);

		        if (inventory == null) {
		            setGraphic(null);
		            return;
		        }
		        
	        	setGraphic(deleteButton);
		        deleteButton.setOnAction(
		            (event) -> {
		           		            	
		            	Inventory inv = getTableView().getItems().get(getIndex());
			            String str = inv.getMenuItem();
			            
			            //TODO: Need to add the other order types
			            if (str.equals("Beef Order (25 lb)")){
			            	beefT += 25;
				            beefStock.setText(String.format("%.2f", (beefT)));
			            }
			            else
			            {
			            	System.out.println("No logic defined for "+str);
			            }			         
		            	
			            getTableView().getItems().remove(inventory);
		            }
		        );
	        }
	        
		});
		
		deliveryTable.setItems(inventories);
		deliveryTable.setPlaceholder(new Label(""));
		deliveryTable.getColumns().add(deliveryStatus);
        

	}
	
	public void returnHomeButton(ActionEvent event) throws IOException
	{	
		Stage window = (Stage)((Button)event.getSource()).getScene().getWindow();
		Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/GetGrubMain.fxml"));
		Scene viewScene = new Scene(viewParent);
		window.setScene(viewScene);
	}
		

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void orderItemForDelivery(ActionEvent event)
	{
		//System.out.print("I do nothing right now");
		Button b = (Button)event.getSource();
		String str = b.getText();
		if(str.equals("Order Beef")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Beef Order (25 lb)", 25.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
	}
}
	
	
	public void updateLabels() {
		
		this.beefStock.setText(String.format("%.2f", (beefT)));
		this.chickenStock.setText(String.format("%.2f", chickenT));
		this.hardSStock.setText(String.format("%.2f", (shellT)));
		this.tortillaStock.setText(String.format("%.2f", (tortillaT)));
		this.lettuceStock.setText(String.format("%.2f", (lettuceT)));
		this.cheeseStock.setText(String.format("%.2f", (cheeseT)));
		this.teaStock.setText(String.format("%.2f", (teaT)));
		this.lemonStock.setText(String.format("%.2f", (lemonT)));
	}
	
	/*
	 * public addToTotal(String itemName) {
	if  (itemName == "beef"){
	beefT = beefT + itemAmount;
	} else if (itemName == "chicken"){ 
	chickenT = chickenT + itemAmount;
	 }else if (itemName == "hard shell"){
	 shellT = shellT + itemAmount;
	} else if (itemName == "tortilla"){
	 tortillaT = tortillaT + itemAmount;
	} else if (itemName == "lettuce"){
	lettuceT = lettuceT + itemAmount;
	} else if (itemName == "cheese"){
	cheeseT = cheeseT + itemAmount;
	} else if (itemName == "tea"){
	teaT = teaT + itemAmount;
	} else if (itemName == "lemon"){
	lemonT = lemonT + itemAmount;
	}
	updateLabels();
*/
}