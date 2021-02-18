package cs213lib;

import java.util.Objects;

public class Employee {

    private Profile profile;
    private float payment = 0.0f;
    private static final int PAY_PERIODS = 26;

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


    @Override
    public String toString() {
      return "";
    }

    public void calculatePayment(){ //idk how to polymorph this

    }
}
