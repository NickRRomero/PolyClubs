package tests.test;

import org.junit.Test;

import main.logic.*;

import static org.junit.Assert.*;

/**
 * @author Holly
 * Unit tests for Date.java
 */
public class TestDate {
    @Test
    public void testGetMonth() {
        Date date = new Date(5, 30);
        assertEquals(5, date.getMonth(), 0);
    }
    
    @Test
    public void testGetDay() {
        Date date = new Date(5, 30);
        assertEquals(30, date.getDay(), 0);
    }
    
    @Test
    public void testSetDateEarlierMonth() {
        Date date = new Date(4, 21);
        date.setDate(3, 21);
        assertEquals(3, date.getMonth(), 0); 
    }
    
    @Test
    public void testSetDateEarlierDay() {
        Date date = new Date(4, 21);
        date.setDate(4, 16);
        assertEquals(16, date.getDay(), 0); 
    }
    
    @Test
    public void testToString() {
        Date date = new Date(3, 19);
        assertEquals("3/19", date.toString());
    }
    
    @Test
    public void testIsEarlierBeforeDate() {
        Date d1 = new Date(1, 1);
        Date d2 = new Date(12, 31);
        boolean result = d1.isEarlier(d2);
        assertEquals(true, result);
    }
    
    @Test
    public void testIsEarlierLaterDate() {
        Date d1 = new Date(6, 2);
        Date d2 = new Date(6, 1);
        boolean result = d1.isEarlier(d2);
        assertEquals(false, result);
    }   
}


