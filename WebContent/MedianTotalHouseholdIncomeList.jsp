<%@page import="assignment1.JPABean"%>
<%@page import="assignment1.GeographicArea"%>
<%@page import="assignment1.HouseHold"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Median Total Household Income List</title>
</head>
<body>
	<h3>Median Total Household Income List</h3>
	<table style="width:100%">
  		<tr>
    		<th>Geographic Area Name</th>
  		</tr>

<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>>
<%@ page import ="java.util.Iterator"%>

<% 
if(session.getAttribute("JPABean") != null){
	
	JPABean jpaBean = (JPABean)session.getAttribute("JPABean");
	List<Object[]> tempList = jpaBean.getMedianTotalHousehold();
	Iterator <Object[]> tempListIterator = tempList.iterator();
	List <String> tempOutputTable = new ArrayList <String>();
	while(tempListIterator.hasNext()){
		Object[] tempResultSet = tempListIterator.next();
		GeographicArea geographicAreaTemp = (GeographicArea) tempResultSet[0];
    	HouseHold houseHoldTemp = (HouseHold) tempResultSet[1];
    	tempOutputTable.add(geographicAreaTemp.getName());
    	tempOutputTable.add(String.valueOf(houseHoldTemp.getNumberReported()));

	}
	for (String tmp : tempOutputTable) {
		out.println("<tr>");
		out.println("<th>");
		out.println(tmp);
		out.println("</th>");
		out.println("</tr>");
	}
}
%>


</body>
</html>