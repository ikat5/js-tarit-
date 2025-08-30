package com.coursetrackr.servlet;

import com.coursetrackr.dao.CourseDAO;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class AssignCourseServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final CourseDAO dao=new CourseDAO();

    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);
        if(s==null || !"admin".equals(s.getAttribute("role"))){ resp.sendRedirect("index.jsp"); return; }
        try{
            int courseId=Integer.parseInt(req.getParameter("courseId"));
            int teacherId=Integer.parseInt(req.getParameter("teacherId"));
            boolean ok = dao.assignOrReplace(courseId, teacherId);
            req.getSession().setAttribute("msg", ok ? "Assigned/changed successfully." : "Assignment failed.");
        }catch(Exception e){ req.getSession().setAttribute("msg","Invalid input."); }
        resp.sendRedirect("adminDashboard");
    }
}
