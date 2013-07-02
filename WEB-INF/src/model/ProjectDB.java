package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Project;
import objects.Task;
import objects.TeamMembers;

public class ProjectDB {
	
	//class variables
	private Project project;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet result;
	
	//constructor
	public ProjectDB(Project projectParam)
	{
		project = projectParam;
	}
	
	//return the project from the db
	public Project getProject() throws SQLException
	{
		//queries
		String projectNameAndDate = "SELECT * FROM APP.ProjectTable";
		String memberList = "SELECT memberName, position FROM APP.MemberTable";
		String taskList = "SELECT * FROM APP.TaskTable";
		String assignments = "SELECT memberName FROM APP.MemberTable WHERE memberID=?";
		String insertNameAndDate = "INSERT INTO APP.ProjectTable VALUES (?, ?)";
		
		//objects to store result data
		TeamMembers member = new TeamMembers();
		Task task = new Task();
		
		//access the db
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	//get the project name and date
        	ps = conn.prepareStatement(projectNameAndDate);
        	result = ps.executeQuery();

        	while (result.next())
            {
            	project.setProjectName(result.getString(1));
            	project.setProjectDueDate(result.getString(2));
            }
        	
        	//if the project name hasn't been set, set name and date with generalized data
        	if (project.getProjectName() == null)
        	{
        		ps = conn.prepareStatement(insertNameAndDate);
        		ps.setString(1, "Has not been set");
        		ps.setString(2, "Has not been set");
        		ps.executeQuery();
        		
        		project.setProjectName("Has not been set");
        		project.setProjectDueDate("Has not been set");
        	}
        	      	
        	//retrieve the member list from the db
        	ps = conn.prepareStatement(memberList);
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		member.setName(result.getString(1));
        		member.setPosition(result.getString(2));
        		project.addMember(member);
        		member = new TeamMembers();
        	}
        	
        	//get the complete task list for the project
        	ps = conn.prepareStatement(taskList);
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		task.setId(result.getInt(1));
        		task.setTaskName(result.getString(2));
        		task.setProgress(result.getInt(3));
        		task.setTaskDueDate(result.getString(4));
        		task.setAssignedTo(result.getInt(5));
        		project.addTask(task);
        		task = new Task();
        	}
        	
        	//insert the names of each member into the Task objects for display purposes
        	for (Task tasking: project.getTasks())
        	{
        		ps = conn.prepareStatement(assignments);
        		ps.setInt(1, tasking.getAssignedTo());
        		result = ps.executeQuery();
        		
        		while (result.next())
        		{
        			tasking.setAssignedName(result.getString(1));
        		}
        	}
        	
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        
        return project;
	}
	
	//add a task to the project
	public Project addTask(Task tasking) throws SQLException
	{
		//queries
		String getMemberName = "SELECT memberID FROM APP.MemberTable WHERE memberName=?";
		String insertTask = "INSERT INTO APP.TaskTable (taskName, progress, dueDate, memberID) VALUES (?, ?, ?, ?) ";
		String getID = "SELECT taskID FROM APP.TaskTable WHERE taskName=?";
		
		//variables to hold data
		int nameID = 0;
		Task task = tasking;
		
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	//retrieve the member id from the member table
        	ps = conn.prepareStatement(getMemberName);
        	ps.setString(1, tasking.getAssignedName());
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		nameID = result.getInt(1);
        	}
        	
        	//insert a task into the db
        	ps = conn.prepareStatement(insertTask);
        	ps.setString(1, task.getTaskName());
        	ps.setInt(2, task.getProgress());
        	ps.setString(3, task.getTaskDueDate());
        	ps.setInt(4, nameID);
        	ps.executeUpdate();

        	//get the task ID so that all the task data can be added to the project object's task list
        	ps = conn.prepareStatement(getID);
        	ps.setString(1, task.getTaskName());
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		task.setId(result.getInt(1));
        	}
        	
        	//add the task to the project's task list
        	project.addTask(task);
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        
        return project;
	}
	
	//update the project's name
	public Project updateProjectName(String newName) throws SQLException
	{
		//queries
		String getCurrent = "SELECT projectName FROM APP.ProjectTable";
		String updateName = "UPDATE APP.ProjectTable SET projectName=? WHERE projectName=?";
		
		//method variable
		String oldName = "";
		
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	//get the current name of the project from the db
        	ps = conn.prepareStatement(getCurrent);
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		oldName = result.getString(1);
        	}
        	
        	//update the name to the new name
        	ps = conn.prepareStatement(updateName);
        	ps.setString(1, newName);
        	ps.setString(2, oldName);
        	ps.executeUpdate();
        	
        	project.setProjectName(newName);
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        
        return project;
	}
	
	public Project updateProjectDate(String newDate) throws SQLException
	{
		//queries
		String getCurrent = "SELECT projectDueDate FROM APP.ProjectTable";
		String updateDate = "UPDATE APP.ProjectTable SET projectDueDate=? WHERE projectDueDate=?";
		
		//method variable
		String oldDate = "";
		
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	//get the current date
        	ps = conn.prepareStatement(getCurrent);
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		oldDate = result.getString(1);
        	}
        	
        	//insert the new date where the old date was
        	ps = conn.prepareStatement(updateDate);
        	ps.setString(1, newDate);
        	ps.setString(2, oldDate);
        	ps.executeUpdate();
        	
        	project.setProjectDueDate(newDate);
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
        
        return project;
	}
}
