package cn.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import cn.cart.connection.DbCon;
import cn.cart.dao.ProductDao;
import cn.cart.dao.UserDao;
import cn.cart.model.Product;
import cn.cart.model.User;


public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			String name = request.getParameter("create-name");
			String email = request.getParameter("create-email");
			String password = request.getParameter("create-password");
			System.out.print(name+email+password);
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			
			UserDao userDao;
			userDao = new UserDao(DbCon.getConnection());
			boolean result = userDao.insertUsers(user);
			System.out.print("Added");
			response.sendRedirect("login.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
