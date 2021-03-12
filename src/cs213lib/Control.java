package cs213lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.DateCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Control {

    private static Company com; //the main company object

    /* GLOBAL COMPONENTS */

    @FXML private TextArea console;
    @FXML private AnchorPane importTab;
    @FXML private AnchorPane exportTab;
    @FXML private Label importStatus;
    @FXML private Label exportStatus;

    /* ADD TAB COMPONENTS */

    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private DatePicker hired;
    @FXML private RadioButton csb;
    @FXML private RadioButton eceb;
    @FXML private RadioButton itb;
    @FXML private RadioButton ftRadio;
    @FXML private RadioButton ptRadio;
    @FXML private RadioButton mRadio;
    @FXML private RadioButton maRadio;
    @FXML private RadioButton dhRadio;
    @FXML private RadioButton dirRadio;
          private ToggleGroup addTabDeptGroup = new ToggleGroup();
          private ToggleGroup addTabTypeGroup = new ToggleGroup();
          private ToggleGroup addTabManTypeGroup = new ToggleGroup();
    @FXML private TextField annualSalary;
    @FXML private TextField hourlyRate;
    @FXML private TextField hoursWorked;
    @FXML private Label messageBox;

    /* MODIFY TAB COMPONENTS */

    @FXML private TextField fnameR;
    @FXML private TextField lnameR;
    @FXML private TextField hoursModify;
    @FXML private DatePicker hiredR;
          private ToggleGroup departmentGroup = new ToggleGroup();
    @FXML private RadioButton csRadio;
    @FXML private RadioButton eceRadio;
    @FXML private RadioButton itRadio;


    /**
     * default constructor called by FXML loader
     */
    public Control(){
        com = new Company();
    }

    /**
     * called by show all button
     */
    @FXML
    private void showAll(){
        console.appendText(com.print());
    }

    /**
     * called by show by departments button
     */
    @FXML
    private void showByDept(){
        console.appendText(com.printByDepartment());
    }

    /**
     * called by show by date button
     */
    @FXML
    private void showByDate(){
        console.appendText(com.printByDate());
    }

    /**
     * called by clear button in the output tab
     */
    @FXML
    private void clearOut(){
        console.setText("");
    }

    /**
     * initializes the add fields in the Add tab
     */
    private void initAddForm(){

        hourlyRate.setDisable(true);
        hoursWorked.setDisable(true); //disable these since no selection is default
        annualSalary.setDisable(true);
        maRadio.setDisable(true);
        dhRadio.setDisable(true);
        dirRadio.setDisable(true);
        hired.setDayCellFactory(param -> new DateCell() { //disable picking future dates
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        //add all the radio buttons to their respective toggle groups
        csb.setToggleGroup(addTabDeptGroup);
        eceb.setToggleGroup(addTabDeptGroup);
        itb.setToggleGroup(addTabDeptGroup);
        ftRadio.setToggleGroup(addTabTypeGroup);
        ptRadio.setToggleGroup(addTabTypeGroup);
        mRadio.setToggleGroup(addTabTypeGroup);
        maRadio.setToggleGroup(addTabManTypeGroup);
        dhRadio.setToggleGroup(addTabManTypeGroup);
        dirRadio.setToggleGroup(addTabManTypeGroup);
        csb.setSelected(true); //set CS as selected by default

        addTabTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        { //add a listener so the employee type spesific fields become available depending which one is currently selected
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {

                RadioButton rb = (RadioButton)addTabTypeGroup.getSelectedToggle(); //get currently selected button

                if (rb != null) {
                    String s = rb.getText();
                    switch (s) {
                        case "Full-Time": //if full time is selected
                            annualSalary.setDisable(false);
                            hourlyRate.setDisable(true);
                            hoursWorked.setDisable(true);
                            maRadio.setDisable(true);
                            dhRadio.setDisable(true);
                            dirRadio.setDisable(true);
                            break;
                        case "Part-Time": //if part time is selected
                            annualSalary.setDisable(true);
                            hourlyRate.setDisable(false);
                            hoursWorked.setDisable(false);
                            maRadio.setDisable(true);
                            dhRadio.setDisable(true);
                            dirRadio.setDisable(true);
                            break;
                        case "Management": //if management is selected
                            annualSalary.setDisable(false);
                            hourlyRate.setDisable(true);
                            hoursWorked.setDisable(true);
                            maRadio.setDisable(false);
                            dhRadio.setDisable(false);
                            dirRadio.setDisable(false);
                            break;
                    }

                }
            }
        });


    }

    /**
     * initializes the modify form
     */
    public void initModifyForm(){
        hiredR.setDayCellFactory(param -> new DateCell() { //disable picking future dates
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        csRadio.setToggleGroup(departmentGroup);
        eceRadio.setToggleGroup(departmentGroup); //add department radio buttons to their toggle group
        itRadio.setToggleGroup(departmentGroup);
        csRadio.setSelected(true);

    }


    /**
     * initializes all parts of the GUI
     */
    @FXML
    public void initialize(){ //initialize ALL GUI elements
        console.setEditable(false); //make the text area read only
        initAddForm(); //initialize the add tab
        initModifyForm(); //initialize the modify tab


    }

    /**
     * checks if the add fields are filled in or not
     * @return true if required fields are filled in, false otherwise
     */
    private boolean addFormCompleted(){
       boolean c1 = !fname.getText().isEmpty(); //first name should not be empty
       boolean c2 = !lname.getText().isEmpty(); //
       boolean c4 = hired.getValue() != null;
       boolean c5 = addTabTypeGroup.getSelectedToggle() != null;

       return c1 && c2 && c4 && c5;

    }

    /**
     * clears the add fields
     */
    private void clearAddForm(){
        fname.setText("");
        lname.setText("");
       csb.setSelected(true);
        hired.setValue(null);
        addTabTypeGroup.getSelectedToggle().setSelected(false);
        if(addTabManTypeGroup.getSelectedToggle() != null) {
            addTabManTypeGroup.getSelectedToggle().setSelected(false);
        }
        annualSalary.setText("");
        hourlyRate.setText("");
        hoursWorked.setText("");
        annualSalary.setDisable(true);
        hourlyRate.setDisable(true);
        hoursWorked.setDisable(true);
        maRadio.setDisable(true);
        dhRadio.setDisable(true);
        dirRadio.setDisable(true);

    }

    /**
     * clears the modify fields
     */
    @FXML
    private void clearModifyForm(){
        fnameR.setText("");
        lnameR.setText("");
       csRadio.setSelected(true);
        hiredR.setValue(null);
        hoursModify.setText("");

    }

    /**
     * processes the add fields and adds an employee, this is called by the Submit button
     */
    @FXML
    public void processAddForm(){ //called by the submit button on the add tab

        if(!addFormCompleted()){
            messageBox.setText("One or more required Fields are EMPTY.");
            console.appendText("\nOne or more required Fields are EMPTY.");
            messageBox.setVisible(true);
            return;
        }
       Date d = new Date(hired.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if(!d.isValid()){
            messageBox.setText("Date is invalid.");
            console.appendText("\nDate is invalid.");
            messageBox.setVisible(true);
            return;
        }

       RadioButton selection = (RadioButton)addTabTypeGroup.getSelectedToggle();
        RadioButton selection2 = (RadioButton)addTabDeptGroup.getSelectedToggle();
        RadioButton selection3 = (RadioButton)addTabManTypeGroup.getSelectedToggle();

        if(selection.getText().equals("Full-Time")){
            Profile p = new Profile(lname.getText()+","+fname.getText(),selection2.getText(),d);
            try {
                float annsal = Float.parseFloat(annualSalary.getText());
                if(annsal < 0){
                    messageBox.setText("Annual Salary can't be negative.");
                    console.appendText("\nAnnual Salary can't be negative.");
                    messageBox.setVisible(true);
                    return;
                }
                if(com.add(new Fulltime(p, annsal))){
                    messageBox.setText("Employee \"" + p.getName() + "\" added successfully.");
                    console.appendText("\nEmployee \"" + p.getName() + "\" added successfully.");
                    messageBox.setVisible(true);
                    clearAddForm();
                }else {
                    messageBox.setText("Employee is already in the database.");
                    console.appendText("\nEmployee is already in the database.");
                    messageBox.setVisible(true);
                }
            }catch(NumberFormatException e){
                messageBox.setText("Annual Salary value is invalid.");
                console.appendText("\nAnnual Salary value is invalid.");
                messageBox.setVisible(true);
            }
        }else if(selection.getText().equals("Part-Time")){
            Profile p = new Profile(lname.getText()+","+fname.getText(),selection2.getText(),d);
            try {
                float hours = !hoursWorked.getText().isEmpty() ? Float.parseFloat(hoursWorked.getText()) : 0;
                float rate = Float.parseFloat(hourlyRate.getText());
                if(rate<0){
                    messageBox.setText("Negative hourly rate entered.");
                    console.appendText("\nNegative hourly rate entered.");
                    messageBox.setVisible(true);
                    return;
                }
                if(hours<0||hours>100){
                    messageBox.setText("Invalid number of hours.");
                    console.appendText("\nInvalid number of hours.");
                    messageBox.setVisible(true);
                    return;
                }
                if(com.add(new Parttime(p, rate, hours))) {
                    messageBox.setText("Employee \"" + p.getName() + "\" added successfully.");
                    console.appendText("\nEmployee \"" + p.getName() + "\" added successfully.");
                    messageBox.setVisible(true);
                    clearAddForm();
                }else{
                    messageBox.setText("Employee is already in the database.");
                    console.appendText("\nEmployee is already in the database.");
                    messageBox.setVisible(true);
                }
            }catch(NumberFormatException e){
                messageBox.setText("Invalid rate or hours entered.");
                console.appendText("\nInvalid rate or hours entered.");
                messageBox.setVisible(true);
            }
        }else if(selection.getText().equals("Management")){
            Profile p = new Profile(lname.getText()+","+fname.getText(),selection2.getText(),d);
            try {
                float annsal = Float.parseFloat(annualSalary.getText());
                if(annsal < 0){
                    messageBox.setText("Annual Salary can't be negative.");
                    console.appendText("\nAnnual Salary can't be negative.");
                    messageBox.setVisible(true);
                    return;
                }
                if(addTabManTypeGroup.getSelectedToggle() == null){
                    messageBox.setText("Manager type is not selected.");
                    console.appendText("\nManager type is not selected.");
                    messageBox.setVisible(true);
                    return;
                }

                if(com.add(new Management(p, annsal, selection3.getText().equals("Manager") ? 1 : (selection3.getText().equals("Department Head") ? 2 : 3)))) {


                    messageBox.setText("Manager \"" + p.getName() + "\" added successfully.");
                    console.appendText("\nManager \"" + p.getName() + "\" added successfully.");
                    messageBox.setVisible(true);
                    clearAddForm();
                }else{
                    messageBox.setText("Employee is already in the database.");
                    console.appendText("\nEmployee is already in the database.");
                    messageBox.setVisible(true);
                }
            }catch(NumberFormatException e){
                messageBox.setText("Annual Salary value is invalid.");
                console.appendText("\nAnnual Salary value is invalid.");
                messageBox.setVisible(true);
            }
        }else{
            messageBox.setText("Unknown Error has Occured.");
            console.appendText("\nUnknown Error has Occured.");
            messageBox.setVisible(true);
        }

    }

    /**
     * checks if all required fields are filled in for modify tab
     * @return
     */
    public boolean modifyCompleted(){
        boolean c1 = !fnameR.getText().isEmpty();
        boolean c2 = !lnameR.getText().isEmpty();
        boolean c3 = hiredR.getValue() != null;
        boolean c4 = csRadio.isSelected() || eceRadio.isSelected() || itRadio.isSelected();

        return c1 && c2 && c3 && c4;
    }

    /**
     * called by the set hours button on the modify tab
     */
    @FXML
    public void setHours(){
        if(!modifyCompleted() || hoursModify.getText().isEmpty()){
            messageBox.setText("One or more required fields are blank");
            console.appendText("\nOne or more required fields are blank");
            messageBox.setVisible(true);
            return;
        }
        Date d = new Date(hiredR.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if(!d.isValid()){
            messageBox.setText("Date is invalid.");
            console.appendText("\nDate is invalid.");
            messageBox.setVisible(true);
            return;
        }
        Employee toModify = null;
        try {
            float hours = Float.parseFloat(hoursModify.getText());
            if(hours<0||hours>100){
                messageBox.setText("Invalid hours.");
                console.appendText("\nInvalid hours.");
                messageBox.setVisible(true);
                return;
            }
            if (csRadio.isSelected()) {
                toModify = new Parttime(new Profile(lnameR.getText() + "," + fnameR.getText(), "CS", d), 0, hours);
            } else if (eceRadio.isSelected()) {
                toModify = new Parttime(new Profile(lnameR.getText() + "," + fnameR.getText(), "ECE", d), 0, hours);
            } else if (itRadio.isSelected()) {
                toModify = new Parttime(new Profile(lnameR.getText() + "," + fnameR.getText(), "IT", d), 0, hours);
            } else {
                messageBox.setText("Department is not selected.");
                console.appendText("\nDepartment is not selected.");
                messageBox.setVisible(true);
            }
            if(com.setHours(toModify)){
                messageBox.setText("Working hours set successfully.");
                console.appendText("\nWorking hours set successfully.");
            }else{
                messageBox.setText("Employee not found or is not part-time.");
                console.appendText("\nEmployee not found or is not part-time.");
            }
            messageBox.setVisible(true);
        }catch(NumberFormatException e){
            messageBox.setText("Hours is not a number");
            console.appendText("\nHours is not a number");
            messageBox.setVisible(true);
        }
    }

    /**
     * removes an employee. called by the remove employee button in the modify tab
     */
    @FXML
    public void removeEmployee(){
        if(!modifyCompleted()){
            messageBox.setText("One or more required fields are blank");
            console.appendText("\nOne or more required fields are blank");
            messageBox.setVisible(true);
            return;
        }
        Date d = new Date(hiredR.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if(!d.isValid()){
            messageBox.setText("Date is invalid.");
            console.appendText("\nDate is invalid.");
            messageBox.setVisible(true);
            return;
        }
        Employee toRemove = null;
        if(csRadio.isSelected()) {
             toRemove = new Employee(new Profile(lnameR.getText() + "," + fnameR.getText(), "CS", d), 'U');
        }else if(eceRadio.isSelected()){
             toRemove = new Employee(new Profile(lnameR.getText() + "," + fnameR.getText(), "ECE", d), 'U');
        }else if(itRadio.isSelected()){
             toRemove = new Employee(new Profile(lnameR.getText() + "," + fnameR.getText(), "IT", d), 'U');
        }else{
            messageBox.setText("Department is not selected.");
            console.appendText("\nDepartment is not selected.");
            messageBox.setVisible(true);
            return;
        }
        if(com.remove(toRemove)){
            messageBox.setText("Employee removed successfully.");
            console.appendText("\nEmployee removed successfully.");
        }else{
            messageBox.setText("Employee not found.");
            console.appendText("\nEmployee not found.");
        }
        messageBox.setVisible(true);
    }

    /**
     * quits the program. called by the quit button
     */
    @FXML
    public void quit(){ //called by the quit button
        System.exit(0);
    }

    /**
     * calculates the payments. called by the calculate payments button
     */
    @FXML
    public void calculate(){ //called by the process payment button


        if(com.processPayments()){
            messageBox.setText("Calculation of Employee Payments is Done.");
            console.appendText("\nCalculation of Employee Payments is Done.");
        }else{
            messageBox.setText("Employee Database is EMPTY.");
            console.appendText("\nEmployee Database is EMPTY.");
        }

        messageBox.setVisible(true);
    }

    /**
     * imports a database.txt file into the array.
     * @param database the file to import
     * @throws IOException if file cannot be read
     */
    public void importDB(File database) throws IOException {
        if(database == null){
            return;
        }
        boolean containsBadLines = false;
        if(database.exists()){
            //the file exists, read from it and add contents to company by calling add()
            //check for proper formatting in file, else return false

            Scanner scanner = new Scanner(database);
            while (scanner.hasNextLine()) {
                //OPERATE ON LINES HERE
                String[] current = new String[6];
                String line = scanner.nextLine();
                current = line.split(","); //5 arguments
                if(current[0].equals("P")||current[0].equals("F")){ //if correct type position
                    if(current[2].equals("ECE")||current[2].equals("IT")||current[2].equals("CS")){ //correct department
                        Date temp = new Date(current[3]); //the date should be the 3rd argument
                        if(temp.isValid()) { //if date is valid
                            try{
                                String[] name = new String[2];
                                name = current[1].split(" ");
                                Profile profile = new Profile(name[1]+","+name[0],current[2],temp);
                                float salary = Float.parseFloat(current[4]);
                                Employee newEmployee;
                                if(current[0].equals("P")){
                                    newEmployee = new Parttime(profile, salary, 0);
                                }
                                else{
                                    newEmployee = new Fulltime(profile, salary);
                                }
                                com.add(newEmployee);

                            } catch (NumberFormatException e) {
                                console.appendText("\n"+line+" is a BAD LINE in imported File.");
                                containsBadLines = true;
                                return;
                            }
                        }
                    }
                }
                else if(current[0].equals("M")){
                    if(current[2].equals("ECE")||current[2].equals("IT")||current[2].equals("CS")){ //correct department
                        Date temp = new Date(current[3]); //the date should be the 3rd argument
                        if(temp.isValid()) { //if date is valid
                            try{
                                String[] name = new String[2];
                                name = current[1].split(" ");
                                Profile profile = new Profile(name[1]+","+name[0],current[2],temp);
                                float salary = Float.parseFloat(current[4]);
                                Employee newEmployee;
                                int type = Integer.parseInt(current[5]); //default initialize

                                newEmployee = new Management(profile, salary,type);
                                com.add(newEmployee);

                            } catch (NumberFormatException e) {
                                console.appendText("\n"+line+" is a BAD LINE in imported File.");
                                containsBadLines = true;
                                return;
                            }
                        }
                    }
                }else{
                    console.appendText("\n"+line+" is a BAD LINE in imported File.");
                    containsBadLines = true;
                     //bad line
                }
            }

            scanner.close();
            if(!containsBadLines) {
                console.appendText("\nEmployee Database was successfully imported.");
                importStatus.setText("Employee Database was successfully imported.");

            }else{
                console.appendText("\nEmployee Database was imported, but some lines were invalid.");
                importStatus.setText("Employee Database was imported, but some lines were invalid.");
            }
            importStatus.setVisible(true);


        }
        else{
            //file does not exist
            console.appendText("\nFile does not exist.");
            importStatus.setText("File does not exist.");
            importStatus.setVisible(true);
        }
    }

    /**
     * called by the open file button in the import tab
     */
    @FXML
    public void importFile(){
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Import Employee Database");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"); //apply a filter so only text files are allowed
        dialog.getExtensionFilters().add(extFilter); //apply a filter to only allow text files.
        File f = dialog.showOpenDialog(importTab.getScene().getWindow());
        try {
            importDB(f);
        } catch (IOException e) {
            console.appendText("\nFile does not exist.");
            importStatus.setText("File does not exist.");
        }
    }

    /**
     * exports the employee database. called by the Save button in the export tab
     */
    @FXML
    public void exportFile(){
        FileChooser dialog = new FileChooser();
        dialog.setInitialFileName("database.txt");
        dialog.setTitle("Export Employee Database");
        File f = dialog.showSaveDialog(exportTab.getScene().getWindow());
        try {
            if(com.export(f)){
                console.appendText("\nEmployee Database was successfully exported.");
                exportStatus.setText("Employee Database was successfully exported.");
            }else{
                console.appendText("\nFile already exists, or a file could not be created, or the database is empty");
                exportStatus.setText("File already exists, or a file could not be created, or the database is empty");
            }
            exportStatus.setVisible(true);
        } catch (IOException e) {
            console.appendText("\nFile already exists, or a file could not be created.");
            exportStatus.setText("File already exists, or a file could not be created.");
            exportStatus.setVisible(true);
        }
    }




}
