package com.coursetrackr.servlet;

import com.coursetrackr.dao.RegistrationDAO;
import com.coursetrackr.model.Student;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class DropCourseServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final RegistrationDAO dao=new RegistrationDAO();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false);
        if(s==null||!"STUDENT".equals(s.getAttribute("role"))){ resp.sendRedirect("index.jsp"); return; }
        Student st=(Student)s.getAttribute("student");
        int courseId=Integer.parseInt(req.getParameter("courseId"));
        dao.drop(st.getId(),courseId);
        resp.sendRedirect("studentDashboard");
    }
}
