/*
 * Author: Kevin Costello
 */

package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.logic.Course;
import main.logic.Time;

public class TestCourse {
	
	@Test
	public void TestSetStartHour() {
		Time start = new Time(9,49);
		Time end = new Time(6, 21);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		TestCourse.setStartTime(new Time(5, 25));
		int returnHour = TestCourse.getStart().getHour();
		assertEquals(returnHour, 5, 0);
	}
	
	@Test
	public void TestSetStartMinute() {
		Time start = new Time(21, 0);
		Time end = new Time(5, 25);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		TestCourse.setStartTime(new Time(5, 25));
		int returnMinute = TestCourse.getStart().getMinute();
		
		assertEquals(returnMinute, 25, 0);
	}
	
	
	@Test
	public void TestSetEndHour() {
		Time start = new Time(21, 57);
		Time end = new Time(5, 25);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		TestCourse.setEndTime(new Time(5, 0));
		int returnHour = TestCourse.getEnd().getHour();
		
		assertEquals(returnHour, 5, 0);
	}
	
	@Test
	public void TestSetEndMinute() {
		Time start = new Time(0, 57);
		Time end = new Time(0, 25);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		TestCourse.setEndTime(new Time(5, 0));
		int returnMinute = TestCourse.getEnd().getMinute();
		
		assertEquals(returnMinute, 0, 0);
	}
	
	
	public void TestGetNameNoSpace() {
		Time start = new Time(10, 57);
		Time end = new Time(13, 25);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		String output = TestCourse.getName();
		
		assertEquals(output, "Bio101");
	}
	
	public void TestGetNameSpace() {
		Time start = new Time(0, 0);
		Time end = new Time(23, 59);
		Course TestCourse = new Course("Bio101", start, end, "Basic bio course");
		
		String output = TestCourse.getName();
		
		assertEquals(output, "Bio 32222");
	}
	
	
	

}
