package cs213lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  @author Mayank Singamreddy mss390, Aryak Pande amp487
 */
public class ManagementJUnitTester {

    @Test
    @DisplayName("Test Management calculatePayment() method")
    public void assertCalculatePayment(){

        Management m = new Management(new Profile("Jane,Doe","IT",new Date("5/17/2020")),90000,1);

        m.calculatePayment();
        //Used a calculator to get expected values
        Assertions.assertEquals(3653.85,Math.round(m.getPayment()*100.0)/100.0); //use Math.round to round to 2 decimal places
        m = new Management(new Profile("Jane,Doe","IT",new Date("5/17/2020")),75000,2);
        m.calculatePayment();
        Assertions.assertEquals(3250.0,Math.round(m.getPayment()*100.0)/100.0);
        m = new Management(new Profile("Jane,Doe","IT",new Date("5/17/2020")),56000,3);
        m.calculatePayment();
        Assertions.assertEquals(2615.38,Math.round(m.getPayment()*100.0)/100.0);
    }

}
