package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all tests that Marii is in charge of.
 * 
 * @author mboyken
 * 
 */

@RunWith(Suite.class)
@SuiteClasses({ ClubAdminTest.class, UserTest.class })
public class MariiTests
{

}