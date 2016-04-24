/**
 * Created by KCost on 4/21/16.
 */
import java.util.Date;
import java.util.ArrayList;

public class Course {
    private ArrayList<String> days;
    private String name;
    private Time start;
    private Time end;


    public Course(String name, int startH, int startM, int endH, int endM) {
        days = new ArrayList<String>();
        name = String(n); //Creates a copy of name, worried about shallow vs. deep??
        start = new Time(startH, startM, 0);
        end = new Time(endH, endM, 0);
    }


    public void setStart(int startH, int startM) {
        startTime.setHours(startH);
        startTime.setMinutes(startM);
    }

   public void setEnd(int endH, int endM) {
        endTime.setHours(endH);
        endTime.setMinutes(endM);
    }

    public void changeName(String newName) {
        name = String(newName);

    }



}
