/* 
 * Author: Kevin Costello
 */

package tests.test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.logic.Time;
import main.logic.Course;
import main.logic.Schedule;


public class TestCourseIntegration {

	@Test
	public void TestScheduleAddCourse() {
		Time start;
		Time end;
		Course c;
		Schedule s;
		
		start = new Time(20, 00);
		end = new Time(13, 2);
		c = new Course("CLASSName", start, end, "Description");
		s = new Schedule();
		
		try {
			s.add(c);
			
		} catch (Exception e) {
			fail();
		}
		assertTrue(s.getCourses().contains(c));
	}
	
	@Test
	public void TestScheduleRemoveCourseIntegration() {
		Time start;
		Time end;
		Course c;
		Schedule s;
		
		start = new Time(20, 00);
		end = new Time(13, 2);
		c = new Course("CLASSName", start, end, "Description");
		s = new Schedule();
		
		try {
			s.remove(c);
			
		} catch (Exception e) {
			fail();
		}
		assertFalse(s.getCourses().contains(c));
	}
	

}
