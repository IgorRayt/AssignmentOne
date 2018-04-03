<%@page import="assignment1.JPABean"%>
<%@page import="assignment1.AgeGroup"%>
<%@page import="assignment1.Age"%>
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


<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Iterator"%>

<% 
if(session.getAttribute("JPABean") != null){
	
	//AgeGroup tempAgeGroup;
	JPABean jpaBean = (JPABean)session.getAttribute("JPABean");
	List<Object[]> tempList = jpaBean.getAllAgeGroups();
	Iterator <Object[]> tempListIterator = tempList.iterator();
	List <String> tempOutputTable = new ArrayList <String>();
	
	while(tempListIterator.hasNext()){
	Object[] tempResultSet = tempListIterator.next();
    String tempAgeGroup = (String) tempResultSet[0];
    String tempMale = (String) tempResultSet[1];
    long tempFemale = (long) tempResultSet[2];
    	
    tempOutputTable.add(String.valueOf(tempAgeGroup));
    tempOutputTable.add(String.valueOf(tempMale));
    tempOutputTable.add(String.valueOf(tempFemale));

	}
	out.println("Age Group Data");
	out.println("<tr>");
	out.println("<th>Description</th>");
	out.println("<th>Male</th>");
	out.println("<th>Female</th>");
	out.println("</tr>");
	out.println("<tr>");
	for(String tmp : tempOutputTable){
	
	out.println("<th>");
	out.println(tmp);
	out.println("</th>");
	out.println("</tr>");
	}
}
%>
</table>

</body>
</html>