package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import application.model.Financials;
import application.model.Order;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private LineChart<String, Double> chartA;

    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private LineChart<String, Double> chartB;
    
    @FXML
    private LineChart<String, Double> chartC;
    
    @FXML
    private ObservableList<String> dates;
	
    //used to hold data read from file *days contains temporary values for testing*
	private String[] days = {"01/01/17", "01/02/17"};
	private double[] incomeList;
	private double[] expenseList;
    
    /**
	 * constructor sets up a Calculator
	 */
	public FinancialsController() {
		super();
	}
	
	
	//reading from file into arrays days, incomeList, and expenseList to be used in chart and totals output
	public void setChartInfo() {
		Scanner scan;
		try {
			URL url = getClass().getResource("./src/application/data/DailyTotals.txt");
			File file = new File(url.getPath());
			scan = new Scanner(file);
			int i = 0;
			while(scan.hasNext()) {
				days[i] = scan.next();
				incomeList[i] = scan.nextDouble();
				expenseList[i] = scan.nextDouble();
				i++;
			}
			scan.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//test print
		for(int i=0;i<days.length;i++) {
			System.out.print(days[i]+ " | ");	
		}
		System.out.print("\n");
		for(int i=0;i<incomeList.length;i++) {
			System.out.print(incomeList[i] + " | ");	
		}
		System.out.print("\n");
		for(int i=0;i<expenseList.length;i++) {
			System.out.print(expenseList[i]+ " | ");	
		}
		System.out.print("\n");
		//end test print
	}

	//
	@FXML
	public void initialize() {
		//setChartInfo();
		//showChartA();
		
		//testing for updateIncome in Financials
	/*	try {
			Financials.updateIncome("01/01/2017", 5.00);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //end test
	*/	
		//temporary values for income, expenses, profit will be chaged when file io figured out/ fixed
		double income = 5; //incomeList[incomeList.length];
		double expenses = 2; //expenseList[expenseList.length];
		double profit = income - expenses;
		this.profitToday.setText(String.format("$%.2f", (profit)));
		this.incomeToday.setText(String.format("$%.2f", (income)));
		this.expensesToday.setText(String.format("$%.2f", (expenses)));
		
		
		
		
	}
	// Will put data into chart - will make others for chartB and chartC once figured out
	public void showChartA() {
		dates.addAll(Arrays.asList(days));
		xAxis.setCategories(dates);
		XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < days.length; i++) {
            series.getData().add(new XYChart.Data<>(days[i], (incomeList[i]-expenseList[i])));
        }

        chartA.getData().add(series);
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