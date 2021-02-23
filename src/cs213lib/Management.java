package cs213lib;

import java.text.DecimalFormat;

public class Management extends Fulltime{

    private int positionType; //track position type
    public static final int MANAGER = 1; //manager is 1
    public static final int DEPARTMENT_HEAD = 2; //department heads are 2
    public static final int DIRECTOR = 3; //directors are 3
    private static final float MAN_BONUS = 5000; //management bonus is 5000
    private static final float DEP_BONUS = 9500; //department bonus is 9500
    private static final float DIR_BONUS = 12000; //director bonus is 12000

    /*
    constructor for management
    */
    public Management(Profile profile,float annualSalary, int positionType) {
        super(profile,annualSalary);
        this.positionType = positionType;
        super.setManager();
    }
    /*
    calculate payment for all management type employees using their specific constants
    dep, dir, and basic manager, all have different bonus amounts

    */
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
    /*
    Convert the manager to a string of data
    */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat(Employee.DOLLAR_FORMAT);
        switch(positionType){
            case MANAGER:
              //compensation plus pay
                return super.toString()+"::Manager Compensation "+df.format(MAN_BONUS/Employee.PAY_PERIODS);
            //compensation plus pay
            case DEPARTMENT_HEAD:
                return super.toString()+"::Department Head Compensation "+df.format(DEP_BONUS/Employee.PAY_PERIODS);
            //compensation plus pay
            case DIRECTOR:
                return super.toString()+"::Director Compensation "+df.format(DIR_BONUS/Employee.PAY_PERIODS);


            default:
               return "";//Error (should never happen)
        }


    }
    /*
    check if this manager equals the entered one
    */
    @Override
    public boolean equals(Object o) {

        if(!super.isUnknown()) {
            return super.equalsManager(o);
        }else{
            return super.equals(o);
        }
    }

}
