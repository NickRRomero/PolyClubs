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
	private boolean addAdmin(ClubAdmin admin)
	{
		return admins.add(admin);
	}

	private boolean deleteAdmin(ClubAdmin admin)
	{
		return admins.remove(admin);
	}

	// Member request functions
	private boolean addRequest(User user)
	{
		areRequests = True;
		return userRequests.add(user);
	}

	private boolean removeRequest(User user)
	{
		boolean val = False;
		val = userRequests.remove(user);
		areRequests = !userRequests.isEmpty();

		return val;
	}

	// Club event functions
	private boolean addEvent(Event event)
	{
		return clubEvents.add(event);
	}

	private boolean removeEvent(Event event)
	{
		return clubEvents.remove(event);
	}

	private void editEvent(Event event, int sh, int sm, int eh, int em,
		String loc, String desc)
	{
		event.updateEvent(sh, sm, eh, em, loc, desc);
	}

	// Club membership functions
	private boolean addMember(User user)
	{
		return members.add(user);
	}

	private boolean removeMember(User user)
	{
		return members.remove(user);
	}
}