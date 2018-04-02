<%@page import="assignment1.JPABean"%>
<%@page import="assignment1.AgeGroup"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Age Groups Details</title>
</head>
		<div class="topnav">
  <a class="active" href="/AssignmentOne/AgeGroupDataDisplay.jsp">AgeGroup</a>
  <a href="/AssignmentOne/GeographicalAreaClassificationList.jsp">GeoArea</a>
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
if(session.getAttribute("JPABean") != null){
	
	AgeGroup tempAgeGroup;
	JPABean jpaBean = (JPABean)session.getAttribute("JPABean");
	List<AgeGroup> tempList = jpaBean.getAllAgeGroups();
	
	for (AgeGroup listAG : tempList) {
		out.println("<tr>");
		out.println("<th>");
		out.println("<a href='./AgeGroupDataDisplay.jsp?id="+listAG.getAgeGroupID() + "'>" + 
				listAG.getDescription() +"</a>");
		out.println("</th>");
		out.println("</tr>");
	}
}
%>


</body>
</html>