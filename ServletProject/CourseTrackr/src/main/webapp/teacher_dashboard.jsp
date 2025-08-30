<%@ page import="java.util.*,com.coursetrackr.model.Course" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Teacher Dashboard</title></head>
<body>
<%@ include file="WEB-INF/header.jspf" %>
<div class="page-container">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Your Assigned Courses</h3>
    <a class="btn btn-outline-secondary" href="logout">Logout</a>
  </div>

  <%
    List<Course> assigned = (List<Course>)request.getAttribute("assigned");
    Map<Integer,Integer> counts = (Map<Integer,Integer>)request.getAttribute("counts");
    Map<Integer,List<String>> studentsMap = (Map<Integer,List<String>>)request.getAttribute("studentsMap");
  %>

  <div class="card card-rounded p-3">
    <table class="table table-striped">
      <thead><tr><th>Code</th><th>Title</th><th>Registered</th><th>Students</th></tr></thead>
      <tbody>
      <% for(Course c: assigned){
           int n = counts.getOrDefault(c.getId(),0);
           List<String> students = studentsMap.getOrDefault(c.getId(), Collections.emptyList());
      %>
        <tr>
          <td><%=c.getCode()%></td>
          <td><%=c.getTitle()%></td>
          <td><%=n%></td>
          <td>
            <button class="btn btn-sm btn-info" data-bs-toggle="collapse" data-bs-target="#st<%=c.getId()%>">View (<%=students.size()%>)</button>
            <div class="collapse mt-2" id="st<%=c.getId()%>">
              <% if(students.isEmpty()){ %>
                <div class="small-muted">No students</div>
              <% } else { %>
                <ul class="list-group">
                  <% for(String nm: students){ %>
                    <li class="list-group-item"><%=nm%></li>
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
</div>
</body></html>
