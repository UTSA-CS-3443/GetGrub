package application.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import application.model.Financials;
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
	
	
	private double beefT;
	private double chickenT;
	private double shellT;
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
			            Inventory x = new Inventory();
			            
			            //TODO: Need to add the other order types
			            if (str.equals("Beef Order (25 lb)")){
			            	x.updateInventory("Beef", 25);
				    
			            }else if (str.equals("Chicken Order (25 lbs)")){
			            	x.updateInventory("Chicken", 25);
				    
			            }
			            else if (str.equals("Hard Shell Order (30 qty)")){
			            	x.updateInventory("HardShell", 30);
				    
			            }
			            else if (str.equals("Tortillas Order (30 qty)")){
			            	x.updateInventory("Tortilla", 30);
				    
			            }
			            else if (str.equals("Lettuce Order (15 lbs)")){
			            	x.updateInventory("Lettuce", 15);
				    
			            }
			            else if (str.equals("Cheese Order (15 lbs)")){
			            	x.updateInventory("Cheese", 15);
				    
			            }
			            else if (str.equals("Tea Order (20 lbs)")){
			            	x.updateInventory("Tea", 20);
				    
			            }
			            else if (str.equals("Lemons Order (30 qty)")){
			            	x.updateInventory("Lemons", 30);
				    
			            } else
			            {
			            	System.out.println("No logic defined for "+str);
			            }			         
		            	
			            updateLabels();
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
	
	
	
	public void orderItemForDelivery(ActionEvent event) throws IOException
	{
		Financials x = new Financials();
		Date today = new Date();
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	String y = (dateFormat.format(today));
		//System.out.print("I do nothing right now");
		Button b = (Button)event.getSource();
		String str = b.getText();
		if(str.equals("Order Beef")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Beef Order (25 lb)", 25.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			//y= todays date, 0.00 = income, 15.00 expense (how much 25 pounds of beef cost from suppliers)
			x.updateDailyTotals(y, 0.00, 15.00);
		}
		else if(str.equals("Order Chicken")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Chicken Order (25 lbs)", 25.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 8.39);
		}
		else if(str.equals("Order Shells")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Hard Shell Order (30 qty)", 30.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 7.20);
		}
		else if(str.equals("Order Tortillas")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Tortillas Order (30 qty)", 30.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 8.20);
		}
		else if(str.equals("Order Lettuce")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Lettuce Order (15 lbs)", 15.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 3.20);
		}
		else if(str.equals("Order Cheese")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Cheese Order (15 lbs)", 15.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 5.20);
		}
		else if(str.equals("Order Tea")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Tea Order (20 lbs)", 20.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 10.20);
		}
		else if(str.equals("Order Lemons")) {
			System.out.println(str);
			Inventory newItem = new Inventory("Lemons Order (30 qty)", 30.00);
			currentInventories.add(newItem);
			inventories.add(newItem);
			x.updateDailyTotals(y, 0.00, 12.20);
		}
	}
	
	
	public void updateInventoryFromFile() {
		String fileName = "./src/application/data/Inventory.txt";    
	    String line = null;    
	    ArrayList<String> itemList = new ArrayList<String>();
        ArrayList<Double> amountList = new ArrayList<Double>();
	        
	    try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" = ");
               
            	String a = values[0];
            	double b = Double.parseDouble(values[1]);
            	itemList.add(a);
            	amountList.add(b);	
	            }
            bufferedReader.close();
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		
	    int z = 0;
	    for(String i: itemList) {
	    	if(i.equals("Beef")) {
	    		System.out.println(i);
	    		this.beefT = amountList.get(z);
	    	}
	    	else if(i.equals("Chicken")) {
	    		System.out.println(i);
	    		this.chickenT = amountList.get(z);
	    	}
	    	else if(i.equals("HardShell")) {
	    		System.out.println(i);
	    		this.shellT = amountList.get(z);
	    		
	    	}
	    	else if(i.equals("Tortilla")) {
	    		System.out.println(i);
	    		this.tortillaT = amountList.get(z);
	    	
	    	}
	    	else if(i.equals("Lettuce")) {
	    		System.out.println(i);
	    		this.lettuceT = amountList.get(z);
	    		
	    	}
			else if(i.equals("Cheese")) {
				System.out.println(i);
				this.cheeseT = amountList.get(z);
				    		
			}
			else if(i.equals("Tea")) {
				System.out.println(i);
				this.teaT = amountList.get(z);
				
			}
			else if(i.equals("Lemons")) {
				System.out.println(i);
				this.lemonT = amountList.get(z);
				
			}
			else {
				System.out.println(i+" Not Found!");
			}
	    	z++;
	    }
		
//		this.beefT;
//		this.chickenT;
//		this.shellT;
//		this.tortillaT;
//		this.lettuceT;
//		this.cheeseT;
//		this.teaT;
//		this.lemonT;
	}
	public void updateLabels() {
		updateInventoryFromFile();
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