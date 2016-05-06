/**
 * Tests for ClubAdmin class.
 */

import java.org.junit.*;

public class ClubAdminTest
{
   private ClubAdmin admin;

   public ClubAdminTest()
   {
      admin = new ClubAdmin(0, "Marii", "(270) 314-6742",
         "008602954", "mboyken@calpoly.edu");
   }

   public void createEventTest()
   {
      
   }

   public void createClubTest()
   {
      
   }

   public void addAdminTest()
   {

   }

   public void deleteEventTest()
   {

   }

   public void updateEventTimeTest()
   {

   }

   public void updateEventLocTest()
   {

   }

   public void updateEventDescrTest()
   {

   }

   public void acceptMemberTest()
   {
      boolean result = admin.acceptMember(this, club, false);
      assertFalse(result);

      result = admin.acceptMember(this, club, true);
      assertTrue(result);
   }

   public void getAdminIDTest()
   {
      assertEquals(0, admin.getAdminID());
   }
}
