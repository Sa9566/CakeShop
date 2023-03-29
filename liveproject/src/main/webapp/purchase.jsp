<%@page import="com.app.sa.purchaseDTO.purchaseDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchase details</title>
</head>
<body>
<%String msg = (String)request.getAttribute("msg");
  if(msg!=null)
  {
%>
   <h3><font color="green"><%=msg %></font></h3>
<%} %>

<table border="1px" style="border-collapse: collapse;">
<%
List<purchaseDTO> pur = (List<purchaseDTO>)request.getAttribute("purchase");
if(pur!=null)
{
%>
  <tr>
    <th>ORDERID</th>
    <th>CAKEID</th>
    <th>USERNAME</th>
    <th>CAKENAME</th>
    <th>PRICE</th>
    <th>KILOGRAM</th>
    <th>PURCHASE STATUS</th>
  </tr>
  <%
      for(purchaseDTO dto:pur)
      {
  %>
  <tr>
    <td><%=dto.getOrderid() %></td>
    <td><%=dto.getCakeid() %></td>
    <td><%=dto.getUsername() %></td>
    <td><%=dto.getCakename() %></td>
    <td><%=dto.getPrice() %></td>
    <td><%=dto.getKilogram() %></td>
    <td><%=dto.getPurchasestatus() %></td>
  </tr>
  <%}
  } %>
</table>

<h4><a href='admin.jsp'>Back</a></h4>

</body>
</html>