package cs213lib;

import java.text.DecimalFormat;

public class Parttime extends Employee{

    private float hourlyRate;
    private float hoursWorked;
    private static final float OVERTIME_HOURS = 80.0f;
    private static final float BONUS_RATE = 1.5f;


    public Parttime(Profile profile, float hourlyRate, float hoursWorked) {
        super(profile);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    @Override
    public void calculatePayment(){
        if(hoursWorked <= OVERTIME_HOURS) {
            super.setPayment(hourlyRate * hoursWorked);
        }else{
            float exceed = hoursWorked - OVERTIME_HOURS; //hours that exceed 80
            super.setPayment(hourlyRate*OVERTIME_HOURS + ((hourlyRate*BONUS_RATE) * exceed)); //for the hours that exceed 80, pay 1.5x hourly rate
        }
    }

    public void setHoursWorked(float hours){
        hoursWorked = hours;
    }



    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,###.##");
        return super.toString()+"::Payment "+df.format(super.getPayment())+"::PART TIME::Hourly Rate "+df.format(hourlyRate)+"::Hours worked this Period: "+hoursWorked;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

}
