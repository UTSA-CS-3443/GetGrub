package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Financials {
	
	public ArrayList<String> days = new ArrayList<String>();
	public ArrayList<Double> incomeList = new ArrayList<Double>();
	public ArrayList<Double> expenseList = new ArrayList<Double>();
	public ArrayList<Double> profitList = new ArrayList<Double>();
	
	public void getData() {
		String fileName = "./src/application/data/DailyTotals.txt";

        // Reads file and saves data in ArrayLists days, incomeList, and expenseList
        String line = null;
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(" ");
                //System.out.println(values[0] + " " + values[1] + " " + values[2]);
            	days.add(values[0]);
            	double a = Double.parseDouble(values[1]);
            	double b = Double.parseDouble(values[2]);
            	incomeList.add(a);
            	expenseList.add(b);
            	for(int i=0; i<incomeList.size(); i++) {
    				profitList.add(incomeList.get(i)-expenseList.get(i));
    			}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	public void updateIncome(String date, Double income) throws IOException {
		getData();
		double newIncome;
		boolean dateFound = false;
		//still need to get file to be cleared before writing to it
		FileWriter wr = new FileWriter("./src/application/data/DailyTotals.txt",false);
		for(int i=0; i<days.size(); i++) {
			if(days.get(i).equals(date)) {
				newIncome = incomeList.get(i) + income;
				dateFound = true;
			}
			else {
				newIncome = incomeList.get(i);
			}
			String str = days.get(i) + " " + newIncome + " " + expenseList.get(i) + "\n";
			wr.write(str);
		}
		if(!dateFound) {
			wr.write(date + " " + income.toString() + " 0.00\n");
		}
		wr.close();			
	}
}
