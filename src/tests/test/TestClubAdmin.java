package tests.test;

/**
 * Tests for ClubAdmin class.
 * @author mboyken
 */

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;

import main.logic.*;

public class TestClubAdmin
{
   private ClubAdmin admin;
   private Event event1;
   private Club club;

   /**
    * Constructor for ClubAdminTest. Initializes a ClubAdmin, User, Club, and
    * Event.
    * 
    * @throws Exception
    */
   public TestClubAdmin() throws Exception
   {
      Time startTime = new Time(8, 0);
      Time endTime = new Time(23, 59);
      Date date = new Date(4, 19);

      admin = new ClubAdmin("Marii", "(270) 314-6742", "008602954",
            "mboyken@calpoly.edu");
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
   public void testRemoveEvent() throws JSONException
   {
      Club club = Mockito.mock(Club.class);
      List<Event> eventList;

      club.addEvent(event1);
      eventList = club.getEvents();
      admin.removeEvent(event1, club);
      assertFalse(eventList.contains(event1));
   }
}
