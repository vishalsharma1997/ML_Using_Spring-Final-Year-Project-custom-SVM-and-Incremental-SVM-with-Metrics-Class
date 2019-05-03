<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incremental SVM</title>
<link href="styles/css/style.css" rel="stylesheet">
<script>
	function displayProgress(){
		var progress = document.getElementById("displayProgressBar");
		progress.style.display="block";
		document.getElementById("backbtn").disabled = true;
		document.getElementById("predictAndDisplay").disabled = true;
	}

</script>
<style>
.meter { 
			height: 20px;  /* Can be anything */
			margin-left:50px;
			margin-right:50px;
			position: relative;
			background: #555;
			-moz-border-radius: 25px;
			-webkit-border-radius: 25px;
			border-radius: 25px;
			padding: 10px;
			-webkit-box-shadow: inset 0 -1px 1px rgba(255,255,255,0.3);
			-moz-box-shadow   : inset 0 -1px 1px rgba(255,255,255,0.3);
			box-shadow        : inset 0 -1px 1px rgba(255,255,255,0.3);
		}
		.meter > span {
			display: block;
			height: 100%;
			   -webkit-border-top-right-radius: 8px;
			-webkit-border-bottom-right-radius: 8px;
			       -moz-border-radius-topright: 8px;
			    -moz-border-radius-bottomright: 8px;
			           border-top-right-radius: 8px;
			        border-bottom-right-radius: 8px;
			    -webkit-border-top-left-radius: 20px;
			 -webkit-border-bottom-left-radius: 20px;
			        -moz-border-radius-topleft: 20px;
			     -moz-border-radius-bottomleft: 20px;
			            border-top-left-radius: 20px;
			         border-bottom-left-radius: 20px;
			background-color: rgb(43,194,83);
			background-image: -webkit-gradient(
			  linear,
			  left bottom,
			  left top,
			  color-stop(0, rgb(43,194,83)),
			  color-stop(1, rgb(84,240,84))
			 );
			background-image: -moz-linear-gradient(
			  center bottom,
			  rgb(43,194,83) 37%,
			  rgb(84,240,84) 69%
			 );
			-webkit-box-shadow: 
			  inset 0 2px 9px  rgba(255,255,255,0.3),
			  inset 0 -2px 6px rgba(0,0,0,0.4);
			-moz-box-shadow: 
			  inset 0 2px 9px  rgba(255,255,255,0.3),
			  inset 0 -2px 6px rgba(0,0,0,0.4);
			box-shadow: 
			  inset 0 2px 9px  rgba(255,255,255,0.3),
			  inset 0 -2px 6px rgba(0,0,0,0.4);
			position: relative;
			overflow: hidden;
		}
		.meter > span:after, .animate > span > span {
			content: "";
			position: absolute;
			top: 0; left: 0; bottom: 0; right: 0;
			background-image: 
			   -webkit-gradient(linear, 0 0, 100% 100%, 
			      color-stop(.25, rgba(255, 255, 255, .2)), 
			      color-stop(.25, transparent), color-stop(.5, transparent), 
			      color-stop(.5, rgba(255, 255, 255, .2)), 
			      color-stop(.75, rgba(255, 255, 255, .2)), 
			      color-stop(.75, transparent), to(transparent)
			   );
			background-image: 
				-moz-linear-gradient(
				  -45deg, 
			      rgba(255, 255, 255, .2) 25%, 
			      transparent 25%, 
			      transparent 50%, 
			      rgba(255, 255, 255, .2) 50%, 
			      rgba(255, 255, 255, .2) 75%, 
			      transparent 75%, 
			      transparent
			   );
			z-index: 1;
			-webkit-background-size: 50px 50px;
			-moz-background-size: 50px 50px;
			-webkit-animation: move 2s linear infinite;
			   -webkit-border-top-right-radius: 8px;
			-webkit-border-bottom-right-radius: 8px;
			       -moz-border-radius-topright: 8px;
			    -moz-border-radius-bottomright: 8px;
			           border-top-right-radius: 8px;
			        border-bottom-right-radius: 8px;
			    -webkit-border-top-left-radius: 20px;
			 -webkit-border-bottom-left-radius: 20px;
			        -moz-border-radius-topleft: 20px;
			     -moz-border-radius-bottomleft: 20px;
			            border-top-left-radius: 20px;
			         border-bottom-left-radius: 20px;
			overflow: hidden;
		}
		
		.animate > span:after {
			display: none;
		}
		
		@-webkit-keyframes move {
		    0% {
		       background-position: 0 0;
		    }
		    100% {
		       background-position: 50px 50px;
		    }
		}
.input1{
	padding:8px;
	font-weight:bold;
	font-size:15px;
	
	border:2px solid teal;
	width:20%
}

</style>
</head>
<body background="styles/images/19.jpg" style="background-position:center top;background-size:cover;">
	<a href="logout"><button class="b2" style="width:auto; top:5%; left:90%;" >Logout</button></a>
	<div class="first">
		<h1 style="background-color:crimson ;color:white;height:50px;font-size:35px;text-align:center;">Predicting Data using Incremental SVM Classifier</h1><br>
		<div style = "display:none" id="displayProgressBar">
			<span style="font-size:20px; margin-left:55px; position:absolute; top:70px; color:white;">Training...</span>
			<div class="meter animate">
				<span style="width: 100%"><span style="color:black; font-size:19px;"><center>Training</center></span></span>
			</div>
		</div>
		<br><p style="color:white;font-size:25px;font-family: 'Lobster', helvetica, arial;text-align:center;">In this algorithm, knowledge is accumulated in the process 
		of incremental learning. In addition, unimportant samples are discarded optimally by a least-recently used (LRU) scheme. 
		Theoretical analyses and experimental results showed that this algorithm could not only speed up the training process, 
		but it could also reduce the storage costs, while the classification precision is also guaranteed.</p>
		<form action = "incrementalSVMtrain" method="post" onSubmit="displayProgress()">
			<br><br><p style="text-align:center;"><label style="color:goldenrod;font-size:25px;font-family: 'Lobster', helvetica, arial;">Enter Train Test Ratio:</label>
				<input type="text" class="input1" name = "trainTestRatio" id = "trainTestRatio" placeholder = "e.g. 0.85" required></p>
			<p style="text-align:center;"><label style="color:goldenrod;font-size:25px;font-family: 'Lobster', helvetica, arial;">Enter No. of Subsets:</label>
				<input type="text" class="input1" name = "noOfSubsets" id = "noOfSubsets" placeholder = "e.g. 3" required></p>
			<button type ="submit" style="width:auto;top:87%;left:20%;" class="b1">Train</button>
		</form>
        <a href="incrementalSVMOutput"><button id="predictAndDisplay" style="width:auto;top:87%;left:50%;" class="b2">Predict And Display Output</button></a>
		<a href="home"><button id="backbtn" style="width:auto;top:87%;left:80%;" class="b1">Back</button></a>
		</div>
        
</body>
</html>
