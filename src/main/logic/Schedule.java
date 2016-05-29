package main.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by jacob on 4/24/2016.
 */
public class Schedule {
    private ArrayList<Object> schedule;
    boolean LeapYear = false;
    
    /**
     * simple constructor initializes schedule
     */
    public Schedule() {
    	schedule = new ArrayList<Object>();
    }

    /**
     * add course or event to schedule
     * @param obj
     */
    public void add(Object obj) {
    	schedule.add(obj);
    }
    
    /**
     * remove course or event from schedule
     * @param event
     */
    public void remove(Object obj) {
        schedule.remove(obj);
    }
    
    /**
     * clear schedule
     */
    public void clear() {
    	schedule.clear();
    }
    
    /**
     * get the number of courses/events in the schedule
     * @return
     */
    public int getSize() {
    	return schedule.size();
    }
    
    /**
     * function that returns all the courses in a schedule
     * @return Arraylist of courses
     */
    public ArrayList<Course> getCourses() {
    	ArrayList<Course> courses = new ArrayList<Course>();
    	
    	// if object is a course, add it to list
    	for (Object obj : schedule) {
    		if (obj instanceof Course) 
    			courses.add((Course) obj);
    	}
    	
    	return courses;
    }
    
    /**
     * helper method for hasConflict method
     * check if two events/courses have conflicting times
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return if times are in conflict
     */
    public boolean timeCheck(Time start1, Time end1, Time start2, Time end2) {
    	// the first event ends before the second event starts
    	if (end1.isEarlier(start2))
    		return false;
    	
    	// the first event starts after the second event ends
    	if (end2.isEarlier(start1))
    		return false;
    	
    	// both conditions are false so there is a conflict
    	return true;
    }
    
    /**
     * determine if an event conflicts with a user's schedule
     * @param event
     * @return if event conflicts with a scheduled course/event
     */
    public boolean hasConflict(Event event) {
    	// course and event to be set in loop
    	Course c = null;
    	Event e = null;
    	
    	boolean conflict = false;
    	
    	// loop through each event/course in schedule
    	for (Object obj : schedule) {
    		
    		// if obj is course, determine if event and course are on same day
    		if (obj instanceof Course) {
    			c = (Course) obj;
    			
    			// check each day that the course is on
    			for(String day : c.getDays()) {
    				
    				// if on same day, check if times are in conflict
    				if (event.getDay().equals(day)) {
    					conflict = timeCheck(c.getStart(), c.getEnd(), 
    		    				event.getStartTime(), event.getEndTime());
    					
    					System.out.println(c.getName());
    					break;
    				}
    			}
    		}
    		// if obj is event, determine if events are on same date
    		else {
    			e = (Event) obj;
    			
    			// if on same date, check if times are in conflict
	    		if (event.getDate().equals(e.getDate())) {
					conflict = timeCheck(e.getStartTime(), e.getEndTime(), 
		    				event.getStartTime(), event.getEndTime());
					
					System.out.println(e.getDescrip());
	    		}
    		}
    		
    		if (conflict)
				return true;
    	}
    	
    	return false;
    }
    
    /**
     * return the name of the event
     * @param event
     * @return event name
     */
    public String getEventName(Event event) {
    	return event.getDescrip().split("\\|")[0];
    }
    
    /**
     * get current date 
     * @return current date
     */
    public Date getCurrentDate() {
    	java.util.Date date = new java.util.Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd");
    	
    	String current = dateFormat.format(date);
    	String[] split = current.split(" ");
    	
    	int month = Integer.parseInt(split[0]);
    	int day = Integer.parseInt(split[1]);
    	
    	return new Date(month, day);
    }
    
 	/**
 	 * get current day of week
 	 * @return current day of week
 	 */
    public String getCurrentDay() {
    	java.util.Date date = new java.util.Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("E");
    	
    	String day = dateFormat.format(date);
    	
    	return getDayAbbrev(day);
    }
    
