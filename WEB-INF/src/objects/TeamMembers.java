package objects;

import java.util.ArrayList;

public class TeamMembers {
	
	private String name;
	private String position;
	private ArrayList<Task> memberTasks;
	private String username;
	private int id;
	
	/**
	 * Constructor.
	 */
	public TeamMembers()
	{
		name = "";
		position = "";
		memberTasks = new ArrayList<Task>();
		username = "";
		id = 0;
	}
	
	//Getters and Setters
	
	public void setName(String memberName)
	{
		name = memberName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setPosition(String pos)
	{
		position = pos;
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public void addTask(Task task)
	{
		memberTasks.add(task);
	}
	
	public ArrayList<Task> getMemberTasks()
	{
		return memberTasks;
	}
	
	public void setUsername(String name)
	{
		username = name;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setId(int memID)
	{
		id = memID;
	}
	
	public int getId()
	{
		return id;
	}

}
