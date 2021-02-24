package cs213lib;

import java.text.DecimalFormat;

/**
 * Part time employee
 *  @author Mayank Singamreddy mss390, Aryak Pande amp487
 */
public class Parttime extends Employee{

    private float hourlyRate;
    private float hoursWorked;
    private static final float OVERTIME_HOURS = 80.0f;
    private static final float BONUS_RATE = 1.5f;

    /**
     * Constructs a parttime employee
     * @param profile the employee profile
     * @param hourlyRate the hourly rate
     * @param hoursWorked hours worked
     */
    public Parttime(Profile profile, float hourlyRate, float hoursWorked) {
        super(profile,'P');
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    /**
     * calculate payment for a parttime employee
     */
    @Override
    public void calculatePayment(){
        if(hoursWorked <= OVERTIME_HOURS) {
            super.setPayment(hourlyRate * hoursWorked);
        }else{
            float exceed = hoursWorked - OVERTIME_HOURS; //hours that exceed 80
            super.setPayment(hourlyRate*OVERTIME_HOURS + ((hourlyRate*BONUS_RATE) * exceed)); //for the hours that exceed 80, pay 1.5x hourly rate
        }
    }

    /**
     * sets hours worked for this employee
     * @param hours the hours
     */
    public void setHoursWorked(float hours){
        hoursWorked = hours;
    }

    /**
     * gets hours worked
     * @return the hours worked for this employee
     */
    public float getHoursWorked(){
        return hoursWorked;
    }


    /**
     * returns a string version of a parttime employee
     * @return the string of an employee
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat(Employee.DOLLAR_FORMAT);
        return super.toString()+"::Payment "+df.format(super.getPayment())+"::PART TIME::Hourly Rate "+df.format(hourlyRate)+"::Hours worked this Period: "+hoursWorked;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.isUnknown()) {
            return super.equals(o) && super.isPartTime();
        }else{
            return super.equals(o);
        }
    }

}
