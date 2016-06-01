/*
 * Author: Kevin Costello
 */
package tests.test;

import static org.junit.Assert.*;

import org.junit.Test;
import main.logic.Course;
import main.logic.Time;
public class TestCourseLoop {

	@Test
	public void TestCourseLoop0() {
		Time start = new Time(0, 0);
		Time end = new Time(7, 10);
		// Course offered once a week
		// Loop should run only once
		
		try {
			new Course("CourseName0", start, end, "");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
	}
	
	@Test
	public void TestCourseLoop1() {
		Time start = new Time(0, 0);
		Time end = new Time(20, 02);
		// Course offered once a week
		// Loop should run only once

		try {
			new Course("CourseName1", start, end, "M");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
		
	}
	
	@Test
	public void TestCourseLoop2() {
		Time start = new Time(12, 10);
		Time end = new Time(14, 00);
		// Course offered once a week
		// Loop should run exactly twice

		try {
			new Course("CourseName2", start, end, "M_Sa");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
		
	}
	
	@Test
	public void TestCourseLoop3() {
		Time start = new Time(2, 2);
		Time end = new Time(14, 59);
		// Course offered once a week
		// Loop should run exactly twice

		
		try {
			new Course("CourseName3", start, end, "M_F_Sa");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
	}
	
	@Test
	public void TestCourseLoop6() {
		Time start = new Time(1, 0);
		Time end = new Time(17, 59);
		// Course offered once a week
		// Loop should run exactly twice
		
		try {
			new Course("CourseName6", start, end, "M_T_W_R_Sa_Su");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
	}

	@Test
	public void TestCourseLoop7() {
		Time start = new Time(0, 0);
		Time end = new Time(23, 59);
		// Course offered once a week
		// Loop should run exactly twice
		
		try {
			new Course("CourseName7", start, end, "M_T_W_R_F_Sa_Su");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
		
	}
	
	@Test
	public void TestCourseLoop8() {
		Time start = new Time(0, 0);
		Time end = new Time(21, 59);
		// Course offered once a week
		// Loop should run 7 times, no more
		
		try {
			new Course("CourseName8", start, end, "M_T_W_R_F_Sa_Su_M");
		}catch (Exception e) {
			fail();
		}
		
		assertTrue(true);
		
	}


}
