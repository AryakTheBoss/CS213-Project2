package cs213lib;

import java.util.Objects;

public class Employee {

    private Profile profile;
    private float payment = 0.0f;
    public static final int PAY_PERIODS = 26;

    public Employee(Profile profile){
      this.profile = profile;
    }

    public boolean isPartTime(){
        return this instanceof Parttime;
    }
    public boolean isManager(){
        return this instanceof Management;
    }

    @Override
    public boolean equals(Object o) {
       return false;
    }

    public void setPayment(float payment){
        this.payment = payment;
    }
    public float getPayment(){
        return this.payment;
    }

    public Profile getProfile(){
        return profile;
    }

    @Override
    public String toString() {
      return profile.toString();
    }

    public void calculatePayment(){ }//leave blank


}
