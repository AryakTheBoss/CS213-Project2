package cs213lib;

public class Company {

    private Employee[] employees;
    private int numEmployee;

    public Company(){
        employees = new Employee[4]; //initialize with 4 books
        numEmployee = 0; //but still empty
    }


    private int find(Employee employee){
        if(isEmpty()){
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
        Employee[] newBookArray = new Employee[employees.length+4]; //increase array length by 4, to copy over
        //run through book array
        //copy over every element
        System.arraycopy(employees, 0, newBookArray, 0, employees.length);
        employees = newBookArray; //set books to new bigger array

    }

    public boolean add(Employee employee){

        int found = find(employee);

        if(found != -1){
            return false; //employee is already in database
        }

        for(int i=0;i<employees.length;i++){ //run through books array
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //set new book to empty spot
                numEmployee++; //increase books count
                return true;
            }
        }
        //if it gets to here it means its full
        grow();
        for(int i=0;i<employees.length;i++){
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //after growth, insert in new spot
                numEmployee++; //increase book count
                return true;
            }
        }

        return false;


    }

    public boolean remove(Employee employee){
        int found = find(employee); //find index, store
        //check if now in an index past the book
        if(found == -1){ //if book does not exist
            return false; //didn't happen
        }
        else{
            employees[found] = null; //remove the book set it to null
            numEmployee-=1; //reduce book count
            if(found != employees.length-1) { //if the book does not equal to the very end
                for (int i = found; i < employees.length; i++) { //this loop shifts all the books to the right of the removed book down by 1
                    if(i+1 != employees.length) //if there is more space in the array
                        employees[i] = employees[i+1]; //put the new book there
                    else
                        employees[i] = null; //this is the last space in the array, place it there
                }
            }
            return true;
        }
    }

    public boolean setHours(Employee employee){
        if(employee.isPartTime()){

            int i = find(employee);
            if(i != -1){
                if(employees[i].isPartTime()){
                    Parttime p = (Parttime)employees[i];
                    Parttime p2 = (Parttime)employee;
                    p.setHoursWorked(p2.getHoursWorked());
                    return true;
                }
            }

        }
        return false;
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

    public void processPayments(){
        for(Employee emp : employees){
            if(emp != null)
            emp.calculatePayment();
        }
    }

    public void print(){

        System.out.println("--Printing earning statements for all Employees--");
        for(Employee e : employees){
            if(e != null)
            System.out.println(e);
        }

    }

    public void printByDepartment(){
        if(isEmpty()){
            System.out.println("Employee database is empty.");
            return;
        }
        System.out.println("--Printing earning statements by department--");
        for (int outside = 0; outside < numEmployee-1; outside++) //for each book
            for (int inside = 0; inside < numEmployee-outside-1; inside++) { //for each book
                if(employees[inside] != null && employees[inside+1] != null) { //if neither book is null
                    if (employees[inside].getProfile().getDepartment().compareTo(employees[inside+1].getProfile().getDepartment()) >0) {
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
                System.out.println(employee); //print their name
        }
        System.out.println("**End of List.");
    }




    public void printByDate(){ //print by date hired
        if(isEmpty()){ //if the array has no employees in it
            System.out.println("Employee database is empty.");
            return;
        }
        System.out.println("--Printing earning statements by date hired--");

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
                System.out.println(employee); //print their name
        }
        System.out.println("**End of List.");
    }

}
