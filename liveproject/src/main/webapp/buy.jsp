
<%@page import="java.util.Random"%>
<%@page import="com.app.sa.cartControllers.generateotp"%>
<%@page import="com.app.sa.cartControllers.mail"%>
<%@page import="com.app.sa.cartDTO.cartDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.app.sa.cartDAO.cartDAO"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buy Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style type="text/css">
.form
{
   margin-left: 5cm;
}
</style>
</head>
<body>
<%!
String otp1="";
String msg="";
Random rd1 = new Random();
%>
<%
        
		HttpSession ses1 = request.getSession(false);
		if(ses1!=null)
		{
			String name =  (String)ses1.getAttribute("un");	
			generateotp otp = new generateotp();
		     otp1 = otp.generateotp(5);

			System.out.println("preparing to send message ...");
			String message = "Hey, This is Cakeshop, Your OTP is "+otp1+"...\n After Purchase a Product Please Share Your Exact Location To This Mail Address...\n And If You Have Any Doubt's Contact Us..\n Thank You...";
			String subject = "OTP : Confirmation";
			String to = ""+name+"";
			String from = "cakeshop9566@gmail.com";
			if(mail.sendEmail(message,subject,to,from))
			{
			    msg="OTP Send Succesfully To Your Email....!!!Check";
			}
			else
			{
				msg="Something Wrong.!Try Again Later..";
			}
		}
		else
		{
			%>
			<font color="red">Something Wrong</font>
		<% }
%>

<%
    cartDAO dao = new cartDAO();
    HttpSession ses = request.getSession(false);
    try
    {
    if(ses!=null)
    {
  	   String name = (String)ses.getAttribute("un");
  	   List<cartDTO> cart = dao.displayall();
     if(cart!=null)
     {%>
   <table border="1px" style="border-collapse: collapse;">
    <tr>
    <th>ORDERID</th>
    <th>CAKENAME</th>
    <th>KILOGRAM</th>
    <th>PRICE</th>
    </tr>
    <% 
     for(cartDTO dto:cart)
     {
    	 if(name.equals(dto.getUsername()))
    	 {
 %> 
  <tr>
    <td><input readonly="readonly" type="text" value=<%=dto.getOrderid() %> name="orderid"></td>
    <td><input readonly="readonly" type="text" value=<%=dto.getCakename() %> name="cakename"></td>
    <td><input readonly="readonly" type="text" value=<%=dto.getKilogram() %> name="kilogram"></td>
    <td><input readonly="readonly" type="text" value=<%=dto.getPrice() %> name="price"></td>
  </tr>
  <%}
     } 
     cartDAO dao3 = new cartDAO();
       double db = dao3.updatequan(name);
     
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
     <%}
     %>
   </table>
   <br>
   <hr>
   <br>
   <form action="buycakecartController" method="post">
   <pre>
   <input type="text" hidden="" value=<%=otp1 %> name="otp1">
   <input placeholder="Enter Otp Here..." type="text" class="form" name="otp">
       <input type="submit" class="form" Value="Verify And Pay">
   </pre>
   </form>
     <%}
    }
    }
     catch (SQLException e) 
       {
     	msg = "Server Error!!!";
			e.printStackTrace();
		}
     %>   

    
<!--OTP  Modal -->
<div class="modal fade" id="example2Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel" style="color:Green;">Message</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
       <h3 style="color:Green"><%=msg%></h3>
      </div>
    </div>
  </div>
</div>   

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script type="text/javascript">
				const myModal = new bootstrap.Modal('#example2Modal');
				window.addEventListener('DOMContentLoaded',()=>{
					myModal.show();
				});
   </script>           
</body>
</html>