    /**
     * Get the abbreviation of the day used by the 
     * Course and Event classes
     * @param day
     * @return day abbreviation
     */
    public String getDayAbbrev(String day) {
    	Character current = day.charAt(0);
    	
    	// if Saturday or Sunday, return first two letters
		if (current.equals('S'))
    		return day.substring(0, 2);
		
		// if Thursday, return "R"
		else if (day.substring(0, 2).equals("Th"))
			return "R";
		
		// else return first letter
    	else
    		return current.toString();
    }
    
    /**
     * return the number of the day of the week 
     * from 0-6 with Sunday = 0 
     * @param day abbreviation
     * @return day number
     */
    public int dayOfWeek (String day) {
    	int dayNum = -1;
    	
    	switch (day) {
    		case "Su":
    			dayNum = 0;
    			break;
    		case "M":
    			dayNum = 1;
    			break;
    		case "T":
    			dayNum = 2;
    			break;
    		case "W":
    			dayNum = 3;
    			break;
    		case "R":
    			dayNum = 4;
    			break;
    		case "F":
    			dayNum = 5;
    			break;
    		case "Sa":
    			dayNum = 6;
    			break;
    	}
    	
    	return dayNum;
    }
    
    /**
     * get the dates of the week
     * @param date
     * @return array of current week's dates
     */
    public Date[] getWeek(Date date) {
    	int month = date.getMonth();
    	int day = date.getDay();
    	int maxDays;
    	
    	// determine how many days are in current month
    	if (month == 2) {
    		if (LeapYear)
    			maxDays = 29;
    		else
    			maxDays = 28;
    	}
    	else if (month == 4 || month == 6 || month == 9 || month == 11)
    		maxDays = 30;
    	else
    		maxDays = 31;
    	
    	// array to hold the week's dates
    	Date[] dates = new Date[7];
    	int index = 1;
    	
    	// add current date to array
    	dates[0] = date;
    	
    	// add the next six dates to array
    	while (index < dates.length) {
    		// if the last day of month, move to next month
    		if (day == maxDays) {
    			day = 1;
    			month++;
    		}
    		else
    			day++;
    		
    		dates[index++] = new Date(month, day);
    	}
    	
    	return dates;
    }
    
    /**
     * get the scheduled courses/events for the given day
     * @param dayOfWeek
     * @return an arraylist of courses/events
     */
    public ArrayList<Object> getDayEvents(String dayOfWeek, Date date) {
    	// list of events on the day
    	ArrayList<Object> events = new ArrayList<Object>();
    	
    	// used when object in schedule is course or event
    	Course c = null;
    	Event e = null;
    	
    	// get day abbreviation
    	String day = getDayAbbrev(dayOfWeek);
    	
    	// loop through each event/course in schedule
    	for (Object obj : schedule) {
    		
    		// if obj is course, determine if course is on day
    		if (obj instanceof Course) {
    			c = (Course) obj;
    			
    			// check each day that the course is on
    			for(String d : c.getDays()) {
    				
    				// if on same day, add course to list
    				if (day.equals(d)) {
    					events.add(c);
    					break;
    				}
    			}
    		}
    		// if obj is event, determine if event is on day
    		else {
    			e = (Event) obj;
    			
    			// get the day that the event is on
    			String d = e.getDay();
    			Date da = e.getDate();
    			
    			// if on same day, add event to list
				if (day.equals(d) && date.isEqual(da))
					events.add(e);    		
    		}
    	}
    	
    	return events;
    }
    
    /**
     * Sort list of courses/events
     * @param events
     * @return a sorted ArrayList 
     */
    public ArrayList<Object> sortList(ArrayList<Object> events) {
    	// list to be returned
    	ArrayList<Object> newList = new ArrayList<Object>();
    	
    	// time which will always be greater than time compared to it
    	Time minComp = new Time(24, 00);
    	
    	// object which will be added to list each iteration
    	Object minCE = null;
    	
    	// minimum start time of objects in events
    	Time min = minComp;
    	
    	// loop until all objects are removed from events
    	while (events.size() > 0) {
	    	for (Object obj : events) {
	    		if (obj instanceof Course) {
	    			Course c = (Course) obj;
	    			
	    			// if start time is earlier than min
	    			// set min to start time and minCE to course
	    			if (c.getStart().isEarlier(min)) {
	    				min = c.getStart();
	    				minCE = c;
	    			}
	    				
				}	
	    		else {
	    			Event e = (Event) obj;  		
	    			
	    			// if start time is earlier than min
	    			// set min to start time and minCE to event
	    			if (e.getStartTime().isEarlier(min)) {
	    				min = e.getStartTime();
	    				minCE = e;
	    			}	    				
	    		}
	    	}
	   
	    	// remove minCE from events and add it to newList
	    	// reset min
	    	events.remove(minCE);
	    	newList.add(minCE);
	    	min = minComp;
    	}

    	return newList;
    }
    
