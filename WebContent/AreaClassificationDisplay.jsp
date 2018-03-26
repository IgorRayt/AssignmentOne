<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Area Classifications</title>
</head>
<body>
	<h3>Please enter level id (Can be 0,1,2, or 3) to get areas in this level</h3>
	<div class="topnav">
  <a class="active" href="/AssignmentOne/AreaClassificationDisplay.jsp">Area Classification</a>
  <a href="/AssignmentOne/AgeGroupDataServlet">Age Group Data</a>
  <a href="/AssignmentOne/GeographicalAreaDisplay.jsp">Area Detail</a>

</div>
	<form action='./AreaClassificationServlet' method='POST'>
        <input type='text' name='areaId'>
    	<input type='submit' value='Enter'>
	</form>
	<table style="width:100%">
  		<tr>
    		<th>Name</th>
  		</tr>
  		<tr>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<% 
if(session.getAttribute("levelData") != null){
	ArrayList<String> list = (ArrayList<String>) session.getAttribute("levelData");
	for (String temp : list) {
		out.println("<tr>");
		out.println("<th>");
		out.println(temp);
		out.println("</th>");
		out.println("</tr>");
	}
}
%>
  		</tr>
  		</table>
</body>
</html>