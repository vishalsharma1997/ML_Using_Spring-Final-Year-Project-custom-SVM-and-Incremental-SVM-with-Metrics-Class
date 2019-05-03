<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
<link href="styles/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
</style>
</head>
<body>
<body background="styles/images/17.jpg" style="background-position:center top">
    <form action="login" method="post"> 
<div class="text-block">
  <center><font size="20" face="calibri light" color="white" style="position:relative;top:60px;">SIGN UP</font></center>
  <div class="container">
  
  <div class="t1">
     <div class="t11">
	 <i class="fa fa-envelope" style="font-size:24px;position:relative;left:25%;top:25%;color:red"></i>
	 </div>
	 <div class="t12">
	  <input type="email" name = "emailid" id = "emailid"  style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" placeholder="e.g. mail@gmail.com" class="text-line" required>
	 </div>
  </div>
 
  <div class="t2">
     <div class="t21">
	 <i class="fa fa-key" style="font-size:24px;position:relative;left:25%;top:25%;color:red"></i>
	 </div>
	 <div class="t22">
		<input type="password" name = "password" id = "password" onclick="myFunction()" style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" class="text-line" placeholder="password" required>
	 </div>
  </div>
    <div class="t2">
     <div class="t21">
	 <i class="fa fa-user" style="font-size:24px;position:relative;left:25%;top:25%;color:red"></i>
	 </div>
	 <div class="t22">
		<input type="text" name = "firstname" id = "firstname" style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" class="text-line" placeholder="first name" required>
	 </div>
  </div>
	<div class="t2">
     <div class="t21">
	 <i class="fa fa-user-circle" style="font-size:24px;position:relative;left:25%;top:25%;color:red"></i>
	 </div>
	 <div class="t22">
		<input type="text" name = "lastname" id = "lastname" style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" class="text-line" placeholder="last name" required>
	 </div>
  </div>
  
  <div class="t3">
     <div class="t31">
	 <i class="fa fa-mobile-phone" style="font-size:30px;position:relative;left:35%;top:25%;color:red"></i>
	 
	 </div>
	 <div class="t32">
	 <input type="text" name = "phoneno" id = "phoneno" style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" class="text-line" placeholder="Please enter 10 digit phone number" pattern="[0-9]{10}" required>
	 </div>
  </div>
   <br><br><input type="checkbox" name="" value="" checked style="position:relative;top:60px; left:30px" required><font size="4" face="calibri light" color="yellow" style="position:absolute;top:395px;left:70px">I agree to the terms and condition</font><br>
   <button class="button" style = "position:relative;top:80px;" type="submit">SIGN UP</button>
   <center><font size="4" face="calibri light" color="white" style="position:relative;top:38px;left:30px;">Have an Account? <a href="login.html" style="color:red;font-size:25px;">Login</a></font></center>
   </div>
  </div>
 </form>
</body>
</html>