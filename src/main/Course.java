/**
 * Created by KCost on 4/21/16.
 */
import java.util.ArrayList;

public class Course {
    private ArrayList<String> days;
    private String name;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    
    /**
    * n: name of the course, ex: "BIO 100"
    * startH: hour the course starts, on a 24 hour scale
    * startM: minute the course start [0, 60)
    * endH: hour the course ends
    * endM: minute the course ends
    * d: String of days abbreviations separated by "_", ex:
    * "M_T_W_R_F_Sa_Su"
    */
    public Course(String n, int startH, int startM, int endH, int endM, String d) {
        name = new String(n);
        startHour = startH;
	startMinute = startM;
        endHour = endH;
	endMinute = endM;

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
    public void setStart(int startH, int startM) {
        startHour = startH;
        startMinute = startM;
    }

    //Set the end time of the course
    public void setEnd(int endH, int endM) {
        endHour = endH;
        endMinute = endM;
    }
    
    // Set the name of the course, ex. "CPE 101"
    public void changeName(String newName) {
        name = new String(newName);
    }
   
    // Getter to return the String's name
    public String getName() {
	return name;
    }

    // Getter to return the Course's start hour
    public int getStartHour() {
        return startHour;
    }
    
    // Getter to return the Course's end hour
    public int getEndHour() {
        return endHour;
    }

    // Getter to return the Course's start minute
    public int getStartMinute() {
        return startMinute;
    }

    // Getter to return the Course's end minute
    public int getEndMinute() {
        return endMinute;
    }

    // Getter to return an array list of day abbreviations
    public String[] getDays() {
        return (String[])days.toArray();
    }

}
