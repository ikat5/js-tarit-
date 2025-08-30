package com.coursetrackr.servlet;

import com.coursetrackr.dao.StudentDAO;
import com.coursetrackr.model.Student;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class StudentSignupServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final StudentDAO dao=new StudentDAO();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        String name=val(req.getParameter("name"));
        String reg=val(req.getParameter("registrationNumber"));
        String username=val(req.getParameter("username"));
        String email=val(req.getParameter("email"));
        String p1=val(req.getParameter("password"));
        String p2=val(req.getParameter("confirmPassword"));

        if(name.isEmpty()||reg.isEmpty()||username.isEmpty()||email.isEmpty()||p1.isEmpty()||p2.isEmpty()){
            req.setAttribute("msg","All fields required."); req.getRequestDispatcher("student.jsp").forward(req,resp); return;
        }
        if(!p1.equals(p2)){ req.setAttribute("msg","Passwords do not match."); req.getRequestDispatcher("student.jsp").forward(req,resp); return; }
        if(dao.existsByUsernameOrEmail(username,email)){
            req.setAttribute("msg","User exists, please login."); req.getRequestDispatcher("student.jsp").forward(req,resp); return;
        }
        boolean ok=dao.create(name,reg,username,email,p1);
        if(!ok){ req.setAttribute("msg","Signup failed."); req.getRequestDispatcher("student.jsp").forward(req,resp); return; }
        Student s=dao.authenticate(username,p1);
        HttpSession session=req.getSession(true);
        session.setAttribute("role","STUDENT");
        session.setAttribute("student",s);
        resp.sendRedirect("studentDashboard");
    }
    private static String val(String s){return s==null?"":s.trim();}
}
