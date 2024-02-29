package com.jsp.userapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        long phone = Long.parseLong(req.getParameter("phone"));
        String password = req.getParameter("password");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student", "postgres", "root");
            String insert = "insert into users (id, name, email, phone_no, password, date_of_birth) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, phone);
            preparedStatement.setString(5, password);
            preparedStatement.setObject(6, dob);
            
            int updated = preparedStatement.executeUpdate();
            PrintWriter writer = resp.getWriter();
            
            if(updated>0) {
                writer.write("<html><body>");
                writer.write("<h1>User registration successful</h1>");
                writer.write("</body></html>");
            } else {
                writer.write("<html><body>");
                writer.write("<h1>User registration failed</h1>");
                writer.write("</body></html>");
            }

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
