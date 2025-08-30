<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Teacher Login</title></head>
<body>
<%@ include file="WEB-INF/header.jspf" %>
<div class="page-container">
  <div class="card card-rounded p-4">
    <h4>Teacher Login</h4>
    <form action="teacherLogin" method="post" class="mt-3">
      <div class="mb-2"><input class="form-control" type="text" name="name" placeholder="Full Name" required></div>
      <div class="mb-3"><input class="form-control" type="text" name="teachercode" placeholder="Teacher Code" required></div>
      <button class="btn btn-brand">Login</button>
      <a class="btn btn-outline-secondary float-end" href="index.jsp">Back</a>
    </form>
    <div class="mt-2 text-success"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %></div>
  </div>
</div>
</body></html>
