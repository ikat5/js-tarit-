package com.coursetrackr.dao;

import com.coursetrackr.util.DBUtil;
import java.sql.*;
import java.util.*;

public class RegistrationDAO{
    public boolean register(int studentId,int courseId){
        String sql="INSERT INTO registrations(student_id,course_id) VALUES(?,?)";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,studentId);p.setInt(2,courseId); return p.executeUpdate()==1;
        }catch(SQLException e){return false;}
    }
    public boolean drop(int studentId,int courseId){
        String sql="DELETE FROM registrations WHERE student_id=? AND course_id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,studentId);p.setInt(2,courseId); return p.executeUpdate()==1;
        }catch(SQLException e){return false;}
    }
    public Set<Integer> myCourseIds(int studentId){
        Set<Integer> s=new HashSet<>();
        String sql="SELECT course_id FROM registrations WHERE student_id=?";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,studentId); try(ResultSet r=p.executeQuery()){ while(r.next()) s.add(r.getInt(1)); }
        }catch(SQLException e){ throw new RuntimeException(e); }
        return s;
    }

    // NEW: return student NAMES registered in a course (ordered by name)
    public List<String> studentNamesInCourse(int courseId){
        List<String> list=new ArrayList<>();
        String sql="SELECT s.name FROM registrations r JOIN students s ON r.student_id=s.id WHERE r.course_id=? ORDER BY s.name";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,courseId); try(ResultSet r=p.executeQuery()){ while(r.next()) list.add(r.getString(1)); }
        }catch(SQLException e){ throw new RuntimeException(e); }
        return list;
    }

    // helper - usernames (if you used earlier)
    public List<String> studentUsernamesInCourse(int courseId){
        List<String> list=new ArrayList<>();
        String sql="SELECT s.username FROM registrations r JOIN students s ON r.student_id=s.id WHERE r.course_id=? ORDER BY s.username";
        try(Connection c=DBUtil.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,courseId); try(ResultSet r=p.executeQuery()){ while(r.next()) list.add(r.getString(1)); }
        }catch(SQLException e){ throw new RuntimeException(e); }
        return list;
    }
}
