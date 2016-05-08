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

    public Course(String n, int startH, int startM, int endH, int endM) {
        days = new ArrayList<>();
        name = new String(n);
        startHour = startH;
	startMinute = startM;
        endHour = endH;
	endMinute = endM;
    }

    public void setStart(int startH, int startM) {
        startHour = startH;
        startMinute = startM;
    }

    public void setEnd(int endH, int endM) {
        endHour = endH;
        endMinute = endM;
    }
 
    public void changeName(String newName) {
        name = new String(newName);
    }
}