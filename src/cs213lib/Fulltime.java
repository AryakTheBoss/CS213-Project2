package cs213lib;

public class Fulltime extends Employee{

    private float annualSalary;


    public Fulltime(Profile profile, float annualSalary) {
        super(profile);
        this.annualSalary = annualSalary;
    }

    @Override
    public void calculatePayment(){


    }

    @Override
    public String toString() {
        return "";
    }

}
