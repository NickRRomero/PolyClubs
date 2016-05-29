package main.Logic;

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
   
   // compare two dates
   public boolean isEqual(Date d2) {
	   if (this.month == d2.month && this.day == d2.day)
		   return true;
	   
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
      return day;
   }

   @Override
   public String toString()
   {
      return Integer.toString(month) + "/" + Integer.toString(day);
   }
}
