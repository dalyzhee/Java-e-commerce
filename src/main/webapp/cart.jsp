<%@ page import="cn.cart.model.*" %>
<%@ page import="cn.cart.dao.*" %>
<%@page import="cn.cart.connection.DbCon"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if(cart_list != null){
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	cartProduct = pDao.getCartProducts(cart_list);
	double total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<title>Cart Page</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td{
	vertical-align: middle;
}
.btn-incre, .btn-decre{
	box-shadow: none;
	font-size: 25px;
}
</style>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: $ ${(total>0)?dcf.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="CheckOutServlet">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<% if(cart_list != null){
				for(Cart c:cartProduct){%>
					<tr>
					<td><%= c.getName() %></td>
					<td><%= c.getCategory() %></td>
					<td><%= dcf.format(c.getPrice())%>$</td>
					<td>
						<form action="OrderNowServlet" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%= c.getQuantity() %>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre" href="QuantityIncrDecrServlet?action=dec&id=<%= c.getId() %>"><i
									class="fa fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50" value="<%= c.getQuantity() %>" readonly>
								<a class="btn btn-sm btn-incre" href="QuantityIncrDecrServlet?action=inc&id=<%= c.getId() %>"><i
									class="fa fa-plus-square"></i></a>

							</div>
							<button type="submit" class="btn btn-primarybtn-sm">Buy</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="RemoveServlet?id=<%= c.getId() %>">Remove</a></td>
				</tr>
				<%}
			}
				
			%>
			</tbody>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>