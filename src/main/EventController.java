


	/* Interfaces with boundaries and entities
	 * They orchestrate the execution of commands coming from the boundary. 
	 * They implement the logic of a UC.
	 */
package Logic;
/*
@Author: Kevin Costello
*/
import java.util.Scanner;
import Logic.Event;

public class EventController {

	private static Event myEvent;
	private static EventController myControl;
	private static EventBoundary myBoundary;
	
	private EventController() {    }
	
	public void setEvent(Event e) {
		myEvent = e;
	}
	
	public static synchronized EventController getInstance() {
	    // Singleton
	    if (myControl == null) {
	       myControl = new EventController();
	       myBoundary = new EventBoundary();
	    }
	    return myControl;
	}
	
	
	//Scanner from method prompt()
	private void rePrompt(Scanner s) {
	    s.close();
	    clearScreen();

	    System.out.println("Invalid character, try again");
	    prompt();
	}
	
	
	public void start(Event e) {
	    myEvent = e;
	    printEventInfo();
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
	
	private void prompt() {
	    Scanner s;
	
	    s = new Scanner(System.in);
	    myBoundary.showPrompt();
	    switch (s.next()) {
	       case "p": printEventInfo();
	       default: rePrompt(s);
	    }
	}
	
	public void printEventInfo() {
	    clearScreen();
	    System.out.println("Start time: "  +        myEvent.getStartTime().toString());
	    System.out.println("End time: "  +          myEvent.getEndTime().toString());
	    System.out.println("Event description: " +  myEvent.getDescrip());
	    System.out.print("Members going: ");
	    for (int i = 0; i < myEvent.getGoing().size(); i++) {
	       if (i % 5 == 0) {
	          System.out.println();
	       }
	       System.out.print(myEvent.getGoing().get(i).getName());
	    }
	}


}
