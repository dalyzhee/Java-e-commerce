package cn.cart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import cn.cart.dao.UserDao;
import cn.cart.model.User;

import cn.cart.connection.DbCon;
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html");
        
        try(PrintWriter out = response.getWriter()){
        	String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");
            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            if(user != null) {
            	request.getSession().setAttribute("auth", user);
            	System.out.print("Admin logged in");
            	response.sendRedirect("Dashboard.jsp");
            }else {
            	out.println("there is no user");
            }
        }catch (Exception e) {
        	e.printStackTrace();
        }
        
        
	}

}
