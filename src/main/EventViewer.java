/*
  @Author: Kevin Costello
*/
import java.util.Scanner;

public class EventViewer {

   private static Event myEvent;
   private static EventViewer myView;

   private EventViewer() {

   }

   public void setEvent(Event e) {
      myEvent = e;
   }

   public static synchronized EventViewer getInstance() {
      if (myView == null) {
         myView = new EventViewer();
      }
      return myView;
   }

   private void prompt() {
      char input;
      Scanner s;

      s = new Scanner(System.in);
      showPrompt();
      switch (s.next()) {
         case "b": exitViewer();
         case "l": logout();
         case "p": printEventInfo();
         default: rePrompt(s);
      }
   }



   private void rePrompt(Scanner s) {
      s.close();
      clearScreen();
      System.out.println("Invalid character, try again");
      prompt();
   }

   private void showPrompt() {
      clearScreen();
      System.out.println("Press 'b' to go back");
      System.out.println("Press 'l' to logout");
      System.out.println("Press 'p' to print the event's info");
   }

   public void start(Event e) {
      char input;
      showEvent(e);
      prompt();
   }

   private void clearScreen() {
      /* Prints out 50 new line characters */
      System.out.printf("\n\n\n\n\n\n\n\n\n\n");
      System.out.printf("\n\n\n\n\n\n\n\n\n\n");
      System.out.printf("\n\n\n\n\n\n\n\n\n\n");
      System.out.printf("\n\n\n\n\n\n\n\n\n\n");
      System.out.printf("\n\n\n\n\n\n\n\n\n\n");
   }

   public void printEventInfo() {
      clearScreen();
      System.out.println("Start time: "  +        myEvent.getStartTime());
      System.out.println("End time: "  +          myEvent.getEndTime());
      System.out.println("Event location: "    +  myEvent.getLoc());
      System.out.println("Event description: " +  myEvent.getDescrip());
      System.out.print("Members going: ");
      for (int i = 0; i < myEvent.getGoing.size(); i++) {
         if (i % 5 == 0) {
            System.out.println();
         }
         System.out.print(myEvent.getGoing().get(i).getName());
      }
   }

   private void exitViewer() {

   }

   private void logout() {

   }

}
