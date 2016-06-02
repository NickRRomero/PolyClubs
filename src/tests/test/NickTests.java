package tests.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Nick Suite Test
 * @author Nick Romero
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestClubPrompts.class, TestDatabaseManager.class })
public class NickTests {

}
