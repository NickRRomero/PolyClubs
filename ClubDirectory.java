/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyclubsconsole;

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
