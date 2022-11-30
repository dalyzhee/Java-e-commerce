<%@ page import="cn.cart.dao.ProductDao" %>
<%@ page import="cn.cart.connection.*" %>
<%@ page import="cn.cart.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	request.setAttribute("auth", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<title>Shopping Cart Page</title>
<%@include file="includes/header.jsp" %>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
<div class="card-header my-3">DashBoard</div>
<div class="row">
<div class="col-sm-6">
      <div class="card" style="width:400px">
  <img class="card-img-top" src="product-image/female-shoes.jpg" alt="Card image">
  <div class="card-body">
    <h4 class="card-title">Admin</h4>
    <p class="card-text">This is admin Page.</p>
    <a href="#" class="btn btn-primary">See Profile</a>
  </div>
</div>
    </div>
    <div class="col-sm-6">
      <a href="Product.jsp" class="btn btn-primary">Add Product</a>
    </div>
</div>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>