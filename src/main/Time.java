/** 
 * @author Holly
 * Represents an event time
 */

public class Time
{
   private int hour;
   private int minute;

   public Time(int hour, int minute)
   {
      this.hour = hour;
      this.minute = minute;
   }

   private String toString()
   {
      return Integer.toString(hour) + ":" + Integer.toString(minute);
   }

   /**
    * Method to order times
    * @return true if t1 is earlier than or equal to t2
    */
   private boolean isEarlier(Time t1, Time t2)
   {
      if (t1.hour <= t2.hour)
      {
         if (t1.minute <= t2.minute)
         {
            return true;
         }
      }
      return false;
   }

   private void setTime(int hour, int minute)
   {
      this.hour = hour;
      this.minute = minute;
   }

   private void getHour()
   {
      return hour;
   }

   private void getMinute()
   {
      return minute;
   }
}
