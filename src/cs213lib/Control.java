package cs213lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Control {

    private static Company com;

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




    public Control(){
        com = new Company();
    }

    @FXML
    private void showAll(){
        console.appendText(com.print());
    }
    @FXML
    private void showByDept(){
        console.appendText(com.printByDepartment());
    }
    @FXML
    private void showByDate(){
        console.appendText(com.printByDate());
    }
    @FXML
    private void clearOut(){
        console.setText("");
    }

    private void initAddForm(){

        hourlyRate.setDisable(true);
        hoursWorked.setDisable(true); //disable these since no selection is default
        annualSalary.setDisable(true);
        maRadio.setDisable(true);
        dhRadio.setDisable(true);
        dirRadio.setDisable(true);
        hired.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        csb.setToggleGroup(addTabDeptGroup);
        eceb.setToggleGroup(addTabDeptGroup);
        itb.setToggleGroup(addTabDeptGroup);
        ftRadio.setToggleGroup(addTabTypeGroup);
        ptRadio.setToggleGroup(addTabTypeGroup);
        mRadio.setToggleGroup(addTabTypeGroup);
        maRadio.setToggleGroup(addTabManTypeGroup);
        dhRadio.setToggleGroup(addTabManTypeGroup);
        dirRadio.setToggleGroup(addTabManTypeGroup);
        csb.setSelected(true);

        addTabTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {

                RadioButton rb = (RadioButton)addTabTypeGroup.getSelectedToggle();

                if (rb != null) {
                    String s = rb.getText();
                    switch (s) {
                        case "Full-Time":
                            annualSalary.setDisable(false);
                            hourlyRate.setDisable(true);
                            hoursWorked.setDisable(true);
                            maRadio.setDisable(true);
                            dhRadio.setDisable(true);
                            dirRadio.setDisable(true);
                            break;
                        case "Part-Time":
                            annualSalary.setDisable(true);
                            hourlyRate.setDisable(false);
                            hoursWorked.setDisable(false);
                            maRadio.setDisable(true);
                            dhRadio.setDisable(true);
                            dirRadio.setDisable(true);
                            break;
                        case "Management":
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

    public void initRemoveForm(){
        hiredR.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        csRadio.setToggleGroup(departmentGroup);
        eceRadio.setToggleGroup(departmentGroup);
        itRadio.setToggleGroup(departmentGroup);
        csRadio.setSelected(true);

    }




    @FXML
    public void initialize(){ //initialize ALL GUI elements
        console.setEditable(false); //make the text area read only
        initAddForm(); //initialize the add tab
        initRemoveForm();


    }

    private boolean addFormCompleted(){
       boolean c1 = !fname.getText().isEmpty();
       boolean c2 = !lname.getText().isEmpty();
       boolean c4 = hired.getValue() != null;
       boolean c5 = addTabTypeGroup.getSelectedToggle().isSelected();

       return c1 && c2 && c4 && c5;

    }

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
    @FXML
    private void clearRemoveForm(){
        fnameR.setText("");
        lnameR.setText("");
       csRadio.setSelected(true);
        hiredR.setValue(null);
        hoursModify.setText("");

    }

    @FXML
    public void processAddForm(){ //called by the submit button on the add tab
       // messageBox.setVisible(false);
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
                com.add(new Fulltime(p, annsal));
                messageBox.setText("Employee \"" + p.getName() + "\" added successfully.");
                console.appendText("\nEmployee \"" + p.getName() + "\" added successfully.");
                messageBox.setVisible(true);
                clearAddForm();
            }catch(NumberFormatException e){
                messageBox.setText("Annual Salary value is invalid.");
                console.appendText("\nAnnual Salary value is invalid.");
                messageBox.setVisible(true);
            }
        }else if(selection.getText().equals("Part-Time")){
            Profile p = new Profile(lname.getText()+","+fname.getText(),selection2.getText(),d);
            try {
                float hours = Float.parseFloat(hoursWorked.getText());
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
                com.add(new Parttime(p, rate, hours));
                messageBox.setText("Employee \"" + p.getName() + "\" added successfully.");
                console.appendText("\nEmployee \"" + p.getName() + "\" added successfully.");
                messageBox.setVisible(true);
                clearAddForm();
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
                if(!addTabManTypeGroup.getSelectedToggle().isSelected()){
                    messageBox.setText("Manager type is not selected.");
                    console.appendText("\nManager type is not selected.");
                    messageBox.setVisible(true);
                    return;
                }
                com.add(new Management(p, annsal, selection3.getText().equals("Manager") ? 1 : (selection3.getText().equals("Department Head") ? 2 : 3)));
                messageBox.setText("Manager \"" + p.getName() + "\" added successfully.");
                console.appendText("\nManager \"" + p.getName() + "\" added successfully.");
                messageBox.setVisible(true);
                clearAddForm();
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

    public boolean modifyCompleted(){
        boolean c1 = !fnameR.getText().isEmpty();
        boolean c2 = !lnameR.getText().isEmpty();
        boolean c3 = hiredR.getValue() != null;
        boolean c4 = csRadio.isSelected() || eceRadio.isSelected() || itRadio.isSelected();

        return c1 && c2 && c3 && c4;
    }
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

    @FXML
    public void quit(){ //called by the quit button
        System.exit(0);
    }

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

    @FXML
    public void importFile(){
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Import Employee Database");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        dialog.getExtensionFilters().add(extFilter); //apply a filter to only allow text files.
        File f = dialog.showOpenDialog(importTab.getScene().getWindow());
        try {
            if(com.importDB(f)){
                console.appendText("\nEmployee Database was successfully imported.");
                importStatus.setText("Employee Database was successfully imported.");
            }else{
                console.appendText("\nFile does not exist.");
                importStatus.setText("File does not exist.");
            }
            importStatus.setVisible(true);
        } catch (IOException e) {
            console.appendText("\nFile does not exist or is not readable.");
            importStatus.setText("File does not exist or is not readable.");
            importStatus.setVisible(true);
        }
    }

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
                console.appendText("\nFile already exists, or a file could not be created.");
                exportStatus.setText("File already exists, or a file could not be created.");
            }
            exportStatus.setVisible(true);
        } catch (IOException e) {
            console.appendText("\nFile already exists, or a file could not be created.");
            exportStatus.setText("File already exists, or a file could not be created.");
            exportStatus.setVisible(true);
        }
    }




}
