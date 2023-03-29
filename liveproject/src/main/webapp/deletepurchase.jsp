<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete here</title>
</head>
<body>
<%String msg = (String)request.getAttribute("msg");
  if(msg!=null)
  {
%>
   <h3><font color="green"><%=msg %></font></h3>
<%} %>
<form method="post" action="deletepurchase">
<pre>
DELETE:<input type="text" name="delete">
       <input type="submit" value="Delete">
</pre>
</form>
<h3><a href='admin.jsp'>Back</a></h3>
</body>
</html>