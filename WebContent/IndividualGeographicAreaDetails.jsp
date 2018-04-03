<%@page import="assignment1.JPABean"%>
<%@page import="assignment1.GeographicArea"%>
<%@page import="assignment1.Age"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Individual Geographic Area Details</title>
</head>
<body>
	<h3>Individual Geographic Area Details</h3>
	<table style="width:100%">
  		<tr>
    		<th>Geographic Area Name</th>
  		</tr>

<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="java.util.Iterator"%>

<% 
int id = Integer.parseInt(request.getParameter("id"));
if(session.getAttribute("JPABean") != null){
	
	JPABean jpaBean = (JPABean)session.getAttribute("JPABean");
	List<Object[]> tempList = jpaBean.getIndividualGeoAreaDetail(id);
	Iterator <Object[]> tempListIterator = tempList.iterator();
	if(id >= 2){
	    List <String> tempOutputTable2011 = new ArrayList <String>();
	    List <String> tempOutputTable2016 = new ArrayList <String>();
		while(tempListIterator.hasNext()){
			Object[] tempResultSet = tempListIterator.next();
			GeographicArea geographicAreaTemp2011 = (GeographicArea) tempResultSet[0];
	    	Age ageTemp2011 = (Age) tempResultSet[1];
	    	GeographicArea geographicAreaTemp2016 = (GeographicArea) tempResultSet[2];
	    	Age ageTemp2016 = (Age) tempResultSet[3];
	    	
	    	tempOutputTable2011.add(geographicAreaTemp2011.getName());
	    	tempOutputTable2011.add(String.valueOf(geographicAreaTemp2011.getCode()));
	    	tempOutputTable2011.add(String.valueOf(geographicAreaTemp2011.getLevel()));
	    	tempOutputTable2011.add(String.valueOf(geographicAreaTemp2011.getAlternativeCode()));
	    	tempOutputTable2011.add(String.valueOf(ageTemp2011.getMale()));
	    	tempOutputTable2011.add(String.valueOf(ageTemp2011.getFemale()));
	    	tempOutputTable2011.add(String.valueOf(ageTemp2011.getCombined()));

	    	tempOutputTable2016.add(geographicAreaTemp2016.getName());
	    	tempOutputTable2016.add(String.valueOf(geographicAreaTemp2016.getCode()));
	    	tempOutputTable2016.add(String.valueOf(geographicAreaTemp2016.getLevel()));
	    	tempOutputTable2016.add(String.valueOf(geographicAreaTemp2016.getAlternativeCode()));
	    	tempOutputTable2016.add(String.valueOf(ageTemp2016.getMale()));
	    	tempOutputTable2016.add(String.valueOf(ageTemp2016.getFemale()));
	    	tempOutputTable2016.add(String.valueOf(ageTemp2016.getCombined()));
		}
		out.println("Census Year 2011");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Code</th>");
		out.println("<th>Level</th>");
		out.println("<th>Alternative Code</th>");
		out.println("<th>Combined</th>");
		out.println("<th>Male</th>");
		out.println("<th>Female</th>");
		out.println("</tr>");
		out.println("<tr>");
		for(String tmp : tempOutputTable2011){
			out.println("<th>");
			out.println(tmp);
			out.println("</th>");
		}
		out.println("</tr>");
		out.println("Census Year 2016");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Code</th>");
		out.println("<th>Level</th>");
		out.println("<th>Alternative Code</th>");
		out.println("<th>Combined</th>");
		out.println("<th>Male</th>");
		out.println("<th>Female</th>");
		out.println("</tr>");
		out.println("<tr>");
		for(String tmp : tempOutputTable2016){
			out.println("<th>");
			out.println(tmp);
			out.println("</th>");
		}
		out.println("</tr>");
	}
	else{
		while(tempListIterator.hasNext()){
			Object[] tempResultSet = tempListIterator.next();
			GeographicArea geographicAreaTemp = (GeographicArea) tempResultSet[0];
	    	Age ageTemp = (Age) tempResultSet[1];
		}
	}
	
}

%>
</table>

</body>
</html>