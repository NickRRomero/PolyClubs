import java.util.ArrayList;

/**
 * Created by jacob on 4/24/2016.
 */
public class Schedule {
    private ArrayList<Object> schedule = new ArrayList<Object>();

    // add a course to a student's schedule
    public void addCourse(Course course) {
        schedule.add(course);
    }

    // remove a course from a student's schedule
    public void removeCourse(Course course) {
        schedule.remove(course);
    }

    // determine if event conflicts with class schedule
    public void hasConflict(Event event) {

    }

    // add an event to the schedule
    public void addEvent(Event event) {
        schedule.add(event);
    }

    // remove event from schedule
    public void removeEvent(Event event) {
        schedule.remove(event)
    }
}
