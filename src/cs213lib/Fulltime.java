package cs213lib;

import java.text.DecimalFormat;

/**
 * Full time Employee
 * @author Mayank Singamreddy mss390, Aryak Pande amp487
 */
public class Fulltime extends Employee{

    private float annualSalary; //salary for fulltime employees is annual

    /**
     * Constructor that takes an employee profile and an annual salary
     * @param profile employee Profile
     * @param annualSalary salary
     */
    public Fulltime(Profile profile, float annualSalary) {
        super(profile,'F');
        this.annualSalary = annualSalary;
    }

    /**
     * divide salary by payment time to know how much is to be paid per period
     */
    @Override
    public void calculatePayment(){

        super.setPayment(annualSalary/Employee.PAY_PERIODS);

    }


    /**
    convert information to string
    */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat(Employee.DOLLAR_FORMAT);
        return super.toString()+"::Payment "+df.format(super.getPayment())+"::FULL TIME::"+"Annual Salary "+df.format(annualSalary);
    }

    /**
     * if the employees equal each other, return true
     * @param o the object to compare
     * @return true if equals false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(!super.isUnknown()) {
            return super.equals(o) && super.isFullTime();
        }else{
            return super.equals(o);
        }
    }

    /**
     * Protected version of the equals() method only visible to Management class.
     * @param o object to compare
     * @return true if equals,false otherwise
     */
    protected boolean equalsManager(Object o) {
        if(!super.isUnknown()) {
            return super.equals(o) && super.isManager();
        }else{
            return super.equals(o);
        }
    }

}
