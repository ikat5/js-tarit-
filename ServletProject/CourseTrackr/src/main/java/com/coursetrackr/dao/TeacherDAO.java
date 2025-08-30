package com.coursetrackr.dao;

import com.coursetrackr.model.Teacher;
import com.coursetrackr.util.DBUtil;
import java.sql.*;

public class TeacherDAO{
    public Teacher authenticate(String name,String code){
        String sql="SELECT id,name,teacher_code FROM teachers WHERE name=? AND teacher_code=? LIMIT 1";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,code);
            try(ResultSet r=p.executeQuery()){
                if(r.next()) return new Teacher(r.getInt("id"),r.getString("name"),r.getString("teacher_code"));
            }
        }catch(SQLException e){throw new RuntimeException(e);}
        return null;
    }
}
