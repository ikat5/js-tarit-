<%@ page import="java.util.*,com.coursetrackr.model.Course,com.coursetrackr.model.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Admin Dashboard</title></head>
<body>
<%@ include file="WEB-INF/header.jspf" %>
<div class="page-container">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Admin Dashboard</h3>
    <div>
      <a class="btn btn-outline-secondary" href="logout">Logout</a>
    </div>
  </div>

  <div>
    <%
      String m=(String)session.getAttribute("msg");
      if(m!=null){ out.print("<div class='alert alert-success'>"+m+"</div>"); session.removeAttribute("msg"); }
      List<Teacher> teachers=(List<Teacher>)request.getAttribute("teachers");
      List<Course> courses=(List<Course>)request.getAttribute("courses");
      Map<Integer,Teacher> assigns=(Map<Integer,Teacher>)request.getAttribute("assigns");
      Map<Integer,Integer> counts=(Map<Integer,Integer>)request.getAttribute("counts");
      Map<Integer,List<String>> studentsMap=(Map<Integer,List<String>>)request.getAttribute("studentsMap");
    %>

    <div class="card card-rounded p-3 mb-4">
      <h5>Courses & Assignments</h5>
      <table class="table table-striped mt-3">
        <thead><tr><th>Code</th><th>Title</th><th>Registered</th><th>Assigned Teacher</th><th>Students</th><th>Action</th></tr></thead>
        <tbody>
        <% for(Course c : courses){
             Teacher t = assigns.get(c.getId());
             int n = counts.getOrDefault(c.getId(),0);
             List<String> students = studentsMap.getOrDefault(c.getId(), Collections.emptyList());
        %>
          <tr>
            <td><%=c.getCode()%></td>
            <td><%=c.getTitle()%></td>
            <td><%=n%></td>
            <td><%= t==null ? "Unassigned" : t.getName() %></td>
            <td>
              <button class="btn btn-sm btn-info" type="button" data-bs-toggle="collapse" data-bs-target="#students<%=c.getId()%>">
                View (<%=students.size()%>)
              </button>
            </td>
            <td>
              <% if(t==null && n>0){ %>
                <!-- assign -->
                <form action="assignCourse" method="post" style="display:inline">
                  <input type="hidden" name="courseId" value="<%=c.getId()%>">
                  <select name="teacherId" class="form-select form-select-sm d-inline-block" style="width:200px" required>
                    <option value="">--select teacher--</option>
                    <% for(Teacher th: teachers){ %>
                      <option value="<%=th.getId()%>"><%=th.getName()%> (<%=th.getCode()%>)</option>
                    <% } %>
                  </select>
                  <button class="btn btn-sm btn-brand" type="submit">Assign</button>
                </form>
              <% } else if(t==null && n==0){ %>
                <span class="small-muted">Assign when students register.</span>
              <% } else { %>
                <!-- already assigned: allow change -->
                <form action="assignCourse" method="post" style="display:inline">
                  <input type="hidden" name="courseId" value="<%=c.getId()%>">
                  <select name="teacherId" class="form-select form-select-sm d-inline-block" style="width:200px" required>
                    <option value="">--select teacher--</option>
                    <% for(Teacher th: teachers){ %>
                      <option value="<%=th.getId()%>" <%= (th.getId()==t.getId() ? "selected" : "") %>><%=th.getName()%> (<%=th.getCode()%>)</option>
                    <% } %>
                  </select>
                  <button class="btn btn-sm btn-warning" type="submit">Change</button>
                </form>
              <% } %>
            </td>
          </tr>

          <tr class="collapse" id="students<%=c.getId()%>">
            <td colspan="6">
              <div class="p-2">
                <% if(students.isEmpty()){ %>
                  <div class="small-muted">No students registered for this course yet.</div>
                <% } else { %>
                  <ul class="list-group">
                    <% for(String sname : students){ %>
                      <li class="list-group-item"><%=sname%></li>
                    <% } %>
                  </ul>
                <% } %>
              </div>
            </td>
          </tr>

        <% } %>
        </tbody>
      </table>
    </div>

    <div class="card card-rounded p-3">
      <h5>Teachers</h5>
      <table class="table table-sm mt-2">
        <thead><tr><th>Name</th><th>Code</th></tr></thead>
        <tbody>
        <% for(Teacher th: teachers){ %>
          <tr><td><%=th.getName()%></td><td><%=th.getCode()%></td></tr>
        <% } %>
        </tbody>
      </table>
    </div>

  </div>
</div>
</body></html>
