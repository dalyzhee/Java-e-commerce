package cn.cart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import cn.cart.dao.UserDao;
import cn.cart.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.cart.connection.DbCon;
import cn.cart.dao.*;
import cn.cart.model.*;

public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        
        try(PrintWriter out = response.getWriter()){
        	String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            if(user != null) {
            	out.print("User login");
            	request.getSession().setAttribute("auth", user);
            	System.out.print("user logged in");
            	response.sendRedirect("index.jsp");
            }else {
            	out.print("there is no user");
            }
        }catch (Exception e) {
			e.printStackTrace();
		} 
        
        
        
        
	}

	}

