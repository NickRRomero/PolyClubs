package Test;

/**
 * Tests for ClubAdmin class.
 * Tests without comments have not been started.
 * @author mboyken
 */

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;

import Logic.Club;
import Logic.ClubAdmin;
import Logic.Date;
import Logic.Event;
import Logic.Time;
import Logic.User;

public class ClubAdminTest
{
   private ClubAdmin admin;
   private User member;
   private Event event1;
   private Event event2;
   private Club club;

   /**
    * Constructor for ClubAdminTest. Initializes a ClubAdmin, User, Club, and
    * Event.
    * 
    * @throws Exception
    */
   public ClubAdminTest() throws Exception
   {
      Time startTime = new Time(8, 0);
      Time endTime = new Time(23, 59);
      Date date = new Date(4, 19);

      admin = new ClubAdmin("Marii", "(270) 314-6742", "008602954",
            "mboyken@calpoly.edu");
      member = new User("Maeve", "123456789", "123456789", "maeve@calpoly.edu");
      club = new Club("SWE", admin.getEmail(), "Society of Women Engineers");
      event1 = new Event("Tuesday", date, startTime, endTime,
            "B-day Party. Garfield Arms. Marii's birthday party.");
      club.addEvent(event1);
   }

   /**
    * Test deleteEvent of ClubAdmin.
    * 
    * @throws JSONException
    */
   @Test
   public void removeEventTest() throws JSONException
   {
      Club club = Mockito.mock(Club.class);
      ArrayList<Event> eventList;

      club.addEvent(event1);
      eventList = club.getEvents();
      admin.removeEvent(event1, club);
      assertFalse(eventList.contains(event1));
   }
}
