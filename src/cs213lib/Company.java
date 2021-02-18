package cs213lib;

public class Company {

    private Employee[] employees;
    private int numEmployee;

    public Company(){
        employees = new Employee[4]; //initialize with 4 books
        numEmployee = 0; //but still empty
    }


    private int find(Employee employee){
        for(int i = 0; i< employees.length; i++){ //run through list
            if(employees[i] != null) { //if the space is empty, add the book there
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
        for(int i=0;i<employees.length;i++){ //run through book array
            newBookArray[i] = employees[i]; //copy over every element
        }
        employees = newBookArray; //set books to new bigger array

    }

    public void add(Employee employee){

        for(int i=0;i<employees.length;i++){ //run through books array
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //set new book to empty spot
                numEmployee++; //increase books count
                return;
            }
        }
        //if it gets to here it means its full
        grow();
        for(int i=0;i<employees.length;i++){
            if(employees[i]==null) { //found empty spot
                employees[i] = employee; //after growth, insert in new spot
                numEmployee++; //increase book count
                return;
            }
        }


    }

    public boolean remove(Employee employee){
        int found = find(employee); //find index, store
        //check if now in an index past the book
        if(found == -1){ //if book doesnt exist
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

    public boolean setHours(Employee employee, float hours){
        if(employee.isPartTime()){
            Parttime p = (Parttime)employee;
            p.setHoursWorked(hours);
            return true;
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
            emp.calculatePayment();
        }
    }

    public void print(){

    }

    public void printByDepartment(){

    }

    public void printByDate(){ //print by date hired

    }

}
