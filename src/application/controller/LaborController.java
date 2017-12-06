package application.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import application.model.Labor;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The Labor Controller contains the FXML items for the Manage Labor screen, along with methods to remove employees or 
 * hire new applicants
 * @author John
 *
 */
public class LaborController implements EventHandler<ActionEvent> {
//	@FXML
	
	@FXML
	private TableView<Labor> employeeTable;
	
	@FXML
	private TableColumn<Labor,String> lastNameColumn;
	
	@FXML
	private TableColumn<Labor,String> firstNameColumn;
	
	@FXML
	private TableColumn<Labor,String> roleColumn;
	
	@FXML
	private TableColumn<Labor,String> payTypeColumn;
	
	@FXML
	private TableColumn<Labor,String> hoursColumn;
	
	
	@FXML
	private TableView<Labor> applicantsTable;
	
	@FXML
	private TableColumn<Labor,String> lastNameApplicantColumn;
	
	@FXML
	private TableColumn<Labor,String> firstNameApplicantColumn;
	
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label desiredRoleLabel;
	
	@FXML
	private TextArea experienceText; 
	
	@FXML
	private TextArea educationText;  
	
	private final ObservableList<Labor> employees = FXCollections.observableArrayList();
	private final ObservableList<Labor> applicants = FXCollections.observableArrayList();
	
	private TableColumn<Labor, Labor> terminateEmployee;
	
	/**
	 * constructor for the Manage Labor screen.
	 */
	public LaborController() {
		super();
		
		
	}
	
	/**
	 * this method is called after the constructor to set up a few FXML items
	 */
	@FXML
	public void initialize() {
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
		roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
		payTypeColumn.setCellValueFactory(cellData -> cellData.getValue().paymentProperty());
		hoursColumn.setCellValueFactory(cellData -> cellData.getValue().hoursProperty());
		
		terminateEmployee = new TableColumn<>("Terminate");
		terminateEmployee.setPrefWidth(113);
		terminateEmployee.setStyle( "-fx-alignment: CENTER-RIGHT;");
		terminateEmployee.setCellValueFactory(
		    param -> new ReadOnlyObjectWrapper<>(param.getValue())
		);
		terminateEmployee.setCellFactory(param -> new TableCell<Labor, Labor>() {
		    private final Button deleteButton = new Button("X");

		    @Override
		    protected void updateItem(Labor labor, boolean empty) {
		        super.updateItem(labor, empty);

		        if (labor == null) {
		            setGraphic(null);
		            return;
		        }
		        
	        	setGraphic(deleteButton);
		        deleteButton.setOnAction(
		            (event) -> {
		            	Labor lab = getTableView().getItems().get(getIndex());
			            
			            Labor p;
			            
			            if(lab.getRole().equals("Owner")) {
			            	double num = lab.getPayment();
			            	String pay = String.format("%.2f",num);
			            	p = new Labor(lab.getLName(),lab.getFName(),lab.getRole(),"Annually",pay,Double.toString(lab.getHours()));
			            	
			            	//System.out.println(p.getPayType());
			            }
			            else {
			            	double num = lab.getPayment();
			            	String pay = String.format("%.2f",num);
			            	System.out.println(pay);
			            	System.out.println(pay);
			            	System.out.println(pay);
			            	p = new Labor(lab.getLName(),lab.getFName(),lab.getRole(),"Hourly",pay,Double.toString(lab.getHours()));
			         
			            	System.out.println(p.getPayment());
			            	System.out.println(pay);
			            }
			            
			            getTableView().getItems().remove(labor);
			            
			            removeEntryFromEmployeeFile(p);
		            }
		        );
	        }
	        
		});
		
		employeeTable.setItems(getEmployeeData());
		employeeTable.setPlaceholder(new Label(""));
		employeeTable.getColumns().add(terminateEmployee);
		
		lastNameApplicantColumn.setCellValueFactory(cellData -> cellData.getValue().lNameProperty());
		firstNameApplicantColumn.setCellValueFactory(cellData -> cellData.getValue().fNameProperty());
		applicantsTable.setItems(getApplicantData());
		applicantsTable.setPlaceholder(new Label(""));
		
		applicantsTable.setOnMouseClicked((MouseEvent event) -> {
	        if(event.getButton().equals(MouseButton.PRIMARY)){
	        	Labor x = applicantsTable.getSelectionModel().getSelectedItem();
	            
	        	if (x != null) {
	        		this.nameLabel.setText(x.getLName()+", "+x.getFName());
		        	
		        	this.desiredRoleLabel.setText(x.getRole());
		        	
		        	this.experienceText.setText(x.getExperience());
		        	
		        	this.educationText.setText(x.getEducation());
	        	}
	        	
	        }
	    });    
	}

	
	/**
	 * returns to GetGrubs main menu
	 * @param event mouse click
	 * @throws IOException file not found
	 */
	public void returnHomeButton(ActionEvent event) throws IOException
	{	
		Stage window = (Stage)((Button)event.getSource()).getScene().getWindow();
		Parent viewParent = FXMLLoader.load(getClass().getResource("/application/view/GetGrubMain.fxml"));
		Scene viewScene = new Scene(viewParent);
		window.setScene(viewScene);
	}
		
	
	/**
	 * setup for the employee list.
	 */
	public ObservableList<Labor> getEmployeeData(){
		String fileName = "./src/application/data/Employees.txt";    
	    String line = null;   
	    
	    
		try {
				FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	
	                String[] values = line.split(", ");
	               
	                String lName = values[0];
	        	    String fName = values[1];
	        	    String role = values[2];
	        	    String payType = values[3];
	        	    String payment = values[4];
	        	    String hours = values[5];
	        	    
	        	    Labor newEmployee = new Labor(lName,fName,role,payType,payment,hours);
	            	
	            	employees.add(newEmployee);
		            }
	            bufferedReader.close();
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		
		return this.employees;
	}
	
