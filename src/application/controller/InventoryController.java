package application.controller;

import java.io.IOException;

import application.model.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	public InventoryController() {
		super();
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

}