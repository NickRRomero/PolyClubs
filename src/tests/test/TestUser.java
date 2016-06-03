package tests.test;

/**
 * Unit tests for User class.
 * Schedule tests are commented out to prevent database manipulation.
 * Uncomment out the tests to run them.
 * Be sure to remove excess from the database after running tests.
 * @author mboyken
 */

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.logic.*;

public class TestUser
{
   private User user1;
   private User user2;

   public TestUser() throws Exception
   {
      user1 = new User("Marii", "(270) 314-6742", "008602954",
            "mboyken@calpoly.edu");
      user2 = new User("Jesus", "8675309", "000000001",
            "jc@christianmingle.com");
   }

   /**
    * Test getter for User's name.
    */
   @Test
   public void testGetName()
   {
      assertEquals("Marii", user1.getName());
   }

   /**
    * Test getter for User's email.
    */
   @Test
   public void testGetEmail()
   {
      assertEquals("jc@christianmingle.com", user2.getEmail());
   }

   /**
    * Test createSchedule loop for only one class.
    */
    @Test public void testCreateScheduleOne()
    {
    	List<String> classes = new ArrayList<String>();
    	List<Course> courseList;
        Schedule schedule;
        
        classes.add("MATH241 11:00-12:00 M_W_F"); schedule =
        user1.createSchedule(classes); courseList = schedule.getCourses();
        assertEquals("MATH241", courseList.get(0).getName());
    }

   /**
    * Test createSchedule loop for only two classes.
    */
   @Test
   public void testCreateScheduleTwo()
   {
	   List<String> classes = new ArrayList<String>();
	   List<Course> courseList;
	   Schedule schedule;
	   
	   classes.add("MATH241 11:00-12:00 M_W_F");
	   classes.add("CSC225 15:00-18:00 M_W_F");
	   schedule =user1.createSchedule(classes);
	   courseList = schedule.getCourses();
	   assertEquals("CSC225", courseList.get(1).getName());
	}

   /**
    * Test createSchedule loop for three classes.
    */
   @Test
   public void testCreateScheduleThree()
   {
	   List<String> classes = new ArrayList<String>();
	   List<Course> courseList;
	   Schedule schedule;
	   
	   classes.add("MATH241 11:00-12:00 M_W_F");
	   classes.add("CSC225 15:00-18:00 M_W_F");
	   classes.add("CPE103 10:00-12:00 T_R");
	   schedule = user1.createSchedule(classes);
	   courseList = schedule.getCourses();
	   assertEquals("CPE103", courseList.get(2).getName());
   }

   /**
    * Test createSchedule loop for four classes.
    */
   @Test
   public void testCreateScheduleFour()
   {
	   List<String> classes = new ArrayList<String>();
	   List<Course> courseList;
	   Schedule schedule;
	   
	   classes.add("MATH241 11:00-12:00 M_W_F");
	   classes.add("CSC225 15:00-18:00 M_W_F");
	   classes.add("CPE103 10:00-12:00 T_R");
	   classes.add("ART101 14:30-16:30 T_R");
	   schedule = user1.createSchedule(classes);
	   courseList = schedule.getCourses();
	   assertEquals("ART101", courseList.get(3).getName());
	}

   /**
    * Test addMsg to another User.
    */
   @Test
   public void testAddMsg()
   {
      user1.addMsg("Son of the original G.", "Jesus");

      assertEquals("From: Jesus Message: Son of the original G.",
            user1.getMsg(0));
   }

   /**
    * Test getMsg of User.
    */
   @Test
   public void testSetMsg()
   {
      user2.addMsg("Yo, JC.", "Marii");
      assertEquals("From: Marii Message: Yo, JC.", user2.getMsg(0));
   }
}
