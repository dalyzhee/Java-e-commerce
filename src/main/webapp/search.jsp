<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
Class.forName("org.sqlite.JDBC");
String url = "jdbc:sqlite:myDB.db";
Connection connection = null;
Statement statement = null;
ResultSet rs = null;
PreparedStatement pst;
%>
<!DOCTYPE html>
<html>
<head>
<title>Search Product Page</title>
<%@include file="includes/header.jsp" %>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">Shopping-Catalogue</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home <span class="sr-only">(current)</span>
				</a></li>
			</ul>
		</div>
	</div>
</nav>
<div class="container">
			<div class="card-header my-3">Search Page</div>
			<div class="row">
		<%
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			String category = request.getParameter("category");
			String sql = "select * from products where category='"+category+"' ";
			//pst = connection.prepareStatement(sql);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
		%>
				<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
						<img class="card-img-top"
							src="product-image/<%= rs.getString("image") %>"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%= rs.getString("name") %></h5>
							<h5 class="price">
								Price: $<%= rs.getString("price") %></h5>
							<h5 class="category">
								Category:
								<%= rs.getString("category") %></h5>
							<div class="mt-3 d-flex justify-content-between">
								<a href="addtocartservlet?id=<%= rs.getInt("id")  %>"
									class="btn btn-dark">Add to Cart</a> <a
									href="OrderNowServlet?quantity=1&id=<%= rs.getInt("id") %>"
									class="btn btn-primary">Buy Now</a>
							</div>

						</div>
					</div>
				</div>
				<%}

				connection.close();
				}catch(

			Exception e)
			{
				e.printStackTrace();
			}
		%>
<%@include file="includes/footer.jsp" %>
</body>
</html>
