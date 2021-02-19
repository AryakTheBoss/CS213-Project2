package cs213lib;

import java.text.DecimalFormat;

public class Management extends Fulltime{

    private int positionType;
    public static final int MANAGER = 1;
    public static final int DEPARTMENT_HEAD = 2;
    public static final int DIRECTOR = 3;
    private static final float MAN_BONUS = 5000;
    private static final float DEP_BONUS = 9500;
    private static final float DIR_BONUS = 12000;


    public Management(Profile profile,float annualSalary, int positionType) {
        super(profile,annualSalary);
        this.positionType = positionType;
    }

    @Override
    public void calculatePayment(){
        super.calculatePayment();
        switch(positionType){
            case MANAGER:
                super.setPayment(super.getPayment() + (MAN_BONUS/Employee.PAY_PERIODS));
                break;
            case DEPARTMENT_HEAD:
                super.setPayment(super.getPayment() + (DEP_BONUS/Employee.PAY_PERIODS));
                break;
            case DIRECTOR:
                super.setPayment(super.getPayment() + (DIR_BONUS/Employee.PAY_PERIODS));
                break;

            default:
                super.setPayment(-1); //Error (should never happen)
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat(Employee.DOLLAR_FORMAT);
        switch(positionType){
            case MANAGER:
                return super.toString()+"::Manager Compensation "+df.format(MAN_BONUS/Employee.PAY_PERIODS);

            case DEPARTMENT_HEAD:
                return super.toString()+"::Department Head Compensation "+df.format(DEP_BONUS/Employee.PAY_PERIODS);

            case DIRECTOR:
                return super.toString()+"::Director Compensation "+df.format(DIR_BONUS/Employee.PAY_PERIODS);


            default:
               return "";//Error (should never happen)
        }


    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && o instanceof Management;
    }

}
