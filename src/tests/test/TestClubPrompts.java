package tests.test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.logic.ClubAdmin;
import main.logic.ClubPrompts;
import main.logic.User;

/**
 * Integration Test of ClubPrompts and User/Admin
 * @author Nick Romero
 *
 */
public class TestClubPrompts 
{

	@Test
	public void TestSetUser() 
	{
		User userNick = new User("Nick", "92581837123", "123456", "nrromero@calpoly.edu");
		ClubPrompts clubPrompts = new ClubPrompts();
		clubPrompts.setUser(userNick);
		assertEquals(userNick, clubPrompts.getUser());
	}
	
	@Test
	public void TestIsAdmin()
	{
		ClubAdmin clubAdmin = new ClubAdmin("Nick", "9258183713", "123456", "nrromero@calpoly.edu");
		ClubPrompts clubPrompts = new ClubPrompts();
		clubPrompts.setAdmin(clubAdmin instanceof ClubAdmin);
		assertTrue(clubPrompts.getIsAdmin());
	}
	
	@Test
	public void TestIsNotAdmin()
	{
		User user = new User("Nick", "9258183713", "123456", "nrromero@calpoly.edu");
		ClubPrompts clubPrompts = new ClubPrompts();
		clubPrompts.setAdmin(user instanceof ClubAdmin);
		assertFalse(clubPrompts.getIsAdmin());
	}

}
