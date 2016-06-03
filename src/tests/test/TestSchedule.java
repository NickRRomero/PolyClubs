package tests.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.logic.*;

/**
 * @author Jacob King
 */
public class TestSchedule {

	// data for test cases using courses and events
	Schedule test = new Schedule();
	Time nine = new Time(9, 00);
	Time ten = new Time(10, 00);
	Time eleven = new Time(11, 00);
	Time noon = new Time(12, 00);
	Date date = new Date(5, 29);
	String description = "Basketball|Cal Poly Rec Center|Intramural game";
	String day = "Monday";
	
	Course math = new Course("math", nine, ten, "M_W_F");
	Course science = new Course("science", ten, eleven, "M_T_W_R");
	Course history = new Course("history", eleven, noon, "M_T_R");
	Event basketball = new Event("M", date, nine, noon, description);
	
	// add method
	@Test
	public void TestAdd() {
		test.add(math);
		test.add(science);
		assertEquals(2, test.getSize());
	}
	
	// remove method
	@Test
	public void TestRemove() {
		test.add(math);
		test.add(history);
		test.remove(math);
		assertEquals(1, test.getSize());
	}
	
	// clear method
	@Test
	public void TestClear() {
		test.add(math);
		test.add(science);
		test.add(history);
		test.clear();
		assertEquals(0, test.getSize());
	}
			
	// get day abbreviation method
	@Test
	public void TestGetDayAbbrev() {
		Schedule test = new Schedule();
		String output = test.getDayAbbrev("Thursday");
		
		assertEquals("R", output);
	}
	 
	// get day of week method
	@Test
	public void TestGetDayOfWeek() {
		Schedule test = new Schedule();
		int output = test.dayOfWeek("R");
		
		assertEquals(4, output);
	}
	
	// sort list method
	@Test
	public void TestSortList() {
		Schedule test = new Schedule();
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(history); // time: 11-12
		list.add(science); // time: 10-11
		list.add(math); // time: 9-10
		
		List<Object> sort = test.sortList(list);
		Course output = (Course) sort.get(0);
		assertEquals("math", output.getName());
	}
	
	// Integration Test with the Event class
	// get event name method
	@Test
	public void TestGetEventName() {
		Schedule test = new Schedule();
		String output = test.getEventName(basketball);
		
		assertEquals("Basketball", output);
	}
	
	// Integration Test with the Time class
	// time check method
	@Test
	public void TestTimeCheckFalse() {
		Schedule test = new Schedule();
		Time start = new Time(12, 00);
		Time end1 = new Time(14, 00);
		Time end2 = new Time(14, 30);
		
		boolean output = test.timeCheck(start, end1, end1, end2);
		assertFalse(output);
	}
	
	// Integration Test with the Time class
	// time check method
	@Test
	public void TestTimeCheckTrue() {
		Schedule test = new Schedule();
		Time start = new Time(12, 00);
		Time end1 = new Time(14, 00);
		Time end2 = new Time(14, 30);
		
		boolean output = test.timeCheck(start, end1, start, end2);
		assertTrue(output);
	}
	
	// Loop test for empty schedule
	// get courses method
	@Test
	public void TestGetCoursesEmpty() {
		Schedule test = new Schedule();
					
		List<Course> output = test.getCourses();
		assertEquals(0, output.size());
	}
	
	// Loop test for one course
	// get courses method
	@Test
	public void TestGetCoursesOne() {
		Schedule test = new Schedule();
		test.add(math);			
		
		List<Course> output = test.getCourses();
		assertEquals("math", output.get(0).getName());
	}

	// Loop test for two courses
	// get courses method
	@Test
	public void TestGetCoursesTwo() {
		Schedule test = new Schedule();
		test.add(math);	
		test.add(basketball);
		test.add(history);
		
		List<Course> output = test.getCourses();
		assertEquals(2, output.size());
	}
	
	// Loop test for empty schedule
	// get day events method
	@Test
	public void TestGetDayEventsEmpty() {
		Schedule test = new Schedule();
		
		List<Object> output = test.getDayEvents(day, date);
		assertEquals(0, output.size());
	}
	
	// Loop test for one event
	// get day events method
	@Test
	public void TestGetDayEventsOne() {
		Schedule test = new Schedule();
		test.add(basketball);
		
		List<Object> list = test.getDayEvents(day, date);
		Event output = (Event) list.get(0);
		assertEquals("Basketball", test.getEventName(output));
	}
	
	// Loop test for one event and one course
	// get day events method
	@Test
	public void TestGetDayEventsTwo() {
		Schedule test = new Schedule();
		test.add(basketball);
		test.add(math);
		
		List<Object> output = test.getDayEvents(day, date);
		assertEquals(2, output.size());
	}
	
	// Loop test for empty list
	// sort list method
	@Test
	public void TestSortListEmpty() {
		Schedule test = new Schedule();
		List<Object> list = test.getDayEvents(day, date);
		
		List<Object> output = test.sortList(list);
		assertEquals(0, output.size());
	}
	
	// Loop test for one event
	// sort list method
	@Test
	public void TestSortListOne() {
		Schedule test = new Schedule();
		test.add(math);
		List<Object> list = test.getDayEvents(day, date);
		
		List<Object> output = test.sortList(list);
		assertEquals(math, output.get(0));
	}
	
	// Loop test for two courses
	// sort list method
	@Test
	public void TestSortListTwo() {
		Schedule test = new Schedule();
		test.add(science);
		test.add(math);
		List<Object> list = test.getDayEvents(day, date);
				
		List<Object> output = test.sortList(list);
		assertEquals(science, output.get(1));
	}
	
	// Loop test for empty list
	// get names method
	@Test
	public void TestGetNamesEmpty() {
		Schedule test = new Schedule();
		List<Object> list = test.getDayEvents(day, date);
		
		List<String> output = test.getNames(list);
		assertEquals(0, output.size());
	}
	
	// Loop test for empty list
	// get names method
	@Test
	public void TestGetNamesOne() {
		Schedule test = new Schedule();
		test.add(history);
		List<Object> list = test.getDayEvents(day, date);
		
		List<String> output = test.getNames(list);
		assertEquals("history", output.get(0));
	}
	
	// Loop test for empty list
	// get names method
	@Test
	public void TestGetNamesTwo() {
		Schedule test = new Schedule();
		test.add(basketball);
		test.add(science);
		List<Object> list = test.getDayEvents(day, date);
				
		List<String> output = test.getNames(list);
		assertEquals("science", output.get(1));
	}
}
