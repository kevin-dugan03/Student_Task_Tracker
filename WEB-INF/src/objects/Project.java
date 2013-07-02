package objects;

import java.util.ArrayList;

/**
 * Class that represents a project. Holds the project name,
 * due date, list of team members, and list of tasks.
 * @author K Dugan
 */
public class Project {
	
	private String projectName;
	private String projectDueDate;
	private ArrayList<TeamMembers> team;
	private ArrayList<Task> tasks;
	
	/**
	 * Constructor
	 */
	public Project()
	{
		projectName = "";
		projectDueDate = null;
		team = new ArrayList<TeamMembers>();
		tasks = new ArrayList<Task>();
	}
	
	//Getters and Setters
	
	public void setProjectName(String name)
	{
		projectName = name;
	}
	
	public String getProjectName()
	{
		return projectName;
	}
	
	public void setProjectDueDate(String date)
	{
		projectDueDate = date;
	}
	
	public String getProjectDueDate()
	{
		return projectDueDate;
	}
	
	public void addMember(TeamMembers member)
	{
		team.add(member);
	}
	
	public ArrayList<TeamMembers> getTeam()
	{
		return team;
	}
	
	public void addTask(Task task)
	{
		tasks.add(task);
	}
	
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	

}
