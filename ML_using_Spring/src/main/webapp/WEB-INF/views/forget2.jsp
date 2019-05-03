<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Forget Password</title>
<link href="styles/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
	.t2 {
    position: relative;
    top: 0px;
    left: 30px;
	width:100%;
	height:90px;
  }
  .t21 {
    position: relative;
    top: 0px;
    left: 30px;
	width:60px;
	height:130px;
}
.t66 {
    position: absolute;
    top: 112px;
    left: 60px;
	width:520px;
	height:100%;
	
    
}
</style>
</head>
<body background="styles/images/17.jpg" style="background-position:center top">
<div class="text-block">
	<form method="post" action="forgetPassword3">
    <br><br>
	<p><center><font size="30" face="calibri light" color="white" style="position:relative;top:60px;">Forgot Password</font></center>
	<p><center><font size="20" face="calibri light" color="white" style="position:relative;top:60px;">Dont Worry!!!</font></center>
	<div class="t2">
     <div class="t21">
	 <i class="fa fa-key" style="font-size:24px;position:relative;left:25%;top:100%;color:red"></i>
	 </div>
	 <div class="t66">
	 <div><input type="password" name="password" id="password" style="font-size:24px;position:relative;left:5%;top:100%;width:100%;height:100%;" placeholder="enter new password" class="text-line" required></div>
	  </div>
	 </div><br><br>
	<button class="button1" type="submit">Continue</button>
	
	</form>
</div>
</body>
</html>