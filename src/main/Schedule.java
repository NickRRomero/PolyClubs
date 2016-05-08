/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyclubsconsole;

import java.util.ArrayList;

/**
 *
 * @author jacob
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
       schedule.remove(event);
   }
}

