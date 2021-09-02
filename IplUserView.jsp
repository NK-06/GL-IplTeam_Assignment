<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import = "java.util.*, iplteam.PointTable, iplteam.Dao, iplteam.IplSummaryService" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Points Table</title>
<style>

tr:nth-child(even){
background-color:#dddddd;}

 a:hover{color : blue;
}
</style>

</head>
<body> 

<a href = "IplUpdateTable.jsp">Add Details</a>

<%
    List<PointTable> pt = Dao.table();
    request.setAttribute("pt", pt);
%>

<h3>IPL Points Table :</h3>
<form>

<table border = "1" width = "100%">
<tr>
 
   <th>Team_Name</th>
   <th>Total_Points</th>
</tr>

 <c:forEach items = "${pt}"  var = "p">
  <tr>
  
     <td ><a href = "IplSummaryService?teamId=${p.getTeamId()}"  title = "clich here to see summary">${p.getTeamName()}</a> </td>    
     <td>${p.getTotalPoints()} </td>
 
  </tr>   
 </c:forEach>

</table>

</form>

</body>
</html>