package com.util;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class DataSourceUtils implements ServletContextListener {
    @Resource(name="jdbc/MySQL")
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }
}
