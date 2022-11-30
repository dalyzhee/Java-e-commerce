<%@ page import="cn.cart.model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<title>Product</title>
<%@include file="includes/header.jsp"%>
<body>
<%@include file="includes/navbar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Add Product</div>
			<div class="card-body">
				<form action="AddProductServlet" method="post">
					<div class="form-group">
						<label>Product Name</label> <input type="text"
							class="form-control" name="product-name" placeholder="Name"
							required>
					</div>
					<div class="form-group">
						<label>Category</label> <input type="text"
							class="form-control" name="product-category" placeholder="Category"
							required>
					</div>
					<div class="form-group">
						<label>Image</label> <input type="text"
							class="form-control" name="product-image" placeholder="Category" required>
					</div>
					<div class="form-group">
						<label>Price</label> <input type="text"
							class="form-control" name="product-price" placeholder="Price"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Add Product</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>