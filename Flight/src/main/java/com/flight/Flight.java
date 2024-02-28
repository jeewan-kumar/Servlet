package com.flight;

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

public class Flight extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
        String airline = req.getParameter("airline");
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        int price = Integer.parseInt(req.getParameter("price"));    
        

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "root");
            String insert = "insert into flight (flightid, airline, origin, destination, price) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, airline);
            preparedStatement.setString(3, origin);
            preparedStatement.setString(4, destination);
            preparedStatement.setInt(5, price);

            int updated = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();

            if(updated > 0) {
                writer.write("<html><body>");
                writer.write("<h1>Flight registration successful</h1>");
                writer.write("</body></html>");
            } else {
                writer.write("<html><body>");
                writer.write("<h1>Flight registration failed</h1>");
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



