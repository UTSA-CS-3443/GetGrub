package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Financials {
	
	public static void updateIncome(String date, Double income) throws IOException {
		// Not currently opening correct file (one in application.data), instead created/updates in top folder of project
        String fileName = "DailyTotals.txt";

        // Reads file and prints it out (will later be changed to just read data for updating)
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   
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
	    BufferedWriter writer = new BufferedWriter(new FileWriter("DailyTotals.txt", true));
	    writer.append("\n");
	    writer.append(str);
	     
	    writer.close();
		
	}
}
