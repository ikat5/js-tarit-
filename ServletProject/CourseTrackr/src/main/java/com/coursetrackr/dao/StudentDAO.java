package com.coursetrackr.dao;

import com.coursetrackr.model.Student;
import com.coursetrackr.util.DBUtil;
import com.coursetrackr.util.PasswordUtil;
import java.sql.*;

public class StudentDAO{
    public boolean existsByUsernameOrEmail(String username,String email){
        String sql="SELECT 1 FROM students WHERE username=? OR email=? LIMIT 1";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,username);p.setString(2,email);
            try(ResultSet r=p.executeQuery()){return r.next();}
        }catch(SQLException e){throw new RuntimeException(e);}
    }
    public boolean create(String name,String reg,String username,String email,String rawPass){
        String sql="INSERT INTO students(name,registration_number,username,email,password_hash) VALUES(?,?,?,?,?)";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,reg);p.setString(3,username);p.setString(4,email);p.setString(5,PasswordUtil.sha256(rawPass));
            return p.executeUpdate()==1;
        }catch(SQLException e){return false;}
    }
    public Student authenticate(String username,String rawPass){
        String sql="SELECT id,name,registration_number,username,email,password_hash FROM students WHERE username=? LIMIT 1";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,username);
            try(ResultSet r=p.executeQuery()){
                if(r.next()){
                    if(r.getString("password_hash").equals(PasswordUtil.sha256(rawPass))){
                        return new Student(r.getInt("id"),r.getString("name"),r.getString("registration_number"),r.getString("username"),r.getString("email"));
                    }
                }
            }
        }catch(SQLException e){throw new RuntimeException(e);}
        return null;
    }
}
