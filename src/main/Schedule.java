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
    	// variables to be set later
    	Course c = null;
    	Event e = null;
    	Time stime = null;
    	Time etime = null;
    	boolean sameDay = false;
    	
    	// loop through each event/course in schedule
    	outerloop: 
    	for (Object ec : schedule) {
    		System.out.println("Event");
    		// if course, determine if event and course are on same day
    		if (ec instanceof Course) {
    			c = (Course) ec;
    			for(String day : c.getDays()) {
    				// if on same day, get time and exit outerloop
    				if (event.getDay().equals(day)) {
    					sameDay = true;
    					stime = c.getStart();
    					etime = c.getEnd();
    					break outerloop;
    				}
    			}
    		}
    		// if event, determine if events are on same date
    		else {
    			e = (Event) ec;
    			// if on same date, get time and exit outerloop
	    		if (event.getDate().equals(e.getDate())) {
					sameDay = true;
					stime = e.getStartTime();
					etime = e.getEndTime();
					break outerloop;
	    		}
    		}	
    	}
    	
    	if (sameDay) 
    		System.out.println("Event: " + stime + " " + etime);
    	
    	return true;
    }
    
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
    
    static public void main(String[] args) {
    	Date pd = new Date(5, 15);
    	Time pst = new Time(13, 00);
    	Time pet = new Time(15, 30);
    	
    	Date nd = new Date(5, 13);
    	Time nst = new Time(14, 10);
    	Time net = new Time(15, 05);
    	
    	Date pid = new Date(5, 14);
    	Time pist = new Time(12, 00);
    	Time piet = new Time(14, 25);
    	
    	Time sst = new Time(11, 10);
    	Time set = new Time(13, 00);
    	
    	Event party = new Event("W", pid, pst, pet, "Birthday Party");
    	Event nap = new Event("M", nd, nst, net, "Take a nap");
    	Event eat = new Event("Sa", pid, pist, piet, "pizza time");
    	Course math = new Course("math", nst, net, "M_T_W_R");
    	Course science = new Course("science", sst, set, "M_W_F");
    	
    	Schedule sch = new Schedule();
    	
    	sch.add(math);
    	sch.add(nap);
    	sch.add(science);
    	sch.add(party);
    	System.out.println(sch.schedule.size() + "\n");
    	System.out.println(nap.getEndTime().toString());

    	sch.hasConflict(eat);
    	
    }
}

