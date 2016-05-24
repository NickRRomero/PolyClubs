package Logic;

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

   public String toString()
   {
      return Integer.toString(hour) + ":" + Integer.toString(minute);
   }

   /**
    * Method to order times
    * @return true if 'this' is earlier than or equal to t2
    */
   public boolean isEarlier(Time t2)
   {
      if (this.hour <= t2.hour)
      {
         if (this.minute <= t2.minute)
         {
            return true;
         }
      }
      return false;
   }

   public void setTime(int hour, int minute)
   {
      this.hour = hour;
      this.minute = minute;
   }

   public int getHour()
   {
      return hour;
   }

   public int getMinute()
   {
      return minute;
   }
}
