<%@page import="assignment1.JPABean"%>
<%@page import="assignment1.GeographicArea"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Geographic Area Classification List</title>
</head>
<body>
	<h3>Geographic Area Classification List</h3>
	<table style="width:100%">
  		<tr>
    		<th>Geographic Area Name</th>
  		</tr>

<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.List"%>

<% 
if(session.getAttribute("JPABean") != null){
	
	GeographicArea tempGeographicArea;
	JPABean jpaBean = (JPABean)session.getAttribute("JPABean");
	List<GeographicArea> tempList = jpaBean.getAllGeographicAreas();
	
	for (GeographicArea listGA : tempList) {
		out.println("<tr>");
		out.println("<th>");
		out.println("<a href='./IndividualGeographicAreaDetails.jsp?id="+listGA.getGeographicAreaID() + "'>" + 
				listGA.getName() +"</a>");
		out.println("</th>");
		out.println("</tr>");
	}
}
%>


</body>
</html>