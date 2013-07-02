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
import objects.TeamMembers;

/**
 * Servlet implementation class MemberInfo
 */
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfo() {
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
		
		//get the current session
		HttpSession session = request.getSession();
		
		//initialize the objects to be used in the method
		TeamMembers member = (TeamMembers) session.getAttribute("member");
		MemberDB memDB = new MemberDB(member);
		
		//Get the current user's username, store in session variable
		String name = request.getRemoteUser();
		session.setAttribute("currentUser", name);
		
		//if the member data has not been previously set in the session, set it

		member = new TeamMembers();
		member.setUsername(name);
			
		//initialize database object with empty TeamMember object
		memDB = new MemberDB(member);
			
		//access the db to get the member data
		try {
			member = memDB.getMemberData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		//set the member session variable
		session.setAttribute("member", member);
			
		//go to the jsp
		String url = "/teamMember.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}
}
