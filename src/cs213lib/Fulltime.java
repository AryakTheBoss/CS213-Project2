package cs213lib;

import java.text.DecimalFormat;

public class Fulltime extends Employee{

    private float annualSalary;


    public Fulltime(Profile profile, float annualSalary) {
        super(profile);
        this.annualSalary = annualSalary;
    }

    @Override
    public void calculatePayment(){

        super.setPayment(annualSalary/Employee.PAY_PERIODS);

    }

    public float getAnnualSalary(){
        return annualSalary;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,###.##");
        return super.toString()+"::Payment "+df.format(super.getPayment())+"::FULL TIME::"+"Annual Salary "+df.format(annualSalary);
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

}
