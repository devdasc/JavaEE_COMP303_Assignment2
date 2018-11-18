package shoppingApp.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import shoppingApp.Services.*;
import shoppingApp.model.*;

@WebServlet("/ProcessOrder")
public class ProcessOrder extends HttpServlet {
	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ShoppingCartDAO cartDAO;
		ShoppingCartBLL cartBLL;
		
		String errorMsg;
		synchronized (session) {
			cartBLL = (ShoppingCartBLL) session.getAttribute("cartBLL");
			// New visitors get a fresh shopping cart.
			// Previous visitors keep using their existing cart.
			if (cartBLL == null) {
				cartBLL = new ShoppingCartBLL();
				session.setAttribute("cartBLL", cartBLL);
			}
			String sku = request.getParameter("sku");
			if (sku != null) {
				
				String numOfProductString = request.getParameter("numOfProducts");
				
				Product newProduct = ProductList.getProduct(sku);
								
				if (numOfProductString == null && newProduct.getNoInStock()>0) {
					// If request specified an ID but no number,
					// then customers came here via an "Add Item to Cart"
					// button on a catalog page.
					cartBLL.addItem(sku);
				} 
				else if(numOfProductString == null && newProduct.getNoInStock()==0)
				{
					RequestDispatcher dispatcher = null;
					dispatcher = request.getRequestDispatcher("ShowMessage.jsp");
					request.setAttribute("errorMsg", "Product not available in stock");
					dispatcher.forward(request, response);	
					return;
				}
				else {
					// If request specified an ID and number, then
					// customers came here via an "Update Order" button
					// after changing the number of items in order.
					// Note that specifying a number of 0 results
					// in item being deleted from cart.
					int updatedNumOfProducts;
					try {
						updatedNumOfProducts = Integer.parseInt(numOfProductString);
						
							String msg = cartBLL.setNumOrdered(sku, updatedNumOfProducts);
						
							if(msg!=null)
							{
								//session.setAttribute("msg", msg);
								//response.sendRedirect("ShowMessage");
								
								RequestDispatcher dispatcher = null;
								dispatcher = request.getRequestDispatcher("ShowMessage.jsp");
								request.setAttribute("errorMsg", msg);
								dispatcher.forward(request, response);	
								return;
							}
						
					} catch (NumberFormatException nfe) {
						RequestDispatcher dispatcher = null;
						dispatcher = request.getRequestDispatcher("ShowMessage.jsp");
						request.setAttribute("errorMsg", nfe.getMessage());
						dispatcher.forward(request, response);	
						return;
					}					
				}
			}
		} // end synchronized
		RequestDispatcher dispatcher = request.getRequestDispatcher(response.encodeURL("ShowOrder.jsp"));
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