// Represents a club event
// Event start and end times based on a 24-hour clock

public class Event
{
	private int startHour;
	private int startMinute;
	private int endHour;
	private int endMinute;
	private String location; // location of the event
	private String description; // description of the event
	private ArrayList<User> going; // users going to the event

	public Event(int sh, int sm, int eh, int em, String loc, String descrip)
	{
		startHour = sh;
		startMinute = sm;
		endHour = eh;
		endMinute = em;
		location = loc;
		description = descrip;
		going = new ArrayList<User>();
	}

	private void addMember(User user)
	{
		going.add(user);
	}

	private void deleteMember(User user)
	{
		going.remove(user);
	}

	private ArrayList<User> getGoing()
	{
		return going;
	}

	private void updateEvent(int sh, int sm, int eh, int em, String loc, 
		String descrip)
	{
		startHour = sh;
		startMinute = sm;
		endHour = eh;
		endMinute = em;
		location = loc;
		description = descrip;
	}
}