<%@ page import="java.util.*, shoppingApp.model.*"
	contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${Title}</title>
</head>
<body BGCOLOR="#f2f1ef">
	<H1 ALIGN="CENTER">${Title}</H1>
	<HR>
	<%
		Vector v = (Vector) request.getAttribute("ProductList");
		Product product = null;
		for (int i = 0; v != null && i < v.size(); i++) {
			product = (Product) v.elementAt(i);
	%>
	<FORM ACTION="ProcessOrder">
	<div style="background-color: lightgrey;width: 800px;border: 25px solid green;padding: 25px;margin: 25px;">
		<INPUT TYPE="HIDDEN" NAME="sku" VALUE="<%=product.getSKU()%>">
		<H2><%=product.getDescription()%>
			($<%=product.getUnitPrice()%>) (Available:
			<%=product.getNoInStock()%>)
		</H2>
		<%=product.getLongDescription()%>
		<P>
		<CENTER>
			<INPUT TYPE="SUBMIT" VALUE="Add to Shopping Cart">
		</CENTER>
		<P>
		</div>
	</FORM>
	
	<%
		}
	%>
	<%
		out.println("<a href=\"index.html\">Home Page</a>");
	%>

</body>
</html>