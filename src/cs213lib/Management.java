package cs213lib;

public class Management extends Fulltime{

    private int positionType;
    public static final int MANAGER = 0;
    public static final int DEPARTMENT_HEAD = 1;
    public static final int DIRECTOR = 2;
    private static final float MAN_BONUS = 5000;
    private static final float DEP_BONUS = 9500;
    private static final float DIR_BONUS = 12000;


    public Management(Profile profile,float annualSalary, int positionType) {
        super(profile,annualSalary);
        this.positionType = positionType;
    }

    @Override
    public void calculatePayment(){

    }

    @Override
    public String toString() {
        return "";
    }

}
