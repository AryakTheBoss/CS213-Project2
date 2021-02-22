package cs213lib;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CompanyTester {

    @Test
    @DisplayName("Test Company add() Method")
    public void assertCompanyAdd(){

        Company cc = new Company();

        Assertions.assertTrue(cc.add(new Fulltime(new Profile("Doe,Jane", "CS", new Date("7/12/2020")), 45000))); //Add employee
        Assertions.assertFalse(cc.add(new Fulltime(new Profile("Doe,Jane", "CS", new Date("7/12/2020")), 65000))); //Add duplicate, should fail
        Assertions.assertTrue(cc.add(new Parttime(new Profile("Charlie,Jane", "CS", new Date("7/12/2020")), 45.6f,0)));
        Assertions.assertFalse(cc.add(new Management(new Profile("Charlie,Jane", "CS", new Date("7/12/2020")), 40000,2))); //try to add same employee but as a manager, should fail
        Assertions.assertTrue(cc.add(new Management(new Profile("Someone,Else", "ECE", new Date("7/12/2020")), 40000,2)));

    }

    @Test
    @DisplayName("Test Company remove() Method")
    public void assertCompanyRemove(){

        Company cc = new Company();
        //Construct a list of employees first
        boolean a,b,c,d;
       a= cc.add(new Fulltime(new Profile("Doe,Jane", "CS", new Date("7/12/2020")), 45000));
       b= cc.add(new Parttime(new Profile("Charlie,Jane", "CS", new Date("7/12/2020")), 45.6f,0));
        c= cc.add(new Management(new Profile("Someone,Else", "ECE", new Date("7/12/2020")), 60000,2));
        d= cc.add(new Fulltime(new Profile("Super,Mario", "IT", new Date("7/18/2020")), 95000));

        Assertions.assertTrue(a && b && c && d); //these should all be true
        //now to test remove()
       Assertions.assertTrue(cc.remove(new Employee(new Profile("Charlie,Jane", "CS", new Date("7/12/2020")),'U'))); //should remove successfully
        Assertions.assertFalse(cc.remove(new Employee(new Profile("DoesntNotExist,Definitely", "ECE", new Date("7/18/2020")),'U'))); //should not remove since does not exist

    }

}