    /**
     * Take a list of courses/events and return the names
     * @param events
     * @return an ArrayList of names
     */
    public ArrayList<String> getNames(ArrayList<Object> events) {
    	ArrayList<String> names = new ArrayList<String>();
    	for (Object obj : events) {
    		if (obj instanceof Course) {
    			Course c = (Course) obj;
    			names.add(c.getName());		
			}	
    		else {
    			Event e = (Event) obj;  		
    			names.add(getEventName(e));	
    		}
    	}
    	
    	return names;
    }
    
    /**
     * get the times that the courses/events start and end at
     * @param events
     * @return a list of times
     */
    public ArrayList<String> getTimes(ArrayList<Object> events) {
    	ArrayList<String> times = new ArrayList<String>();
    	for (Object obj : events) {
    		if (obj instanceof Course) {
    			Course c = (Course) obj;
    			String time = c.getStart() + "-" + c.getEnd();
    			times.add(time);	
			}	
    		else {
    			Event e = (Event) obj; 
    			String time = e.getStartTime() + "-" + e.getEndTime();
    			times.add(time);
    		}
    	}
    	
    	return times;
    }
    
    /**
     * display weekly calendar of courses
     */
    public void displayWeek() {
    	String day = getCurrentDay(); // current day of week
    	Date date = getCurrentDate(); // current date 
    	
    	String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday",
    			"Thursday", "Friday", "Saturday"};
    	
    	// the dates of the current week starting with the current day
    	Date[] week = getWeek(date);
    	
    	// index for current day of week, used for days array
    	int num = dayOfWeek(day);
    	    	    	
    	ArrayList<Object> events; // courses/events on a given day
    	ArrayList<Object> sorted; // "events" sorted
    	ArrayList<String> names; // names of the courses/events in "events"
    	ArrayList<String> times; // times of the courses/events in "events"
    	
    	int[] daypad = {9, 9, 9, 11, 10, 9, 10}; // padding data for each day
    	int datepad = 8; // number of padded spaces for date
    	int barpad = 13; // number of padded spaces for bar
    	int timepad = 20; // number of padded spaces for event time
    	String dayOW; // day of week
    	int pad; // number of padded spaces for day of week
    	
    	// print out calendar
    	int count = 0;
    	while (count < 7) {
    		dayOW = days[num];
    		pad = daypad[num];

    		events = getDayEvents(dayOW, week[count]);
    		sorted = sortList(events);
    		names = getNames(sorted);
    		times = getTimes(sorted);
    		
    		// print day of week and first 2 bars
	    	System.out.println(String.format("%" + barpad + "s", "|"));
	    	System.out.print(String.format("%" + pad + "s" + 
	   	    	 "%" + (barpad-pad) + "s     ", dayOW, "|"));
	    	
	    	// print names of courses/events
	    	for (String name : names) 
	    		System.out.print(String.format("%-" + timepad + "s", name));
	    	
	    	// print date and bar
	    	System.out.print("\n" + String.format("%" + datepad + "s" + 
	    			"%" + (barpad-datepad) + "s     ", 
	    			week[count].toString(), "|"));
	    	
	    	// print times of courses/events
	    	for (String time : times)
	    		System.out.print(String.format("%-" + timepad + "s", time));
	    	
	    	// print last bar and dashes
	    	System.out.println("\n" + String.format("%" + barpad + "s", "|"));
	    	System.out.println(String.format("%100s", "").replace(' ', '-'));
	    	
	    	// move to the next day
	    	if (num == 6)
	    		num = 0;
	    	else
	    		num++;
	    	
	    	count++;
    	}
    }
}
