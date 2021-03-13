package cs213lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * wrapper class that represents a company
 *  @author Mayank Singamreddy mss390, Aryak Pande amp487
 */
public class Company {

    private Employee[] employees; //array to store employees in Company
    private int numEmployee; //track the count of employees
    private static final int GROW_SIZE = 4;

    /**
     * Constructor for Company
     */
    public Company(){
        employees = new Employee[4]; //initialize with 4 books
        numEmployee = 0; //but still empty
    }


    /**
     *  finds the index of the employee being searched for within employees array
     * @param employee the employee to find
     * @return the index if found -1 if not
     */
    private int find(Employee employee){
        if(isEmpty() || employee == null){ //if nothing inside, return false
            return -1;
        }
        for(int i = 0; i< employees.length; i++){ //run through list
            if(employees[i] != null) {
                if (employees[i].equals(employee)) //if match
                    return i; //return index
            }
        }
        return -1; //to check if not found
    }

    /**
     * Grows the bag by 4 spaces.
     */
    private void grow(){ //returns larger size array
        Employee[] newEmployeeArray = new Employee[employees.length+GROW_SIZE]; //increase array length by 4, to copy over
        //run through employee array
        //copy over every element
        System.arraycopy(employees, 0, newEmployeeArray, 0, employees.length);
        employees = newEmployeeArray; //set employees to new bigger array
    }

