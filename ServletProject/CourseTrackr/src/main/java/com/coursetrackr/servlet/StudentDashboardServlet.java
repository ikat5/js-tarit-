package com.coursetrackr.servlet;

import com.coursetrackr.dao.CourseDAO;
import com.coursetrackr.dao.RegistrationDAO;
import com.coursetrackr.model.Course;
import com.coursetrackr.model.Student;
import com.coursetrackr.model.Teacher;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException; import java.util.*;

public class StudentDashboardServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final CourseDAO courseDAO=new CourseDAO(); private final RegistrationDAO regDAO=new RegistrationDAO();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);
        if(s==null||!"STUDENT".equals(s.getAttribute("role"))){ resp.sendRedirect("index.jsp"); return; }
        Student st=(Student)s.getAttribute("student");
        List<Course> courses=courseDAO.listAll();
        Map<Integer,Teacher> assigns=courseDAO.getAssignments();
        Set<Integer> mine=regDAO.myCourseIds(st.getId());
        req.setAttribute("courses",courses);
        req.setAttribute("assigns",assigns);
        req.setAttribute("mine",mine);
        req.getRequestDispatcher("student_dashboard.jsp").forward(req,resp);
    }
}
