package cs213lib;

public class PayrollProcessing {

    public void run(){ //yeet

        Company cc = new Company();
        System.out.println(cc.add(new Fulltime(new Profile("Jane,Doe","CS",new Date("1/12/2021")),85000)));
        System.out.println(cc.add(new Parttime(new Profile("Jane,Doe","ECE",new Date("1/12/2021")),49.5f,0)));
        System.out.println(cc.add(new Management(new Profile("Jane,Doe","IT",new Date("1/12/2021")),85000,Management.MANAGER)));
        System.out.println(cc.add(new Parttime(new Profile("Jane,Doe","CS",new Date("1/12/2021")),44.6f,0)));
        //cc.processPayments();
        cc.print();
        cc.processPayments();
        cc.print();

    }

}
