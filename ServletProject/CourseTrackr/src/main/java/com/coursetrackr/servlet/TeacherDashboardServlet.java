package com.coursetrackr.servlet;

import com.coursetrackr.dao.CourseDAO;
import com.coursetrackr.dao.RegistrationDAO;
import com.coursetrackr.model.Course;
import com.coursetrackr.model.Teacher;

import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException; import java.util.*;

public class TeacherDashboardServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final CourseDAO courseDAO=new CourseDAO();
    private final RegistrationDAO regDAO=new RegistrationDAO();

    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);
        if(s==null || !"TEACHER".equals(s.getAttribute("role"))){ resp.sendRedirect("index.jsp"); return; }
        Teacher t=(Teacher)s.getAttribute("teacher");

        // find courses assigned to this teacher
        List<Course> all = courseDAO.listAll();
        Map<Integer,Teacher> assigns = courseDAO.getAssignments();
        List<Course> assigned = new ArrayList<>();
        for(Course c : all){
            Teacher assignedTeacher = assigns.get(c.getId());
            if(assignedTeacher != null && assignedTeacher.getId() == t.getId()) assigned.add(c);
        }

        Map<Integer,Integer> counts = courseDAO.registeredCounts();

        // NEW: student lists for assigned courses only
        Map<Integer, List<String>> studentsMap = new HashMap<>();
        for(Course c : assigned) studentsMap.put(c.getId(), regDAO.studentNamesInCourse(c.getId()));

        req.setAttribute("assigned", assigned);
        req.setAttribute("counts", counts);
        req.setAttribute("studentsMap", studentsMap);
        req.getRequestDispatcher("teacher_dashboard.jsp").forward(req, resp);
    }
}
