package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Task;
import objects.TeamMembers;

public class MemberDB {

	//class variables
	private TeamMembers member;
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet result;
	
	//Constructor
	public MemberDB(TeamMembers mem)
	{
		member = mem;
	}
	
	//returns a specific team member with associated data
	public TeamMembers getMemberData() throws SQLException
	{
		//queries
		String getMemberName = "SELECT memberName, memberID FROM APP.MemberTable WHERE username=?";
		String getTasks = "SELECT taskID, taskName, dueDate, progress FROM APP.TaskTable WHERE memberID=?";
		
		//variables for temporary storage
		String name = "";
		int memberID = 0;
		Task task = new Task();
		
		//access the db, execute the queries
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	//get the member's name and id
        	ps = conn.prepareStatement(getMemberName);
        	ps.setString(1, member.getUsername());
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		member.setName(result.getString(1));
        		member.setId(result.getInt(2));
        	}
        	
        	//get the tasks associated with the member
        	ps = conn.prepareStatement(getTasks);
        	ps.setInt(1, member.getId());
        	result = ps.executeQuery();
        	
        	while (result.next())
        	{
        		task.setId(result.getInt(1));
        		task.setTaskName(result.getString(2));
        		task.setTaskDueDate(result.getString(3));
        		task.setProgress(result.getInt(4));
        		
        		member.addTask(task);
        		task = new Task();
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
        
        //return the member after the queries
        return member;
		
	}
	
	public void updateTask(int number, int newProgress) throws SQLException
	{
		String update = "UPDATE APP.TaskTable SET progress=? WHERE taskID=?";
		
		//update with the new progress numbers
		try
		{
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/exam3");
        	conn = data.getConnection();
        	
        	ps = conn.prepareStatement(update);
        	ps.setInt(1, newProgress);
        	ps.setInt(2, number);
        	ps.executeUpdate();
		}
		catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }
	}
}
