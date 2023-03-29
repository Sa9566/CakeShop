<%@page import="java.util.List"%>
<%@page import="com.app.sa.userDTO.userDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
</head>
<body>

<%
   String msg = (String)request.getAttribute("msg");
    if(msg!=null)
    {
%>
     <h2><font color="green"><%=msg %></font></h2>
<%} %>

<table border="1px" style="border-collapse: collapse;">
<%
  List<userDTO> user = (List<userDTO>)request.getAttribute("user");  
   if(user!=null)
   {
     %>
     <tr>
    <th>Username</th>
    <th>Mobile Number</th>
    <th>Name</th>
    </tr>
  
  <%for(userDTO dto:user)
  {
%>
  
  <tr>
    <td><%=dto.getUsername() %></td>
    <td><%=dto.getContact() %></td>
    <td><%=dto.getName() %></td>
  </tr>
  <%}
  } %>
</table>

<h4><a href='admin.jsp'>Back</a></h4>



</body>
</html>