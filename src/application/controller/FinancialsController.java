package application.controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import application.model.Financials;
import application.model.LineChartA;
import application.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class FinancialsController  implements EventHandler<ActionEvent> {
	
	//labels for Today's Totals section	
	@FXML 
	private Label profitToday;	
	@FXML 
	private Label incomeToday;	
	@FXML 
	private Label expensesToday;
	
	//set up for charts (A - profit, B - income, C - expenses
    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private Axis<Double> y;
    
    @FXML
    private ToggleButton showProfit;
    
    @FXML
    private ToggleButton showIncome;
    
    @FXML
    private ToggleButton showExpenses;
	//Series for chart
	XYChart.Series<String, Double> seriesP = new XYChart.Series<String, Double>();
	XYChart.Series<String, Double>  seriesI = new XYChart.Series<String, Double>();
	XYChart.Series<String, Double>  seriesE = new XYChart.Series<String, Double>();
    
    /**
	 * constructor sets up a Calculator
	 */
	public FinancialsController() {
		super();
	}

	@FXML
	public void initialize() {
		try {
			//uses Financials to read the data from file
			Financials fl = new Financials();
			/*--------TESTING FOR undateIncome()-------------
			fl.updateIncome("01/04/2017", 50.00);
			fl.updateIncome("01/06/2017", 10.00);
			----------END TEST-------------------------------*/
			fl.getData();
			//gets most recent day's income, expense, and profit
			double income = fl.incomeList.get(fl.incomeList.size()-1);
			double expenses = fl.expenseList.get(fl.expenseList.size()-1);
			double profit = income - expenses;
			//Updates Profit, Income, and Expenses fields in "Today's Totals" section
			this.profitToday.setText(String.format("$%.2f", (profit)));
			this.incomeToday.setText(String.format("$%.2f", (income)));
			this.expensesToday.setText(String.format("$%.2f", (expenses)));
		
			lineChart.setAnimated(false);
			
			//Takes profitList, incomeList, and expenseList data and puts it into a series for the chart		
			seriesI.setName("Income");
			for(int k=0; k<fl.incomeList.size(); k++) {
				seriesI.getData().add(new Data<String, Double>(fl.days.get(k), fl.incomeList.get(k)));
			}
			
			seriesE.setName("Expenses");
			for(int l=0; l<fl.expenseList.size(); l++) {
				seriesE.getData().add(new Data<String, Double>(fl.days.get(l), fl.expenseList.get(l)));
			}
			
			seriesP.setName("Profit");
			for(int m=0; m<fl.expenseList.size(); m++) {
				seriesP.getData().add(new Data<String, Double>(fl.days.get(m), fl.profitList.get(m)));
			}

		} catch (Exception e) {
			System.out.println("Failed to in Initialize");
		} 
	}
	
	// Displays chart based off of which buttons are selected
	public void showChart(ActionEvent event) throws IOException{
		//Empties Chart
		lineChart.getData().clear();
		//Then adds series for each selected button
		if(showProfit.isSelected()) {
			lineChart.getData().addAll(seriesP);
		}
		if(showIncome.isSelected()) {
			lineChart.getData().addAll(seriesI);
		}
		if(showExpenses.isSelected()) {
			lineChart.getData().addAll(seriesE);
		}
		
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
}