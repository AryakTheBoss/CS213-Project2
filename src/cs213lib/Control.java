package cs213lib;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Control {

    private static Company com;


    @FXML private TextArea console;

    /** ADD TAB COMPONENTS **/

    @FXML private TextField fname;
    @FXML private TextField lname;
    @FXML private ChoiceBox<String> dept;
    @FXML private DatePicker hired;
    @FXML private ChoiceBox<String> emptype;
    @FXML private TextField annualSalary;
    @FXML private TextField hourlyRate;
    @FXML private TextField hoursWorked;
    @FXML private ChoiceBox<String> mantype;
    @FXML private Label messageBox;

    /** REMOVE TAB COMPONENTS **/

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
        String[] departments = { "CS", "ECE", "IT"};
        dept.setItems(FXCollections.observableArrayList(departments));

        String[] empTypes = {"Full-Time", "Part-Time","Manager"};
        emptype.setItems(FXCollections.observableArrayList(empTypes));
        hourlyRate.setDisable(true);
        hoursWorked.setDisable(true); //disable these since no selection is default
        annualSalary.setDisable(true);
        mantype.setDisable(true);
        hired.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        String[] managerTypes = {"Manager", "Department Head","Director"};
        mantype.setItems(FXCollections.observableArrayList(managerTypes));

        emptype.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value) //enable and disable text fields depending on which employee type is currently selected.
            {
                int index = new_value.intValue();
                try {
                    if (empTypes[index].equals("Full-Time")) {
                        annualSalary.setDisable(false);
                        hourlyRate.setDisable(true);
                        hoursWorked.setDisable(true);
                        mantype.setDisable(true);
                    } else if (empTypes[index].equals("Part-Time")) {
                        annualSalary.setDisable(true);
                        hourlyRate.setDisable(false);
                        hoursWorked.setDisable(false);
                        mantype.setDisable(true);
                    } else {
                        annualSalary.setDisable(false);
                        hourlyRate.setDisable(true);
                        hoursWorked.setDisable(true);
                        mantype.setDisable(false);
                    }
                }catch(ArrayIndexOutOfBoundsException e){

                    hourlyRate.setDisable(true);
                    hoursWorked.setDisable(true); //disable these since no selection is default
                    annualSalary.setDisable(true);
                    mantype.setDisable(true);
                }
            }
        });
    }

    @FXML
    public void initialize(){ //initialize ALL GUI elements
        console.setEditable(false); //make the text area read only
        initAddForm(); //initialize the add tab


    }

    private boolean addFormCompleted(){
       boolean c1 = !fname.getText().isEmpty();
       boolean c2 = !lname.getText().isEmpty();
       boolean c3 = !dept.getSelectionModel().isEmpty();
       boolean c4 = hired.getValue() != null;
       boolean c5 = !emptype.getSelectionModel().isEmpty();

       return c1 && c2 && c3 && c4 && c5;

    }

    private void clear(){
        fname.setText("");
        lname.setText("");
        dept.getSelectionModel().clearSelection();
        hired.setValue(null);
        emptype.getSelectionModel().clearSelection();
        annualSalary.setText("");
        hourlyRate.setText("");
        hoursWorked.setText("");
        mantype.getSelectionModel().clearSelection();
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
        }

        if(emptype.getSelectionModel().getSelectedIndex() == 0){
            Profile p = new Profile(lname.getText()+","+fname.getText(),dept.getSelectionModel().getSelectedItem(),d);
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
                clear();
            }catch(NumberFormatException e){
                messageBox.setText("Annual Salary value is invalid.");
                console.appendText("\nAnnual Salary value is invalid.");
                messageBox.setVisible(true);
            }
        }else if(emptype.getSelectionModel().getSelectedIndex() == 1){
            Profile p = new Profile(lname.getText()+","+fname.getText(),dept.getSelectionModel().getSelectedItem(),d);
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
                clear();
            }catch(NumberFormatException e){
                messageBox.setText("Invalid rate or hours entered.");
                console.appendText("\nInvalid rate or hours entered.");
                messageBox.setVisible(true);
            }
        }else if(emptype.getSelectionModel().getSelectedIndex() == 2){
            Profile p = new Profile(lname.getText()+","+fname.getText(),dept.getSelectionModel().getSelectedItem(),d);
            try {
                float annsal = Float.parseFloat(annualSalary.getText());
                if(annsal < 0){
                    messageBox.setText("Annual Salary can't be negative.");
                    console.appendText("\nAnnual Salary can't be negative.");
                    messageBox.setVisible(true);
                    return;
                }
                if(mantype.getSelectionModel().isEmpty()){
                    messageBox.setText("Manager type is not selected.");
                    console.appendText("\nManager type is not selected.");
                    messageBox.setVisible(true);
                    return;
                }
                com.add(new Management(p, annsal, mantype.getSelectionModel().getSelectedIndex() + 1));
                messageBox.setText("Manager \"" + p.getName() + "\" added successfully.");
                console.appendText("\nManager \"" + p.getName() + "\" added successfully.");
                messageBox.setVisible(true);
                clear();
            }catch(NumberFormatException e){
                messageBox.setText("Annual Salary value is invalid.");
                console.appendText("\nAnnual Salary value is invalid.");
                messageBox.setVisible(true);
            }
        }else{
            messageBox.setText("Unknown Error has Occured.");
            console.setText("\nUnknown Error has Occured.");
            messageBox.setVisible(true);
        }

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




}
