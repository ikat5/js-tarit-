package com.coursetrackr.dao;

import com.coursetrackr.model.Admin;
import com.coursetrackr.util.DBUtil;
import java.sql.*;

public class AdminDAO{
    public Admin authenticate(String name,String code){
        String sql="SELECT id,name,admin_code FROM admins WHERE name=? AND admin_code=? LIMIT 1";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,code);
            try(ResultSet r=p.executeQuery()){
                if(r.next()) return new Admin(r.getInt("id"),r.getString("name"),r.getString("admin_code"));
            }
        }catch(SQLException e){throw new RuntimeException(e);}
        return null;
    }
}
