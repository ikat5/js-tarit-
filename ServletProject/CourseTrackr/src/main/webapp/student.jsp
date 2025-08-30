<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Student Signup/Login</title></head>
<body>
<%@ include file="WEB-INF/header.jspf" %>
<div class="page-container">
  <div class="row g-4">
    <div class="col-md-6">
      <div class="card card-rounded p-4">
        <h4>Student Signup</h4>
        <p class="small-muted">Create your student account to register courses.</p>
        <form action="studentSignup" method="post" class="mt-3">
          <div class="mb-2"><input class="form-control" type="text" name="name" placeholder="Full Name" required></div>
          <div class="mb-2"><input class="form-control" type="text" name="registrationNumber" placeholder="Registration Number" required></div>
          <div class="mb-2"><input class="form-control" type="text" name="username" placeholder="Username" required></div>
          <div class="mb-2"><input class="form-control" type="email" name="email" placeholder="Email" required></div>
          <div class="mb-2"><input class="form-control" type="password" name="password" placeholder="Set Password" required></div>
          <div class="mb-3"><input class="form-control" type="password" name="confirmPassword" placeholder="Confirm Password" required></div>
          <button class="btn btn-brand">Signup</button>
        </form>
      </div>
    </div>

    <div class="col-md-6">
      <div class="card card-rounded p-4">
        <h4>Student Login</h4>
        <p class="small-muted">Already have an account? Login here.</p>
        <form action="studentLogin" method="post" class="mt-3">
          <div class="mb-2"><input class="form-control" type="text" name="username" placeholder="Username" required></div>
          <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password" required></div>
          <button class="btn btn-brand">Login</button>
        </form>
      </div>

      <div class="mt-3 text-end">
        <a class="btn btn-outline-secondary" href="index.jsp">Back</a>
      </div>
      <div class="mt-2">
        <div class="text-success"><%= request.getAttribute("msg")==null?"":request.getAttribute("msg") %></div>
      </div>
    </div>
  </div>
</div>
</body></html>
