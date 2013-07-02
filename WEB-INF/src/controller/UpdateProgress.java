package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDB;
import objects.Task;
import objects.TeamMembers;

/**
 * Servlet implementation class UpdateProgress
 */
public class UpdateProgress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProgress() {
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
		HttpSession session = request.getSession();
		TeamMembers member = (TeamMembers) session.getAttribute("member");
		MemberDB memDB = new MemberDB(member);
		
		//get the taskID and the new progress numbers
		int taskNum = Integer.parseInt((String) request.getParameter("taskNum"));
		int progress = Integer.parseInt((String) request.getParameter("progress"));
		
		//update the task
		try {
			memDB.updateTask(taskNum, progress);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//update the bean (should've just done it in the DAO)
		for (Task tasking : member.getMemberTasks())
		{
			if (tasking.getId() == taskNum)
			{
				tasking.setProgress(progress);
				session.setAttribute("member", member);
			}
		}
		
		//return to the jsp
		String url = "/teamMember.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
	}

}
