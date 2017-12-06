package application.controller;

import java.io.IOException;
import application.model.Financials;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * The Financials Controller Contains the FXML items on the Manage Financials screen, along with a graph to show the Daily income,
 * expenses, and profit.
 * @author Joshua Thomas
 *
 */
public class FinancialsController  implements EventHandler<ActionEvent> {
	
	//labels for Today's Totals section	
	@FXML 
	private Label profitToday;	
	@FXML 
	private Label incomeToday;	
	@FXML 
	private Label expensesToday;	
	//Chart and x,y axis
    @FXML
    private LineChart<String, Double> lineChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private Axis<Double> y;
    //Toggle Buttons for chart
    @FXML
    private ToggleButton showProfit;
    @FXML
    private ToggleButton showIncome;   
    @FXML
    private ToggleButton showExpenses;
	//Series for chart data
	XYChart.Series<String, Double> seriesP = new XYChart.Series<String, Double>();
	XYChart.Series<String, Double>  seriesI = new XYChart.Series<String, Double>();
	XYChart.Series<String, Double>  seriesE = new XYChart.Series<String, Double>();
    
    /**
	 * constructor for Manage Finacials screen
	 */
	public FinancialsController() {
		super();
	}

	/**
	 * this method is called after the constructor to set up a few FXML items
	 */
	@FXML
	public void initialize() {
		try {
			//uses Financials to read the data from file
			Financials fl = new Financials();
			fl.getData();
			//Gets most recent day's income, expense, and profit
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

		} 
		catch (Exception e) {
			System.out.println("Failed to in Initialize");
		} 
	}
	
	/**
	 * Displays a chart of the history of each day's income, expenses, and profit. 
	 * @param event mouse click
	 * @throws IOException file not found
	 */
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
	
	/**
	 * returns to GetGrubs main menu
	 * @param event mouse click
	 * @throws IOException file not found
	 */
	//Return to main screen
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