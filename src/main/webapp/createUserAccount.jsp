<%@ page import="cn.cart.model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	response.sendRedirect("index.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<title>Login Page</title>
<%@include file="includes/header.jsp"%>
<body>
<%@include file="includes/navbar.jsp" %>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Create Account.</div>
			<div class="card-body">
				<form action="CreateAccountServlet" method="post">
					<div class="form-group">
						<label>Name</label> <input type="text"
							class="form-control" name="create-name" placeholder="Enter your name"
							required>
					</div>
					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="create-email" placeholder="Enter your Email"
							required>
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="create-password" placeholder="********"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Sign Up</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>