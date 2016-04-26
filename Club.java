public class Club
{
	private String name;
	private String descrip;
	private ArrayList<ClubAdmin> admins
	private ArrayList<User> members
	private ArrayList<Event> clubEvents;
	private ArrayList<User> userRequests;
	private boolean areRequests;

	public Club(String nm, String des, ClubAdmin admin)
	{
		name = nm;
		descrip = des;
		admins = new ArrayList<ClubAdmin>();
		admins.add(admin);
		members = new ArrayList<User>();
		clubEvents = new ArrayList<Event>();
		userRequests = new ArrayList<User>();
		areRequests = False;
	}

	private void editDescription(String newDescrip)
	{
		descrip = newDescrip;
	}

	// Club admin functions
	private void addAdmin(ClubAdmin admin)
	{
		admins.add(admin);
	}

	private void deleteAdmin(ClubAdmin admin)
	{
		admins.remove(admin);
	}

	// Member request functions
	private void addRequest(User user)
	{
		userRequests.add(user);
		areRequests = True;
	}

	private void removeRequest(User user)
	{
		userRequests.remove(user);
		areRequests = !userRequests.isEmpty()
	}

	// Club event functions
	private void addEvent(Event event)
	{
		clubEvents.add(event);
	}

	private void removeEvent()
	{
		clubEvents.remove(event);
	}

	private void editEvent(Event event, int sh, int sm, int eh, int em,
		String loc, String desc)
	{
		event.updateEvent(sh, sm, eh, em, loc, desc);
	}
}