	/**
	 * setup for the applicant list
	 * @return list of applicants
	 */
	public ObservableList<Labor> getApplicantData(){
		String fileName = "./src/application/data/Applicants.txt";    
	    String line = null;   
	    
	    
		try {
				FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	
	                String[] values = line.split(", ");
	                
	                String lName = values[0];
	                String fName = values[1];
	        	    String role = values[2];
	        	    String experience = values[3];
	        	    String education = values[4];
	        	    
	        	    
	        	    Labor newEmployee = new Labor(lName,fName,role,experience,education);
	            	
	            	applicants.add(newEmployee);
		            }
	            bufferedReader.close();
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
		
		
		return this.applicants;
	}
	
	/**
	 * when the Hire Applicant button is clicked they are added to the employee list and txt file
	 * @param event mouse click
	 */
	public void hireNewEmployee(ActionEvent event) {
		
		if( applicantsTable.getSelectionModel().getSelectedItem() != null) {
			Labor q = applicantsTable.getSelectionModel().getSelectedItem();
			String x = this.nameLabel.getText();
	    	
	    	String x1 = this.desiredRoleLabel.getText();
	    	
	    	String x2 = this.experienceText.getText();
	    	
	    	String x3 = this.educationText.getText();
			
	    	String newPaymentType = null;
	    	
	    	String newWage = null;
	    	
	    	String hrs = null;
	    	
	    	String g = x+", "+x1+", "+x2+", "+x3;
	    	System.out.println(g);
	    	
	    	if(x1.equals("Head Chef")) {
	    		newPaymentType = "Hourly";
	    		newWage = "15.50";
	    		hrs = "40.0";
	    		q.setPayment(newWage);
	            q.setPaymentType(newPaymentType);
	            q.setHours(hrs);
	    	}
	    	else if(x1.equals("Bus person")) {
	    		newPaymentType = "Hourly";
	    		newWage = "9.30";
	    		hrs = "25.0";
	    		q.setPayment(newWage);
	            q.setPaymentType(newPaymentType);
	            q.setHours(hrs);
	    	}
	    	
	    	String fileName = "./src/application/data/Employees.txt";    
		    String line = null;   
		    ArrayList<String> newLines = new ArrayList<String>();
		    
			try {
					FileReader fileReader = new FileReader(fileName);
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            
		            while((line = bufferedReader.readLine()) != null) {
		            	
		            	newLines.add(line);
		            	
			        }
		            bufferedReader.close();
		            
		            newLines.add(x+", "+x1+", "+newPaymentType+", "+newWage+", "+hrs);
		            Files.write(Paths.get("./src/application/data/Employees.txt"), newLines, StandardCharsets.UTF_8);
		           
		            employees.add(q);
		            removeEntryFromApplicantFile(q);
		            applicants.remove(q);
		            resetApplicationLabels();
		    }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                  
	        }
		}
		
	}
	
	/**
	 * remove applicant from list
	 * @param event mouse click
	 */
	public void removeApplicant(ActionEvent event) {
		Labor y = applicantsTable.getSelectionModel().getSelectedItem();
		removeEntryFromApplicantFile(y);
		resetApplicationLabels();
	}
	
	/**
	 * remove applicant from file
	 * @param x file name
	 */
	public void removeEntryFromApplicantFile(Labor x) {
		String p =x.getLName()+", "+x.getFName()+", "+x.getRole()+", "+x.getExperience()+", "+x.getEducation();
		
		String fileName = "./src/application/data/Applicants.txt";    
	    String line = null;   
	    ArrayList<String> newLines = new ArrayList<String>();
	    System.out.println(p);
	    
		try {
				FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	if(!line.equals(p)) {
	            		newLines.add(line);
	            		System.out.println(line);
	            	}
	            	
		        }
	            bufferedReader.close();
	            applicants.remove(x);
	            Files.write(Paths.get("./src/application/data/Applicants.txt"), newLines, StandardCharsets.UTF_8);
	            
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	/**
	 * remove employee from file
	 * @param x file name
	 */
	public void removeEntryFromEmployeeFile(Labor x) {

		String p =x.getLName()+", "+x.getFName()+", "+x.getRole()+", "+x.getPaymentType()+", "+String.format("%.2f",x.getPayment())+", "+x.getHours();
		
		String fileName = "./src/application/data/Employees.txt";    
	    String line = null;   
	    ArrayList<String> newLines = new ArrayList<String>();
	    System.out.println(p);
	    
		try {
				FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	if(!line.equals(p)) {
	            		newLines.add(line);
	            		System.out.println(line);
	            	}
	            	
		        }
	            bufferedReader.close();
	            applicants.remove(x);
	            Files.write(Paths.get("./src/application/data/Employees.txt"), newLines, StandardCharsets.UTF_8);
	            
	    }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	}
	
	/**
	 * clears applicant review labels
	 */
	public void resetApplicationLabels() {
		this.nameLabel.setText("");
    	
    	this.desiredRoleLabel.setText("");
    	
    	this.experienceText.setText("");
    	
    	this.educationText.setText("");
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

