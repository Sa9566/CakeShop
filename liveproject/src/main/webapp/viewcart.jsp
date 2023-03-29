<%@page import="com.app.sa.cartDAO.cartDAO"%>
<%@page import="com.app.sa.cartDTO.cartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Page</title>
<style></style>
</head>
<body>
<%!
double db=0.0;
%>
 <%
  String msg = (String)request.getAttribute("msg");
  if(msg!=null)
  {
%>
   <h2><font color="green"><%= msg %></font></h2>
<%}
%>
    <%
    HttpSession ses = request.getSession(false);
    if(ses!=null)
    {
  	   String name = (String)ses.getAttribute("un");
     List<cartDTO> cart = (List<cartDTO>)request.getAttribute("cart");
     if(cart!=null)
     {%>
    	 <table border="1px" style="border-collapse: collapse;">
   <tr>
    <th>ORDERID</th>
    <th>CAKENAME</th>
    <th>KILOGRAM</th>
    <th>PRICE</th>
    <th>Delete</th>
    </tr>
    <% 
     for(cartDTO dto:cart)
     {
    	 if(name.equals(dto.getUsername()))
    	 {
 %> 
  <tr>
    <td><%=dto.getOrderid() %></td>
    <td><%=dto.getCakename() %></td>
    <td><%=dto.getKilogram() %></td>
    <td><%=dto.getPrice() %></td>
    <td><form action="deletecartController" method="post">
      <input type="text" name='orderid' value=<%=dto.getOrderid()%> hidden="">
      <input type="submit" value="Remove">
    </form></td>
  </tr>
  <%}
     } 
     cartDAO dao = new cartDAO();
       double db = dao.updatequan(name);
     
     if(db>1200)
     {
    	 double d = db-db*(0.10);
   %>
     <tr><td style="text-align: center;" colspan="5">Original Amount=<%=db %></td></tr>
     <tr><td style="text-align: center;" colspan="5">Discount Amount=<%=d%></td></tr>
     <tr><td style="text-align: center;" colspan="5">Total Amount To Pay=<%=d%></td></tr>
     <%} 
     else
     {%>
       <tr><td colspan="5" style="text-align: center;">Total Amount=<%=db %></td></tr>
     <%} %>
     <tr>
     <td colspan="5" style="text-align: center;"><form action="buy.jsp" method="post">
      <input type="submit" value="Buy">
    </form></td>
    </tr>
    </table>
     <%}
    }
   %> 
   
   
   
   
   
   <br>
   <form action="BackcakeController">
   <input type="submit" value="Back">
   </form>
</body>
</html>