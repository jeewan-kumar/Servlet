package com.jsp.userapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "root");

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    PrintWriter writer = resp.getWriter();
                    if (resultSet.next()) {
                        writer.write("<html><body>");
                        writer.write("<h1>Welcome, " + resultSet.getString("name") + "</h1>");
                        writer.write("</body></html>");
                    } else {
                        writer.write("<html><body>");
                        writer.write("<h1>Login failed</h1>");
                        writer.write("</body></html>");
                        RequestDispatcher dispatcher =req.getRequestDispatcher("Signup.html");
        	            dispatcher.forward(req, resp);
                    }
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        }
    }
}
