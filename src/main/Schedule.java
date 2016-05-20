package Logic;

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
    	
    	// sameDay is true if event is on same day as another course/event
    	boolean sameDay = false;
    	
    	// 0 if sameDay is false, 1 if event is on same day as another event
    	// -1 if event is on same day as a course
    	int course_or_event = 0;
    	
    	// loop through each event/course in schedule
    	outerloop: 
    	for (Object obj : schedule) {
    		// if obj is course, determine if event and course are on same day
    		if (obj instanceof Course) {
    			c = (Course) obj;
    			for(String day : c.getDays()) {
    				// if on same day, set variables and exit outerloop
    				if (event.getDay().equals(day)) {
    					sameDay = true;
    					course_or_event = -1;
    					break outerloop;
    				}
    			}
    		}
    		// if obj is event, determine if events are on same date
    		else {
    			e = (Event) obj;
    			// if on same date, set variables and exit outerloop
	    		if (event.getDate().equals(e.getDate())) {
					sameDay = true;
					course_or_event = 1;
					break outerloop;
	    		}
    		}	
    	}
    	
    	if (course_or_event > 0 && sameDay) 
    		return timeCheck(e.getStartTime(), e.getEndTime(), 
    				event.getStartTime(), event.getEndTime());
    	else if (course_or_event < 0 && sameDay)
    		return timeCheck(c.getStart(), c.getEnd(), 
    				event.getStartTime(), event.getEndTime());    	
    		
    	return false;
    }
    
    // check if two events/courses have conflicting times
    public boolean timeCheck(Time start1, Time end1, Time start2, Time end2) {
    	// the first event ends before the second event starts
    	if (end1.isEarlier(start2))
    		return false;
    	
    	// the first event starts after the second event ends
    	if (!start1.isEarlier(end2))
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
    
    public void displayWeek() {
    	;
    }
}

