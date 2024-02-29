package com.request_dispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class Example extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println(email);
		req.setAttribute("email", email);
		
		if(password.equals("123")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("requs");
			dispatcher.forward(req, resp);
		} else {
			PrintWriter writer = resp.getWriter();
			 	writer.write("<html><body>");
	            writer.write("<h1>"+ "wrong password "+ "</h1>");
	            writer.write("</body></html>");
	            RequestDispatcher dispatcher =req.getRequestDispatcher("sigin.html");
	            dispatcher.forward(req, resp);
		}
		
		
	}
	

}
