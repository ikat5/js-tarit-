package com.coursetrackr.servlet;

import javax.servlet.*; import javax.servlet.http.*; import java.io.IOException;

public class LogoutServlet extends HttpServlet{
    private static final long serialVersionUID=1L;
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        HttpSession s=req.getSession(false); if(s!=null) s.invalidate();
        resp.sendRedirect("index.jsp");
    }
}
