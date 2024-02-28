package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Employee extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String position = req.getParameter("position");
        int salary = Integer.parseInt(req.getParameter("salary"));    
        

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "root");
            String insert = "insert into employee (employeeid, firstname, lastname, position, salary) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, position);
            preparedStatement.setInt(5, salary);

            int updated = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();

            if(updated > 0) {
                writer.write("<html><body>");
                writer.write("<h1>Employee registration successful</h1>");
                writer.write("</body></html>");
            } else {
                writer.write("<html><body>");
                writer.write("<h1>Employee registration failed</h1>");
                writer.write("</body></html>");
            }

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	

}


