package cs213lib;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CompanyTester {

    @Test
    @DisplayName("Test Company Add Method")
    public void assertCompany(){

        Company cc = new Company();

        Assertions.assertTrue(cc.add(new Fulltime(new Profile("Doe,Jane", "CS", new Date("7/12/2020")), 45000))); //Add employee
        Assertions.assertFalse(cc.add(new Fulltime(new Profile("Doe,Jane", "CS", new Date("7/12/2020")), 65000))); //Add duplicate, should fail
        Assertions.assertTrue(cc.add(new Parttime(new Profile("Charlie,Jane", "CS", new Date("7/12/2020")), 45.6f,0)));

    }

}
