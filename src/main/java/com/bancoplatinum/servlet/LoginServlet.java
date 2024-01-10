package com.bancoplatinum.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bancoplatinum.Dao.conexionBD;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = conexionBD.getConnection()) {

            if (verificarCredenciales(connection, username, password)) {

                response.sendRedirect("login.jsp");
            } else {
                
                response.setContentType("text/plain");
                if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                    response.getWriter().write("Ingresar valores ");
                } else {
                    response.getWriter().write("Usuario y/o contraseña incorrectos"); 
                }
            }
        } catch (SQLException e) {
            response.sendRedirect("login.jsp?error=Error en la conexión a la base de datos");
        }
    }

    private boolean verificarCredenciales(Connection connection, String username, String password) throws SQLException {
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        } else {
            return false; 
        }
    }
}
