package com.coursetrackr.servlet;

import com.coursetrackr.dao.CourseDAO;
import com.coursetrackr.dao.RegistrationDAO;
import com.coursetrackr.model.Course;
import com.coursetrackr.model.Teacher;
import com.coursetrackr.util.DBUtil;

import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;
import java.sql.*; import java.util.*;

public class AdminDashboardServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final CourseDAO courseDAO=new CourseDAO();
    private final RegistrationDAO regDAO=new RegistrationDAO();

    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);
        if(s==null || !"admin".equals(s.getAttribute("role"))){ resp.sendRedirect("index.jsp"); return; }

        // load teachers
        List<Teacher> teachers=new ArrayList<>();
        try(Connection c=DBUtil.getConnection(); PreparedStatement p=c.prepareStatement("SELECT id,name,teacher_code FROM teachers ORDER BY name"); ResultSet r=p.executeQuery()){
            while(r.next()) teachers.add(new Teacher(r.getInt(1), r.getString(2), r.getString(3)));
        }catch(SQLException e){ throw new RuntimeException(e); }

        List<Course> courses = courseDAO.listAll();
        Map<Integer,Teacher> assigns = courseDAO.getAssignments();
        Map<Integer,Integer> counts = courseDAO.registeredCounts();

        // NEW: students per course
        Map<Integer, List<String>> studentsMap = new HashMap<>();
        for(Course c : courses){
            studentsMap.put(c.getId(), regDAO.studentNamesInCourse(c.getId()));
        }

        req.setAttribute("teachers", teachers);
        req.setAttribute("courses", courses);
        req.setAttribute("assigns", assigns);
        req.setAttribute("counts", counts);
        req.setAttribute("studentsMap", studentsMap);
        req.getRequestDispatcher("admin_dashboard.jsp").forward(req, resp);
    }
}
