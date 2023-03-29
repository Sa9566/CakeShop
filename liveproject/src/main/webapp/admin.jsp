<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="icon" type="image/x-icon" href="https://cdn.iconscout.com/icon/premium/png-512-thumb/cake-2604074-2168304.png?f=avif&w=256">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style type="text/css">
#drop li a
{
text-decoration: none;
}
#para img
{
   width: 100%;
   height: 12cm;
}
</style>
</head>
<body>
<%HttpSession ses = request.getSession(false);
  String user = (String)ses.getAttribute("user");

%>
<nav  class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  <nav>
  <div class="container">
    <a class="navbar-brand" href="#">
    <img src="https://cdn-icons-png.flaticon.com/512/792/792771.png" alt="" width="40" height="30">
    </a>
  </div>
  </nav>
    <a class="navbar-brand" href="#">Cake Shop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div  class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <div class="nav-item dropdown" >
          <a  class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Cake Details
          </a>
          <ul id="drop" class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a href='addcake.jsp'>*Add Cake</a></li>
            <li><a href='deletecake.jsp'>*Delete Cake</a></li>
             <li><a href='Searchcake.jsp'>*Search Cake</a></li>
             <li><a href='updatecake.jsp'>*Update Cake</a></li>
            <li><a href='DisplayallcakeController'>*DisplayAll Cake</a></li>
          </ul>
        </div>
        <div class="nav-item dropdown" >
          <a  class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          User Details
          </a>
          <ul id="drop" class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a href='displayUser'>*Display User Details</a></li>
            <li><a href='purchaseUser'>*User Purchase Details</a></li>
             <li><a href='deletepurchase.jsp'>*Delete Purchase Details</a></li>
          </ul>
        </div>
      </ul>
    </div>
  </div>
  <div>
   <h3>Welcome Admin <%=user %></h3>
  </div>
  <div>
  <button type="button" class="btn btn-primary"><a href='Logout' style="color:black">Logout</a></button>
  </div>
  </nav>
<br><br>
<div id="para">
<img alt="" src="https://t4.ftcdn.net/jpg/01/05/72/61/240_F_105726195_r0MpL0Noxp2NeMh3RsRwCskbeL7ensjV.jpg">
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>