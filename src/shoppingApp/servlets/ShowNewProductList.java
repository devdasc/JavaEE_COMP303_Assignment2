package shoppingApp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingApp.Services.ShoppingCartBLL;
import shoppingApp.model.Product;

@WebServlet("/ShowNewProductList")
public class ShowNewProductList extends HttpServlet {
	private ShoppingCartBLL cartBLL;
	HttpSession session;

	// Initialize global variables
	public void init() throws ServletException {
			
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession(true);
			Vector productList = new Vector();;
			if(session.getAttribute("ProductList")!=null)
			{
				productList=(Vector) session.getAttribute("ProductList");
			}
			else
			{					
				cartBLL=new ShoppingCartBLL();					
				productList=cartBLL.productList;
				session.setAttribute("ProductList", productList);
				session.setAttribute("cartBLL", cartBLL);
			}
			
			RequestDispatcher dispatcher = null;
		
			dispatcher = request.getRequestDispatcher("ShowProductList.jsp");
			request.setAttribute("Title", "All-Time Best Selling Books");
			request.setAttribute("ProductList", productList);
			dispatcher.forward(request, response);		
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
}
