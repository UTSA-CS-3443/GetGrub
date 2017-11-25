package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.model.Financials;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	//Scene mainGUIScene, manageInventoryScene, manageOrdersScene, manageFinacialsScene, manageLaborScene, scheduelingScene;
	
	
	//Main
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/GetGrubMain.fxml"));
			primaryStage.setScene(new Scene(root, 650, 500));
			primaryStage.setTitle("Get Grub");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void checkEmployeesPaid(String x) {
		String fileName = "./src/application/data/EmployeePaid.txt";    
        String line = null;    
        boolean dateExist = false;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
           
            while((line = bufferedReader.readLine()) != null) {
            	System.out.println(line);
            	if(line.equals(x)) {
            		dateExist = true;
            		System.out.println("Employees already paid this week");
            	}
            }
            bufferedReader.close();
            
            if (dateExist == false) {
            	System.out.println("Paying Employees");
            	try {
            		File fout = new File("./src/application/data/EmployeePaid.txt");
            		
            		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fout, true));
            		
            		//Financials y = new Financials();
            		
            		//y.updateDailyTotals(x, 0.00, 0.00);
            		
            		bufferedWriter.write(x);
            		bufferedWriter.newLine();
            		bufferedWriter.close();
            		
            	} catch(FileNotFoundException ex) {
            		System.out.println("Unable to open file '" + fileName + "'"); 
            	} 
            }
            
            //Runs through incomeList and expenseList to create a list of profits
            
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	
	public static void main(String[] args) {
		Date today = new Date();
 
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); 
 
        if(simpleDateformat.format(today).equals("Saturday")) {
        	System.out.println(today);
        	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        	System.out.println(dateFormat.format(today));
        	
        	//checkEmployeesPaid(dateFormat.format(today));
        }
 
		launch(args);
	}
}
