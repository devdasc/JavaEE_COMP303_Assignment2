<%@ page
	import="java.util.*, shoppingApp.model.*, shoppingApp.Services.*, java.text.NumberFormat"
	contentType="text/html"%>
<HTML>
<HEAD>
<TITLE>Status of Your Order</TITLE>
</HEAD>
<BODY BGCOLOR="#f2f1ef">
	<H1 ALIGN="CENTER">Your Cart</H1>
	<%
		ShoppingCartBLL cartBLL = (ShoppingCartBLL) session.getAttribute("cartBLL");
		if (cartBLL == null || cartBLL.getNumOrdered() == 0) {
			out.println("<H2><I>No items in your cart...</I></H2>");
			out.println("<a href=\"index.html\">Continue to Shop</a>");
		} else {
	%>
	<TABLE BORDER=1 ALIGN="CENTER">
		<TR BGCOLOR="#c1f287">
			<TH>Item ID
			<TH>Description
			<TH>Unit Price
			<TH>In Stock 
			<TH>Order Qty
			<TH>Total Cost<%
				Vector productsOrdered = cartBLL.getProductsOrdered();
					ProductOrder order;
					/* Rounds to two decimal places, inserts dollar
					 * sign (or other currency symbol), etc., as
					 * appropriate in current Locale.
					*/
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String formURL = "ProcessOrder";

					// Pass URLs that reference own site through encodeURL.
					formURL = response.encodeURL(formURL);
					// For each entry in shopping cart, make table row showing ID, description, per-item
					// cost, number ordered, and total cost. Put number ordered in textfield that user
					// can change, with "Update Order" button next to it, which resubmits to the OrderProcesser page
					// but specifying a different number of items.
					for (int i = 0; i < productsOrdered.size(); i++) {
						order = (ProductOrder) productsOrdered.elementAt(i);
			%>
		<TR>
			<TD><%=order.getProductSKU()%>
			<TD><%=order.getDescription()%>
			<TD><%=formatter.format(order.getUnitPrice())%>
			<TD><%=order.getNoInStock()%>
			<TD><FORM ACTION="<%=formURL%>">
					<INPUT TYPE="HIDDEN" NAME="sku" VALUE="<%=order.getProductSKU()%>">
					<INPUT TYPE="TEXT" NAME="numOfProducts" SIZE=3
						VALUE="<%=order.getNumOfProducts()%>"> <SMALL> <INPUT
						TYPE="SUBMIT" VALUE="Update Order">
					</SMALL>
				</FORM>
			<TD><%=formatter.format(order.getTotalPrice())%> <%
 	} //end for
 		String checkoutURL = response.encodeURL("ShowMessage");
 %>
	</TABLE>
	<p></p>
	<FORM ACTION="<%=checkoutURL%>">
		<CENTER>
			<INPUT TYPE="SUBMIT" VALUE="Proceed to Checkout">
		</CENTER>
	</FORM>
	<%
		} //end else
	%>

	<%
		out.println("<a href=\"index.html\">Continue to Shop</a>");
	%>
</body>
</html>
