<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="styles/css/style.css" rel="stylesheet">
<style>
h1 {
	font-family: 'Lobster', helvetica, arial;
	text-align:center;
	font-size:60px;
	text-shadow: 0 0 10px #fff,
		0 0 20px #fff,
	0 0 30px #fff,
	0 0 40px #ff00de,
	0 0 70px #ff00de,
	0 0 80px #ff00de,
	0 0 100px #ff00de,
	0 0 150px #ff00de;
}
</style>

</head>
<body background="styles/images/22.png" style="background-position:center top;background-size:cover;">
	<a href="logout"><button class="b2" style="width:auto; top:5%; left:90%;" >Logout</button></a>
	<div class="first" style="top:70px;opacity:0.9;background-color:smoke">
	
		<br><br><br><br><br><br><br><h1>Welcome ${username},</h1>
		<a href="customSVM"><button style="width:auto;" class="b1">Predict Data Using Custom SVM</button></a>
        <a href="incrementalSVM"><button style="width:auto;" class="b2">Using Incremental SVM Classifier</button></a>
         
	</div>
</body>
</html>