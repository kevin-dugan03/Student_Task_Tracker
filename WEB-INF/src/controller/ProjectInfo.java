package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProjectDB;
import objects.Project;
import objects.Task;

/**
 * Servlet implementation class ProjectInfo
 */
public class ProjectInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectInfo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get the current session
		HttpSession session = request.getSession();
		
		//initialize the objects to be used
		Project project = (Project) session.getAttribute("project");
		ProjectDB projDB = new ProjectDB(project);
		
		//get the current user's username
		String name = request.getRemoteUser();
		session.setAttribute("currentUser", name);
		
		//if the project hasn't initialized yet, do so
		if (project == null)
		{
			//new DB object with a fresh project
			projDB = new ProjectDB(new Project());
			
			//get the project's information
			try {
				project = projDB.getProject();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//set the project session variable
			session.setAttribute("project", project);
			
			//redirect to the appropriate page
			String url = "/teamLead.jsp";
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	        dispatcher.forward(request, response);
		}
		else //the project has been previously initialized in this session
		{
			//get the project name, check to see if it's changed.
			String newName = (String) request.getParameter("newProjectName");
			
			//if it has changed, update the db
			if (newName != null && !(newName.equals(session.getAttribute("projectName"))))
			{
				//update the db
				try {
					project = projDB.updateProjectName(newName);
					session.setAttribute("project", project);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//get the due date, see if it's changed
			String newDueDate = (String) request.getParameter("newDate");
			
			//if it's changed, update it in the db
			if (newDueDate != null && !(newDueDate.equals(session.getAttribute("newDate"))))
			{
				try {
					project = projDB.updateProjectDate(newDueDate);
					session.setAttribute("project", project);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//check to see if the user wants to add a task
			String taskName = (String) request.getParameter("taskDescription");
			
			//if the task name is not empty, update the task in the db
			if (taskName == null || !taskName.isEmpty())
			{
				//make a task object, get the information from the form
				Task task = new Task();
				task.setTaskName((String) request.getParameter("taskDescription"));
				task.setAssignedName((String) request.getParameter("assignedName"));
				task.setTaskDueDate((String) request.getParameter("taskDate"));
				task.setProgress(Integer.parseInt((String) request.getParameter("progress")));

				//update the db
				try {
					project = projDB.addTask(task);
					session.setAttribute("project", project);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			//return to the main team leader page
			String url = "/teamLead.jsp";
	        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	        dispatcher.forward(request, response);
		}
	}
}
