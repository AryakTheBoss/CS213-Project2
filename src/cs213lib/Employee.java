package cs213lib;


public class Employee {

    private Profile profile;
    private float payment = 0.0f;
    private char employeeType;
    public static final int PAY_PERIODS = 26;
    public static final String DOLLAR_FORMAT = "$#,##0.00";


    public Employee(Profile profile, char employeeType){
      this.profile = profile;
      this.employeeType = employeeType;
    }
    public void setManager(){
        employeeType = 'M';
    }

    public boolean isPartTime() {
        return employeeType == 'P';
    }
    public boolean isFullTime() {
        return employeeType == 'F';
    }
    public boolean isManager() {
        return employeeType == 'M';
    }
    public boolean isUnknown(){
        return employeeType == 'U';
    }


    public Profile getProfile(){
        return profile;
    }

    @Override
    public boolean equals(Object o) {
       if(o instanceof Employee){
           Employee other = (Employee) o;
           return profile.equals(other.getProfile());
       }
       return false;
    }

    public void setPayment(float payment){
        this.payment = payment;
    }
    public float getPayment(){
        return this.payment;
    }

    @Override
    public String toString() {
      return profile.toString();
    }

    public void calculatePayment(){ }//leave blank


}
