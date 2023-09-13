

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class CheckingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckingTest
{
    /**
     * Default constructor for test class CheckingTest
     */
    public CheckingTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    
    @Test
    public void testCheckingWithdraw()
    {
        
        // overdraftMax is 5,00
        
        Money overdraftMaximum = new Money (5,00);
        Money money1= new Money(5,00);
        Checking account1 = new Checking ("Mathew", "1", money1,overdraftMaximum);
        
        
        Money money2 = new Money (-2,00);
        Checking account2 = new Checking("Mathew" , "1", money2,overdraftMaximum);
        
        Money money3 = new Money (7,00);
        account1.withDraw(money3);
        
        Money money4 = new Money(15,0);
        
        
        
        Checking account3 = new Checking("Mathew" , "1", money1,overdraftMaximum);
        
        

        
        
        assertTrue(account1.equals(account2));
        
        
        Checking account5 = new Checking("Chris","1",money1,overdraftMaximum);
        
            
        
        
        
    }
}
