<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>OUTPUT</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="styles/css/style.css" rel="stylesheet">
<style>
	.tab3{
	color : #ffffff;
	}
	.tab2{
	color : #00ff00;
	}
</style>
</head>
<body background="styles/images/1.jpeg" style="background-position:center top;background-size:cover;">
	<a href="logout"><button class="b2" style="width:auto; top:5%; left:90%;" >Logout</button></a>
	<div class="first1">
		<h1 class="h1">OUTPUT METRICS</h1><br>
		<div class="f1" style = "top:10px;">
			<table>
			<tr style="background-color:#003366;color:white;">
				<th>Parameters/Approach</th>
				<th>Existing Approach</th>
				<th>New Approach</th>
			<tr>
			<tr>
				<td class = "tab3">Confusion Matrix</td>
				<td>
					<table  style = "margin-top: 5px; margin-bottom: 5px; width:80%;" align="center">
						<tr>
							<td>${predictionOutput.olderHeuristics.getConfusionMatrix()[0][0]}</td>
							<td>${predictionOutput.olderHeuristics.getConfusionMatrix()[0][1]}</td>
						</tr>
						<tr>
							<td>${predictionOutput.olderHeuristics.getConfusionMatrix()[1][0]}</td>
							<td>${predictionOutput.olderHeuristics.getConfusionMatrix()[1][1]}</td>
						</tr>
					</table>
				</td>
				<td>
					<table style = "margin-top: 5px; margin-bottom: 5px; width:80%;" align="center" >
						<tr>
							<td class = "tab2">${predictionOutput.newerHeuristics.getConfusionMatrix()[0][0]}</td>
							<td class = "tab2">${predictionOutput.newerHeuristics.getConfusionMatrix()[0][1]}</td>
						</tr>
						<tr>
							<td class = "tab2">${predictionOutput.newerHeuristics.getConfusionMatrix()[1][0]}</td>
							<td class = "tab2">${predictionOutput.newerHeuristics.getConfusionMatrix()[1][1]}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class = "tab3">Accuracy</td>
				<td>${predictionOutput.olderHeuristics.getAccuracyScore()}</td>
				<td  class = "tab2">${predictionOutput.newerHeuristics.getAccuracyScore()}</td>
			</tr>
			<tr>
				<td class = "tab3">Precision</td>
				<td>${predictionOutput.olderHeuristics.getPrecisionScore()}</td>
				<td  class = "tab2">${predictionOutput.newerHeuristics.getPrecisionScore()}</td>
			</tr>
			<tr>
				<td class = "tab3">Recall</td>
				<td>${predictionOutput.olderHeuristics.getRecallScore()}</td>
				<td  class = "tab2">${predictionOutput.newerHeuristics.getRecallScore()}</td>
			</tr>
			<tr>
				<td class = "tab3">F1-Score</td>
				<td>${predictionOutput.olderHeuristics.getF1_score()}</td>
				<td  class = "tab2">${predictionOutput.newerHeuristics.getF1_score()}</td>
			</tr>
			</table>
		</div>	
		<a href="incrementalSVM"><button style="margin-top:5px; width:auto; left:35%;" class="b4">Back</button></a>
		<a href="thanks"><button style="margin-top:5px; width:auto; left:65%;" class="b4">Exit</button></a>
		</div>
        
</body>
</html>