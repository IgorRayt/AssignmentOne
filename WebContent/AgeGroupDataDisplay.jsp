<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Age Groups Details</title>
</head>
	<div class="topnav">
  <a class="active" href="/AssignmentOne/AreaClassificationDisplay.jsp">Area Classification</a>
  <a href="/AssignmentOne/AgeGroupDataServlet">Age Group Data</a>
  <a href="/AssignmentOne/GeographicalAreaDisplay.jsp">Area Detail</a>
<body>
	<h3>The age group details</h3>
	<table style="width:100%">
  		<tr>
    		<th>Description</th>
    		<th>Male Number</th> 
    		<th>Female Number</th>
  		</tr>

<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<% 
if(session.getAttribute("ageGroupsData") != null){

	ArrayList<ArrayList> list = (ArrayList<ArrayList>) session.getAttribute("ageGroupsData");

	for (ArrayList<String> data : list) {
		out.println("<tr>");
		for(String message : data){
			out.println("<th>");
			out.println(message);
			out.println("</th>");
		}
		out.println("</tr>");
	}
}
%>


</body>
</html>