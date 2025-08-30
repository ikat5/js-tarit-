package com.coursetrackr.servlet;

import com.coursetrackr.dao.StudentDAO;
import com.coursetrackr.model.Student;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class StudentLoginServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final StudentDAO dao=new StudentDAO();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String username=req.getParameter("username"); String password=req.getParameter("password");
        Student s=dao.authenticate(username,password);
        if(s==null){ req.setAttribute("msg","Invalid credentials."); req.getRequestDispatcher("student.jsp").forward(req,resp); return; }
        HttpSession session=req.getSession(true);
        session.setAttribute("role","STUDENT");
        session.setAttribute("student",s);
        resp.sendRedirect("studentDashboard");
    }
}
