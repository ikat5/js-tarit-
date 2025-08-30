<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    if(username == null){
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body { font-family: Arial; background: #eef2f3; text-align: center; padding-top: 100px; }
        a { display: inline-block; margin-top: 20px; padding: 10px 15px; background: #f44336; color: white; border-radius: 6px; text-decoration: none; }
        a:hover { background: #d32f2f; }
    </style>
</head>
<body>
    <h1>Welcome, <%=username%>!</h1>
    <h3>(Role: <%=role%>)</h3>
    <p>🚧 Features have not been built yet 🚧</p>
    <a href="LogoutServlet">Logout</a>
</body>
</html>
