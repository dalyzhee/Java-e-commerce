package cn.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import cn.cart.connection.DbCon;
import cn.cart.dao.ProductDao;
import cn.cart.model.Product;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter()){
			String name = request.getParameter("product-name");
			String category = request.getParameter("product-category");
			String image = request.getParameter("product-image");
			String price = request.getParameter("product-price");
			System.out.print(name+category+image+price);
			Product product = new Product();
			product.setName(name);
			product.setCategory(category);
			product.setImage(image);
			product.setPrice(Double.parseDouble(price));
			
			ProductDao productDao;
			productDao = new ProductDao(DbCon.getConnection());
			boolean result = productDao.insertProducts(product);
			out.print("Added");
			response.sendRedirect("Dashboard.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);		
	}

}
