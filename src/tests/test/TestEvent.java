package tests.test;

import org.junit.Test;

import main.logic.*;

import static org.junit.Assert.*;

/**
 * @author Holly
 * Unit tests for Event.java
 * Includes 2 integration tests
 */
public class TestEvent {
    @Test
    public void testGetDate() {
        Date d = new Date(3, 19);
        Time t = new Time(5, 00);
        Event e = new Event("M", d, t, t, "Finishing our 309 project!");
        boolean datesEqual = e.getDate().isEqual(d);
        assertEquals(true, datesEqual);
    }
    
    @Test
    public void testGetTime() {
        Date d = new Date(3, 19);
        Time t = new Time(5, 00);
        Event e = new Event("M", d, t, t, "Finishing our 309 project!");
        Time startT = e.getStartTime();
        assertEquals(5, startT.getHour(), 0);
    }
    
    @Test
    public void testGetDay() {
        Date d = new Date(3, 19);
        Time t = new Time(5, 00);
        Event e = new Event("M", d, t, t, "Finishing our 309 project!");
        String eventDay = e.getDay();
        assertEquals("M", eventDay);
    }
}

