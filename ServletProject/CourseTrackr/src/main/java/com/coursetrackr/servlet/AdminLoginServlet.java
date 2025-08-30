package com.coursetrackr.servlet;

import com.coursetrackr.dao.AdminDAO;
import com.coursetrackr.model.Admin;
import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class AdminLoginServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    private final AdminDAO dao=new AdminDAO();
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        String name=req.getParameter("name"); String code=req.getParameter("admincode");
        Admin a = dao.authenticate(name,code);
        if(a==null){ req.setAttribute("msg","Invalid name or admin code."); req.getRequestDispatcher("admin.jsp").forward(req,resp); return; }
        HttpSession s=req.getSession(true); s.setAttribute("role","admin"); s.setAttribute("admin",a);
        resp.sendRedirect("adminDashboard");
    }
}
