<%@ page import="java.util.*,com.coursetrackr.model.Course,com.coursetrackr.model.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Student Dashboard</title></head>
<body>
<%@ include file="WEB-INF/header.jspf" %>
<div class="container">
  <a class="btn" href="logout">Logout</a>
  <h2>Available Courses</h2>
  <%
    List<Course> courses=(List<Course>)request.getAttribute("courses");
    Map<Integer,Teacher> assigns=(Map<Integer,Teacher>)request.getAttribute("assigns");
    Set<Integer> mine=(Set<Integer>)request.getAttribute("mine");
  %>
  <table>
    <tr><th>Code</th><th>Title</th><th>Teacher</th><th>Action</th></tr>
    <% for(Course c:courses){ 
         Teacher t=assigns.get(c.getId());
         boolean registered=mine.contains(c.getId());
    %>
      <tr>
        <td><%=c.getCode()%></td>
        <td><%=c.getTitle()%></td>
        <td><%= t==null ? "No teacher assigned yet" : t.getName() %></td>
        <td>
          <% if(!registered){ %>
            <form action="registerCourse" method="post" style="display:inline">
              <input type="hidden" name="courseId" value="<%=c.getId()%>">
              <button class="btn" type="submit">Register</button>
            </form>
          <% }else{ %>
            <form action="dropCourse" method="post" style="display:inline">
              <input type="hidden" name="courseId" value="<%=c.getId()%>">
              <button class="btn" type="submit">Delete</button>
            </form>
          <% } %>
        </td>
      </tr>
    <% } %>
  </table>
</div>
</body></html>
