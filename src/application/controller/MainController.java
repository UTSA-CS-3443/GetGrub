package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements EventHandler<ActionEvent> {
	
	/**
	 * constructor sets up a Calculator
	 */
	public MainController() {
		super();
	}

	public void changeScreenButton(ActionEvent event) throws IOException
	{
		Scene viewScene;
		Button b = (Button)event.getSource();
		String str = b.getText();
		
		Stage window = (Stage)((Button)event.getSource()).getScene().getWindow();
		
		if(str.equals("Manage Orders")) {
			Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/ManageOrders.fxml"));
			viewScene = new Scene(viewParent);
			window.setScene(viewScene);
		}
		else if(str.equals("Manage Labor")) {
			Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/ManageLabor.fxml"));
			viewScene = new Scene(viewParent);
			window.setScene(viewScene);
		}
		else if(str.equals("Manage Financials")) {
			Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/ManageFinancials.fxml"));
			viewScene = new Scene(viewParent);
			window.setScene(viewScene);
		}
		else if(str.equals("Manage Inventory")) {
			Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/ManageInventory.fxml"));
			viewScene = new Scene(viewParent);
			window.setScene(viewScene);
		}
//		else if(str == "Options") {
//			Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/Options.fxml"));
//			viewScene = new Scene(viewParent);
//		}
		else {
			System.out.println(str);
			
		}
		window.show();
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
