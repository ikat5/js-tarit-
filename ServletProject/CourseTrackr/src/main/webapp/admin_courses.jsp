<%@ page import="java.util.*,com.coursetrackr.model.Course,com.coursetrackr.model.User" %>
<%
    List<Course> courses = (List<Course>)request.getAttribute("courses");
    Map<Integer,User> assignments = (Map<Integer,User>)request.getAttribute("assignments");
    List<User> teachers = (List<User>)request.getAttribute("teachers");
    String msg = (String)session.getAttribute("msg");
    if(msg!=null){ out.println("<p style='color:green;'>"+msg+"</p>"); session.removeAttribute("msg"); }
%>
<h2>Courses</h2>
<table border="1" cellpadding="6" cellspacing="0">
    <tr><th>Code</th><th>Title</th><th>Assigned Teacher</th><th>Action</th></tr>
    <% for(Course c: courses){ %>
        <tr>
            <td><%=c.getCode()%></td>
            <td><%=c.getTitle()%></td>
            <td><%= (assignments.get(c.getId())==null) ? "Unassigned" : assignments.get(c.getId()).getName() %></td>
            <td>
                <% if(assignments.get(c.getId())==null){ %>
                    <form method="post" action="adminCourses" style="margin:0;">
                        <input type="hidden" name="courseId" value="<%=c.getId()%>"/>
                        <select name="teacherId" required>
                            <option value="">--select teacher--</option>
                            <% for(User t: teachers){ %>
                                <option value="<%=t.getId()%>"><%=t.getName()%> (<%=t.getUsername()%>)</option>
                            <% } %>
                        </select>
                        <button type="submit">Assign</button>
                    </form>
                <% } else { %>
                    Already assigned
                <% } %>
            </td>
        </tr>
    <% } %>
</table>
