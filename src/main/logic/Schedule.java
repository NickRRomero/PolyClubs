package main.logic;

import java.util.ArrayList;

/**
 * Created by jacob on 4/24/2016.
 */
public class Schedule {
    private ArrayList<Object> schedule;
    
    public Schedule() {
    	schedule = new ArrayList<Object>();
    }

    // add course or event to schedule
    public void add(Object obj) {
    	schedule.add(obj);
    }
    
    // remove course or event from schedule
    public void remove(Event event) {
        schedule.remove(event);
    }
    
    // clear schedule
    public void clear() {
    	schedule.clear();
    }
    
    // determine if an event conflicts with a user's schedule
    public boolean hasConflict(Event event) {
    	// course and event to be set in loop
    	Course c = null;
    	Event e = null;
    	
    	// is there a conflict
    	boolean conflict = false;
    	
    	// loop through each event/course in schedule
    	for (Object obj : schedule) {
    		// if obj is course, determine if event and course are on same day
    		if (obj instanceof Course) {
    			c = (Course) obj;
    			for(String day : c.getDays()) {
    				// if on same day, check if times are in conflict
    				if (event.getDay().equals(day)) {
    					conflict = timeCheck(c.getStart(), c.getEnd(), 
    		    				event.getStartTime(), event.getEndTime());
    					
    					System.out.println(c.getName());
    				}
    			}
    		}
    		// if obj is event, determine if events are on same date
    		else {
    			e = (Event) obj;
    			// if on same date, set variables and exit outerloop
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
    
    // check if two events/courses have conflicting times
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
    
    // function that returns all the courses in a schedule
    public ArrayList<Course> getCourses() {
    	ArrayList<Course> courses = new ArrayList<Course>();
    	
    	for (Object obj : schedule) {
    		if (obj instanceof Course) 
    			courses.add((Course) obj);
    	}
    	
    	return courses;
    }
    
    // display weekly calendar of courses
    public void displayWeek() {
    	;
    }
}

