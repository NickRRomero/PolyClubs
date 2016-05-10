/** 
 * @author Holly
 * Represents an event date
 */

public class Date
{
   private int month;
   private int day;

   public Date(int month, int day)
   {
      this.month = month;
      this.day = day;
   }

   private String toString()
   {
      return Integer.toString(month) + "/" + Integer.toString(day);
   }

   /**
    * Method to order dates
    * @return true if d1 is earlier than or equal to d2
    */
   private boolean isEarlier(Date d1, Date d2)
   {
      if (d1.month <= d2.month)
      {
         if (d1.day <= d2.day)
         {
            return true;
         }
      }
      return false;
   }

   private void setDate(int month, int day)
   {
      this.month = month;
      this.day = day;
   }

   private int getMonth()
   {
      return month;
   } 

   private int getDay()
   {
      return day;
   }
}
