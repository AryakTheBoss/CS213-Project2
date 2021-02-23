package cs213lib;


public class Employee {

    private Profile profile; //profile for employee
    private float payment = 0.0f; //payment float
    private char employeeType; //parttime fulltime Management
    public static final int PAY_PERIODS = 26;
    public static final String DOLLAR_FORMAT = "$#,##0.00";


    public Employee(Profile profile, char employeeType){
      this.profile = profile;
      this.employeeType = employeeType;
    }

    /**
     *  setter for Manager employeetype
     */
    public void setManager(){
        employeeType = 'M';
    }

    /**
     *  check if parttime employeetype
     * @return true if is part time
     */
    public boolean isPartTime() {
        return employeeType == 'P';
    }

    /**
     * check for fulltime employeetype
     * @return true if is full time
     */
    public boolean isFullTime() {
        return employeeType == 'F';
    }

    /**
     * check if Manager employeetype
     * @return true if manager
     */
    public boolean isManager() {

        return employeeType == 'M';
    }

    /**
     *  check if employeetype unknown
     * @return true if unknown
     */
    public boolean isUnknown(){
        return employeeType == 'U';
    }

    /**
     * getter for employeeprofile
     * @return the profile
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * if this employee is the employee in argument, return true
     * @param o object to compare to
     * @return true if equal false otherwise
     */
    @Override
    public boolean equals(Object o) {
       if(o instanceof Employee){ //if o is an employee
           Employee other = (Employee) o; //casts o as an employee object

           return profile.equals(other.getProfile()); //return if they are the same
       }
       return false; //else false
    }

    /**
     * setter for payment
     * @param payment the payment amount
     */
    public void setPayment(float payment){
        this.payment = payment;
    }

    /**
     * getter for payment
     * @return the payment
     */
    public float getPayment(){
        return this.payment;
    }

    /**
     * convert employee to a string
     * @return the string
     */
    @Override
    public String toString() {
      return profile.toString();
    }

    /**
     * does nothing
     */
    public void calculatePayment(){ }//leave blank


}
