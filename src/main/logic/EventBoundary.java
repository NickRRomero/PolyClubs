package main.logic;

import java.util.logging.*;

public class EventBoundary {
	// Interface with system actors: user interfaces
	// Visualization of the Event's information
	private static final Logger logger = Logger.getLogger( EventBoundary.class.getName() );
	
	public void showPrompt() {
	    logger.log(Level.INFO, "Press 'p' to print the event's info");
	}

	
}
