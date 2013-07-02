package objects;


public class Task {

	private int id;
	private String taskName;
	private int assignedTo;
	private String taskDueDate;
	private int progress;
	private String assignedName;
	
	/**
	 * Constructor.
	 */
	public Task()
	{
		id = 0;
		taskName = "";
		assignedTo = 0;
		taskDueDate = null;
		int progress = 0;
		assignedName = "";
	}
	
	//Getters and Setters
	public void setId(int taskId)
	{
		id = taskId;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setTaskName(String name)
	{
		taskName = name;
	}
	
	public String getTaskName()
	{
		return taskName;
	}
	
	public void setAssignedTo(int member)
	{
		assignedTo = member;
	}
	
	public int getAssignedTo()
	{
		return assignedTo;
	}
	
	public void setTaskDueDate(String date)
	{
		taskDueDate = date;
	}
	
	public String getTaskDueDate()
	{
		return taskDueDate;
	}
	
	public void setProgress(int prog)
	{
		progress = prog;
	}
	
	public int getProgress()
	{
		return progress;
	}
	
	public void setAssignedName(String name)
	{
		assignedName = name;
	}
	
	public String getAssignedName()
	{
		return assignedName;
	}
	
}
