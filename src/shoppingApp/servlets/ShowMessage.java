package shoppingApp.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ShowMessage")
public class ShowMessage extends HttpServlet {
	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
	
	
	RequestDispatcher dispatcher = null;
	dispatcher = request.getRequestDispatcher("ShowMessage.jsp");
	request.setAttribute("errorMsg", session.getAttribute("msg"));
	dispatcher.forward(request, response);	
	return;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
