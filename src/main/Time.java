
ckage Logic;

/** 
 * @author Holly
 * Represents an event time
 */

public class Time {
   private int hour;
   private int minute;

   public Time(int hour, int minute)
   {
      this.hour = hour;
      this.minute = minute;
   }

   /**
    * Method to order times
    * @param t2 - Time to compare to 'this'
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

   // Time setters
   public void setTime(int hour, int minute)
   {
      this.hour = hour;
      this.minute = minute;
   }

   // Time getters
   public int getHour()
   {
      return hour;
   }

   public int getMinute()
   {
      return minute;
   }
   
   @Override
   public String toString()
   {
      return String.format("%02d", hour) + ":" + String.format("%02d", minute);
   }
}

/ckage Logic;

/** 
 * @author Holly
 * Represents an event date
 */

public class Date {
	private int month;
	private int day;

	public Date(int month, int day)
	{
		this.month = month;
      this.day = day;
    }

   /**
    * Method to order dates
    * @param d2 - Date to compare to 'this'
    * @return true if 'this' is earlier than or equal to d2
    */
   public boolean isEarlier(Date d2)
   {
      if (this.month <= d2.month)
      {
         if (this.day <= d2.day)
         {
            return true;
         }
      }
      return false;
   }

   // Date setters
   public void setDate(int month, int day)
   {
      this.month = month;
      this.day = day;
   }

   // Date getters
   public int getMonth()
   {
      return month;
   } 

   public int getDay()
   {
