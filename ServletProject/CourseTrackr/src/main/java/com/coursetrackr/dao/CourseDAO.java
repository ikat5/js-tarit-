package com.coursetrackr.dao;

import com.coursetrackr.model.Course;
import com.coursetrackr.model.Teacher;
import com.coursetrackr.util.DBUtil;

import java.sql.*;
import java.util.*;

public class CourseDAO {
    public List<Course> listAll(){
        List<Course> list = new ArrayList<>();
        String sql = "SELECT id,code,title FROM courses ORDER BY code";
        try(Connection c = DBUtil.getConnection(); PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()){
            while(r.next()) list.add(new Course(r.getInt("id"), r.getString("code"), r.getString("title")));
        }catch(SQLException e){ throw new RuntimeException(e); }
        return list;
    }

    public Map<Integer,Teacher> getAssignments(){
        String sql = "SELECT c.id AS cid, t.id AS tid, t.name, t.teacher_code FROM courses c " +
                     "LEFT JOIN course_assignments ca ON c.id = ca.course_id " +
                     "LEFT JOIN teachers t ON ca.teacher_id = t.id";
        Map<Integer,Teacher> map = new HashMap<>();
        try(Connection c = DBUtil.getConnection(); PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()){
            while(r.next()){
                int cid = r.getInt("cid");
                String tcode = r.getString("teacher_code"); // will be null if no teacher
                if(tcode == null) map.put(cid, null);
                else map.put(cid, new Teacher(r.getInt("tid"), r.getString("name"), tcode));
            }
        }catch(SQLException e){ throw new RuntimeException(e); }
        return map;
    }

    // assign only if not assigned (keeps old behavior)
    public boolean assign(int courseId,int teacherId){
        String check="SELECT 1 FROM course_assignments WHERE course_id=? LIMIT 1";
        String insert="INSERT INTO course_assignments(course_id,teacher_id) VALUES(?,?)";
        try(Connection c=DBUtil.getConnection()){
            c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement(check)){ p.setInt(1,courseId); try(ResultSet r=p.executeQuery()){ if(r.next()){ c.rollback(); return false; } } }
            try(PreparedStatement p=c.prepareStatement(insert)){ p.setInt(1,courseId); p.setInt(2,teacherId); int rows=p.executeUpdate(); c.commit(); return rows==1; }
        }catch(SQLException e){ return false; }
    }

    // NEW: assignOrReplace -> if an assignment exists update it, otherwise insert
    public boolean assignOrReplace(int courseId,int teacherId){
        String update = "UPDATE course_assignments SET teacher_id=? WHERE course_id=?";
        String insert = "INSERT INTO course_assignments(course_id,teacher_id) VALUES(?,?)";
        try(Connection c = DBUtil.getConnection()){
            c.setAutoCommit(false);
            try(PreparedStatement pu = c.prepareStatement(update)){
                pu.setInt(1, teacherId); pu.setInt(2, courseId);
                int updated = pu.executeUpdate();
                if(updated == 1){ c.commit(); return true; }
            }
            try(PreparedStatement pi = c.prepareStatement(insert)){
                pi.setInt(1, courseId); pi.setInt(2, teacherId);
                int inserted = pi.executeUpdate(); c.commit(); return inserted==1;
            }catch(SQLException e){ c.rollback(); throw e; }
        }catch(SQLException e){ return false; }
    }

    // counts registered students per course
    public Map<Integer,Integer> registeredCounts(){
        String sql = "SELECT course_id, COUNT(*) cnt FROM registrations GROUP BY course_id";
        Map<Integer,Integer> m = new HashMap<>();
        try(Connection c=DBUtil.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()){
            while(r.next()) m.put(r.getInt("course_id"), r.getInt("cnt"));
        }catch(SQLException e){ throw new RuntimeException(e); }
        return m;
    }

    // helper if you want teacher list here (optional)
    public List<Teacher> listAllTeachers(){
        List<Teacher> list=new ArrayList<>();
        String sql="SELECT id,name,teacher_code FROM teachers ORDER BY name";
        try(Connection c=DBUtil.getConnection(); PreparedStatement p=c.prepareStatement(sql); ResultSet r=p.executeQuery()){
            while(r.next()) list.add(new Teacher(r.getInt(1), r.getString(2), r.getString(3)));
        }catch(SQLException e){ throw new RuntimeException(e); }
        return list;
    }
}
