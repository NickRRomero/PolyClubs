package Logic;

import java.util.Scanner;

/**
 *
 * @author jacob
 */
public class ClubSearch {

   static void search(String club){
      
   }
   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("What would you like to do?");
      
      if("Search".equals(scanner.next())) {
         System.out.print("*");
      }
   }
}
