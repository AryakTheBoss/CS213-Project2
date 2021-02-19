package cs213lib;

public class PayrollProcessing {

    public void run(){ //yeet

        Company cc = new Company();

        cc.add(new Fulltime(new Profile("Jane,Doe","CS",new Date("1/12/2021")),85000));
        cc.add(new Parttime(new Profile("Jane,Doe","ECE",new Date("1/12/2021")),49.5f,0));
        cc.add(new Management(new Profile("Jane,Doe","IT",new Date("1/12/2021")),85000,Management.MANAGER));
        cc.processPayments();
        cc.print();

    }

}
