package main.logic;

/**
 * Created by KCost on 4/21/16.
 */
import java.util.ArrayList;

public class Course {
    private ArrayList<String> days;
    private String name;
    private Time start;
    private Time end;
    
    /**
    * n: name of the course, ex: "BIO 100"
    * startH: hour the course starts, on a 24 hour scale
    * startM: minute the course start [0, 60)
    * endH: hour the course ends
    * endM: minute the course ends
    * d: String of days abbreviations separated by "_", ex:
    * "M_T_W_R_F_Sa_Su"
    */
    public Course(String n, Time s, Time e, String d) {
       name = new String(n);
       start = s;
       end = e;

       // d contains String abbreviations separated by '_'
       days = new ArrayList<String>();
	
       // Each elements in parts represents a day appreviation
       String[] parts = d.split("_");
	
       // add each day abbreviation the course occurs on to the days
       // ArrayList
       for (int i = 0; i < parts.length; i ++) {
           days.add(parts[i]);
       }
    }

    // Set the start time of the course
    public void setStartTime(Time t) {
        start = new Time(t.getHour(), t.getMinute());
    }

     public void setEndTime(Time t) {
        end = new Time(t.getHour(), t.getMinute());
    }

    // Set the name of the course, ex. "CPE 101"
    public void changeName(String newName) {
        name = new String(newName);
    }
   
    // Getter to return the String's name
    public String getName() {
        return new String(name);
    }

    // Getter to return the Course's start hour
    public Time getStart() {
        return start;
    }
    
    // Getter to return the Course's end hour
    public Time getEnd() {
        return end;
    }


    // Getter to return an array list of day abbreviations
    public String[] getDays() {
        return days.toArray(new String[0]);
    }

}
