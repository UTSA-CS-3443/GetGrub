package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Financials {
	
	public static void updateIncome(String date, Double income) throws IOException {
		// Not currently opening correct file (one in application.data), instead created/updates in top folder of project
        String fileName = "./src/application/data/DailyTotals.txt";

        // Reads file and prints it out (will later be changed to just read data for updating)
        String line = null;
        ArrayList<String> days = null;
    	ArrayList<Double> incomeList = null;
    	ArrayList<Double> expenseList = null;
    	
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
            	days.add(values[0]);
            	double a = Double.parseDouble(values[1]);
            	double b = Double.parseDouble(values[2]);
            	incomeList.add(a);
            	expenseList.add(b);
                
            }   
           //test print
    		for(int j=0;j<days.size();j++) {
    			System.out.print(days.get(j)+ " | ");	
    		}
    		System.out.print("\n");
    		for(int k=0;k<incomeList.size();k++) {
    			System.out.print(incomeList.get(k) + " | ");	
    		}
    		System.out.print("\n");
    		for(int l=0;l<expenseList.size();l++) {
    			System.out.print(expenseList.get(l)+ " | ");	
    		}
    		System.out.print("\n");
    		//end test print
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");                  
        }
		
		//appends new line to file containing the new income and date. Will be changed to only occur if date does not yet exist, otherwise will add to that data
		String str = date + " " + income.toString() + " 0.00";
	    BufferedWriter writer = new BufferedWriter(new FileWriter("./src/application/data/DailyTotals.txt", true));
	   // writer.append("\n");
	   // writer.append(str);
	     
	    writer.close();
		
	}
}