    /**
     * add a new employee to the employee array
     * @param employee the employee to add
     * @return true if employee was added false if employee already exists
     */
    public boolean add(Employee employee){

        int found = find(employee); //store index of the employee

        if(found != -1){ //if found
            return false; //employee is already in database
        }

        for(int i=0;i<employees.length;i++){ //run through employee array
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //set new employee to empty spot
                numEmployee++; //increase employee count
                return true;
            }
        }
        //if it gets to here it means its full
        grow(); //increase size
        for(int i=0;i<employees.length;i++){
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //after growth, insert in new spot
                numEmployee++; //increase book count
                return true;
            }
        }

        return false;
    }

    /**
     * remove the employee from the employees array
     * @param employee the employee to remove
     * @return true if employee was removed false if not found
     */
    public boolean remove(Employee employee){
        int found = find(employee); //find index, store
        //check if now in an index past the employee
        if(found == -1){ //if employee does not exist
            return false; //didn't happen
        }
        else{
            employees[found] = null; //remove the employee set it to null
            numEmployee-=1; //reduce employee count
            if(found != employees.length-1) { //if the employee does not equal to the very end
                for (int i = found; i < employees.length; i++) { //this loop shifts all the employee to the right of the removed employee down by 1
                    if(i+1 != employees.length) //if there is more space in the array
                        employees[i] = employees[i+1]; //put the new employee there
                    else
                        employees[i] = null; //this is the last space in the array, place it there
                }
            }
            return true;
        }
    }

    /**
     * setter method for employee hours
     * @param employee the employee
     * @return true if sethours false if employee was not found or is not parttime
     */
    public boolean setHours(Employee employee){
        if(employee == null){
            return false;
        }
        if(employee.isPartTime()){//if they are part time

            int i = find(employee); //find index
            if(i != -1){ //if found
                if(employees[i].isPartTime()){ //if part time
                    Parttime p = (Parttime)employees[i];
                    Parttime p2 = (Parttime)employee;
                    p.setHoursWorked(p2.getHoursWorked());
                    return true; //done
                }
            }

        }
        return false; //not a part time employee
    }

    /**
     * checks if the bag is empty
     * @return true if the bag is empty, false otherwise.
     */
    private boolean isEmpty(){
        for (Employee employee : employees) {
            if (employee != null)
                return false;
        }
        return true; //else has books
    }

    /**
     * run calculatePayment for each employee
     * @return true if processed false if database is empty
     */
    public boolean processPayments(){
        if(isEmpty()){ //if no employees

            return false;
        }
        for(Employee emp : employees){ //for each employee
            if(emp != null) //if the employee exists
            emp.calculatePayment(); // calculatePayment()
        }
        return true;
    }

    /**
     * exports the employee database with each employee on one line
     *
     * @param saveLocation the path to the save location
     * @return true if saved successfully, false otherwise
     * @throws IOException if file cannot be read or cannot be created
     */
    public boolean export(File saveLocation) throws IOException { //returns true if database was successfully exported
        if(saveLocation == null){
            return false;
        }
        if(isEmpty()){
            return false;
        }
        if (!saveLocation.exists()) {

                if(saveLocation.createNewFile()){
                    //file created successfully, write the stuff
                    FileWriter myWriter = new FileWriter(saveLocation); //filewriter to write to a file
                    for(int i =0;i<employees.length;i++){//for each employee, write to file
                        if(employees[i] != null) { //if employee is not null
                            myWriter.write(employees[i].toString()); //convert their information to string
                            myWriter.write("\n"); //write a new line to file
                        }
                    }
                    myWriter.flush(); //close and write to the file
                    myWriter.close();

                    return true;
                }else{ //file could not be created
                    return false;
                }

        }else{
            //file already exists
            return false;
        }

    }



    /**
    print all employees' earning statements out
     @return the string of employees
    */
    public String print(){
        String result = "";
        if(isEmpty()){ //if no employees
            result+="\nEmployee Database is empty.";
            return result;
        }


        //print the earning statements
        result+="\n--Printing earning statements for all Employees--\n";
        for(Employee e : employees){ //for each employee
            if(e != null) //if the employee exists
              result+=e+"\n";
        }
        return result;

    }

    /**
    print all employees in order of the departments: cs, ee, iti
     @return the string of employees by department
    */
    public String printByDepartment(){
        String result="";
        if(isEmpty()){ //if no employees
           result+="\nEmployee database is empty.";
            return result;
        }
        result+="\n--Printing earning statements by department--\n";

        //begin bubble sort
        for (int outside = 0; outside < numEmployee-1; outside++) //outer loop
            for (int inside = 0; inside < numEmployee-outside-1; inside++) { //inner loop
                if(employees[inside] != null && employees[inside+1] != null) { //if the employees being compared are not out of order
                    if (employees[inside].getProfile().getDepartment().compareTo(employees[inside+1].getProfile().getDepartment()) >0) {
                        //if the employee's department is out of order

                        // swap arr[j+1] and arr[j]
                        Employee temp = employees[inside]; //save the next employee as a temp value
                        employees[inside] = employees[inside + 1]; //set the current employee to be the next
                        employees[inside + 1] = temp; //set replaced employee to value of temp, to swap them
                    }
                }
            }
        for (Employee employee : employees) { //for each employee
            if (employee != null) //if it exists
                result+=employee+"\n"; //print their name
        }
        result+="**End of List.";
        return result;
    }



    /**
    print employees in order of their date hired
     @return the string of employees by date
    */
    public String printByDate(){ //print by date hired
        String result="";
        if(isEmpty()){ //if the array has no employees in it
            result+="\nEmployee database is empty.";
            return result;
        }
        result+="\n--Printing earning statements by date hired--\n";

        for (int outside = 0; outside < numEmployee-1; outside++) //for each employees
            for (int inside = 0; inside < numEmployee-outside-1; inside++) { //for each employees
                if(employees[inside] != null && employees[inside+1] != null) { //if neither employee is null

                    if (employees[inside].getProfile().getDateHired().compareTo(employees[inside + 1].getProfile().getDateHired()) > 0) {
                        //if the date is greater than the next
                        // swap arr[j+1] and arr[j]

                        Employee temp = employees[inside]; //save the next employee as a temp value
                        employees[inside] = employees[inside + 1]; //set the current employee to be the next
                        employees[inside + 1] = temp; //set replaced employee to value of temp, to swap them
                    }
                }
            }
        for (Employee employee : employees) { //for each employee
            if (employee != null) //if it exists
               result+=employee+"\n"; //print their name
        }
        result+=("**End of List.");

        return result;

    }

}
