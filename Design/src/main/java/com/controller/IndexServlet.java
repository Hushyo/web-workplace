package com.controller;

import com.entity.User;
import com.util.DataSourceUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
    {
        List<User> users=new ArrayList<>();
        String sql="select * from user";
        try (Connection coon= DataSourceUtils.getConnection();
             PreparedStatement ps=coon.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                User user= new User(rs.getInt("id"),rs.getString("name"),
                        rs.getObject("insert_time", LocalDateTime.class));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("users",users);
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,resp);
    }
}
