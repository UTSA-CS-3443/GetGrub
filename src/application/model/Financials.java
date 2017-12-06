package application.model;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Financial object that contains each days total income, expenses, and profit.
 * @author Joshua Thomas 
 *
 */
public class Financials {
	
	public ArrayList<String> days;
	public ArrayList<Double> incomeList;
	public ArrayList<Double> expenseList;
	public ArrayList<Double> profitList;
	
	// Reads DailyTotals.txt file and saves data in ArrayLists days, incomeList, and expenseList
	/**
	 * Reads from text file to get the current income and expenses for the day
	 */
	public void getData() {
		String fileName = "./src/application/data/DailyTotals.txt";    
        String line = null;    
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Empties ArrayLists
            days = new ArrayList<String>();
        	incomeList = new ArrayList<Double>();
        	expenseList = new ArrayList<Double>();
        	profitList = new ArrayList<Double>();
        	//Reads through each line, separating the data into the three arraylists
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                //System.out.println(values[0] + " " + values[1] + " " + values[2]);
            	days.add(values[0]);
            	double a = Double.parseDouble(values[1]);
            	double b = Double.parseDouble(values[2]);
            	incomeList.add(a);
            	expenseList.add(b);	
            }
            bufferedReader.close();
            //Runs through incomeList and expenseList to create a list of profits
            for(int i=0; i<incomeList.size(); i++) {
    				profitList.add(incomeList.get(i)-expenseList.get(i));
    		}
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	//Takes in a date, income, and adds the income and expense values to the totals for that day in DailyTotals.txt 
	/**
	 * updates the Daily labels, and DailyTotals.txt
	 * @param date todays date
	 * @param income todays income
	 * @param expenses todays expenses
	 * @throws IOException file not found
	 */
	public void updateDailyTotals(String date, Double income, Double expenses) throws IOException {
		ArrayList<String> newLines = new ArrayList<String>();
		int i=0;
		boolean dateFound = false;
		for (String line : Files.readAllLines(Paths.get("./src/application/data/DailyTotals.txt"), StandardCharsets.UTF_8)) {
			//If the date is found, update that line
			if (line.contains(date)) {
				dateFound = true;
				getData();
				double newIncome = incomeList.get(i) + income;
				double newExpense = expenseList.get(i) + expenses;
				String str = days.get(i) + " " + newIncome + " " + newExpense;
				newLines.add(line.replace(line, str));
		    } 
			//Otherwise keep it the same
			else {
				newLines.add(line);
		    }
			i++;
		}
		//If the date is not found (therefore new data), add to DailyTotals.txt
		if(!dateFound) {
			newLines.add(date + " " + income + " " + expenses);
		}
		Files.write(Paths.get("./src/application/data/DailyTotals.txt"), newLines, StandardCharsets.UTF_8);
	}
}
