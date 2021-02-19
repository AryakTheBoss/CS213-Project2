package cs213lib;

import java.util.Objects;

public class Employee {

    private Profile profile;
    private float payment = 0.0f;
    public static final int PAY_PERIODS = 26;
    public static final String DOLLAR_FORMAT = "$#,##0.00";

    public Employee(Profile profile){
      this.profile = profile;
    }

    public boolean isPartTime() {
        return this instanceof Parttime;
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
