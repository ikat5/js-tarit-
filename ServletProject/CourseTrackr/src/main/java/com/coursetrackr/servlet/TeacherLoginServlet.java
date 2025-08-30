package com.coursetrackr.servlet;

import com.coursetrackr.dao.TeacherDAO;
import com.coursetrackr.model.Teacher;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class TeacherLoginServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final TeacherDAO dao=new TeacherDAO();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String name=req.getParameter("name"); String code=req.getParameter("teachercode");
        Teacher t=dao.authenticate(name,code);
        if(t==null){ req.setAttribute("msg","Invalid name or teacher code."); req.getRequestDispatcher("teacher.jsp").forward(req,resp); return; }
        HttpSession s=req.getSession(true); s.setAttribute("role","TEACHER"); s.setAttribute("teacher",t);
        resp.sendRedirect("teacherDashboard");
    }
}
