package com.coursetrackr.util;

import java.sql.*;

public class DBUtil{
    private static final String URL="jdbc:mysql://localhost:3306/course_trackr?useSSL=false&serverTimezone=UTC";
    private static final String USER="ikat5";
    private static final String PASS="password";
    static{
        try{Class.forName("com.mysql.cj.jdbc.Driver");}
        catch(ClassNotFoundException e){throw new RuntimeException("MySQL Driver not found",e);}
    }
    public static Connection getConnection() throws SQLException{ return DriverManager.getConnection(URL,USER,PASS); }
}
