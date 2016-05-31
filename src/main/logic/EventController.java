package main.logic;
/*
@Author: Kevin Costello
*/
import java.util.Scanner;
import java.util.logging.*;

public class EventController {

	private Event myEvent;
	private static EventController myControl;
	private static EventBoundary myBoundary;
	private static final Logger logger = Logger.getLogger( EventController.class.getName() );
	
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

	    logger.log(Level.INFO, "Invalid character, try again");
	    prompt();
	}
	
	
	public void start(Event e) {
	    myEvent = e;
	    printEventInfo();
	    prompt();
	}
	
	private void clearScreen() {
	    /* Prints out 50 new line characters */
	    for (int iter = 0; iter < 50; iter++)
	    {
			logger.log(Level.INFO, "\n");
	    }
	}
	
	private void prompt() {
	    Scanner s;
	
	    s = new Scanner(System.in);
	    myBoundary.showPrompt();
	    if ("p".equals(s.next())) 
	    {
	    	printEventInfo();
	    }
	    else
	    { 
	    	rePrompt(s);
	    }
	}
	
	public void printEventInfo() {
	    clearScreen();
	    logger.log(Level.INFO, "Start time: "  +        myEvent.getStartTime().toString());
	    logger.log(Level.INFO, "End time: "  +          myEvent.getEndTime().toString());
	    logger.log(Level.INFO, "Event description: " +  myEvent.getDescrip());
	}


}
