package application.controller;

import application.model.MainPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController implements EventHandler<ActionEvent> {
	@FXML
	private Label output;
	private MainPage mPage;
	
	/**
	 * constructor sets up a Calculator
	 */
	public MainController() {
		super();
		this.mPage = new MainPage();
	}

	@Override
	public void handle(ActionEvent event) {
		Button b = (Button)event.getSource();
	}

}
