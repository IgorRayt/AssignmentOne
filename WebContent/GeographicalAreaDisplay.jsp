<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Geographical Area Details</title>
    </head>
    	<div class="topnav">
  <a class="active" href="/AssignmentOne/AreaClassificationDisplay.jsp">Area Classification</a>
  <a href="/AssignmentOne/AgeGroupDataServlet">Age Group Data</a>
  <a href="/AssignmentOne/GeographicalAreaDisplay.jsp">Area Detail</a>
    <body>
    	Please enter the geographical area id
		<form action='./GeographicalAreaDetailsServlet' method='POST'>
        	<input type='text' name='areaId'>
            <input type='submit' value='Enter'>
		</form>
		<table style="width:100%">
  			<tr>
    			<th>Name</th>
    			<th>Level</th> 
    			<th>Code</th>
    			<th>Combined</th>
  			</tr>
  			<tr>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<%
if(session.getAttribute("areaDetails") != null){
	ArrayList<String> data = (ArrayList<String>) session.getAttribute("areaDetails");
	for (String message : data) {
		out.println("<th>");
		out.println(message);
		out.println("</th>");
	}
	out.println("</table>");
}

if(session.getAttribute("listOfDependencies") != null){
	out.println("<br>");
	out.println("Dependencies:");
	out.println("<table>");
	out.println("<tr>");
	out.println("<th>");
	out.println("Name");
	out.println("</tr>");
	out.println("</th>");

	ArrayList<String> list = (ArrayList<String>) session.getAttribute("listOfDependencies");

	for(String message : list){
		out.println("<tr>");
		out.println("<th>");
		out.println(message);
		out.println("</th>");
		out.println("</tr>");
	}
	
	out.println("</table>");
}
%>
  			</tr>
  		</table>
    </body>
</html>



