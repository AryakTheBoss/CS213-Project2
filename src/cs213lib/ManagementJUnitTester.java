package cs213lib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagementJUnitTester {

    @Test
    @DisplayName("Test Management calculatePayment() method")
    public void assertCalculatePayment(){

        Management m = new Management(new Profile("Jane,Doe","IT",new Date("5/17/2020")),90000,1);

    }

}
