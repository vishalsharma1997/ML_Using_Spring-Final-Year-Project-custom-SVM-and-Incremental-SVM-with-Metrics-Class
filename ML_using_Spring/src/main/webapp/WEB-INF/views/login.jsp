<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body background="styles/images/17.jpg" style="background-position:center top;background-attachment:fixed;background-size:cover;">
    <form method ="post" action="home">
<div class="text-block">
  <center><font size="20" face="calibri light" color="white" style="position:relative;top:60px;">LOGIN</font></center>
  <div class="container">
  
  <div class="t1">
     <div class="t11">
	 <i class="fa fa-envelope" style="font-size:24px;position:relative;left:25%;top:35%;color:red"></i>
	 </div>
	 <div class="t12">
	  <input type="email" name = "emailid"  style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" placeholder="e.g. mail@gmail.com" class="text-line" required>
	 </div>
  </div>
 
  <div class="t2">
     <div class="t21">
	 <i class="fa fa-key" style="font-size:24px;position:relative;left:25%;top:35%;color:red"></i>
	 </div>
	 <div class="t22">
	 <input type="password" name="password" style="font-size:24px;position:relative;left:0%;top:0%;width:100%;height:100%;" class="text-line" placeholder="e.g. password" required>
	  <button style="background-color:transparent;position:absolute;left:90%;top:0%;width:10%;height:100%;border: none;cursor: pointer;display: inline-block;" type="button"></button>
	 
	 </div>
  </div>
    <br><center><font size="5" face="calibri light" color="white" style="position:relative;top:60px;><a href="forgetPassword" style="color:red"><a href="forgetPassword" style="color:red">Forgot Password?</a></font></center>
   <button class="button1" type="submit">LOGIN</button><br><br>
   <center><font size="5" face="calibri light" color="white" style="position:relative;top:120px;">Haven't Registered<a href="signup" style="color:red"> Create Account</a></font></center>
   </div>
  </div>
</form>
</body>
</html>
<!-- pattern(for email)="/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/"-->