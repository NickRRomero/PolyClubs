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

   public String toString()
   {
      return Integer.toString(month) + "/" + Integer.toString(day);
   }

   /**
    * Method to order dates
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

   public void setDate(int month, int day)
   {
      this.month = month;
      this.day = day;
   }

   public int getMonth()
   {
      return month;
   } 

   public int getDay()
   {
      return day;
   }
}
