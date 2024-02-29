package com.request_dispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/requs")
public class Example1 extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s = (String) req.getAttribute("email");
		System.out.println(s);
		System.out.println("Hii");
		PrintWriter writer = resp.getWriter();

            writer.write("<html><body>");
            writer.write("<h1>request successful</h1>");
            writer.write("</body></html>");
		
	}
	

}
