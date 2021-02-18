package cs213lib;

public class Parttime extends Employee{

    private float hourlyRate;
    private float hoursWorked;


    public Parttime(Profile profile, float hourlyRate) {
        super(profile);
        this.hourlyRate = hourlyRate;
    }
    @Override
    public void calculatePayment(){

    }

    public void setHoursWorked(float hours){
        hoursWorked = hours;
    }

    @Override
    public String toString() {
        return "";
    }

}
