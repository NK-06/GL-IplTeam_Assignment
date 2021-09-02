<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import = "java.util.*, iplteam.SummaryTable, iplteam.Dao, java.util.stream.Collectors" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Summary</title>

<style>

tr:nth-child(even){
background-color:#dddddd;}

</style>

</head>
<body>
<a href = "IplUserView.jsp">Home</a>
<% 
    /*
     String id = request.getParameter("teamId");
     List<SummaryTable> st = Dao.summary();
     List <SummaryTable> sf = st.stream().filter( x -> x.getTeamId().equalsIgnoreCase(id)).collect(Collectors.toList());
     request.setAttribute("sf", sf);
     String name = sf.get(0).getTeam();
     request.setAttribute("name", name);
     */
%>
<h3>Summary of ${name} : </h3>
<table border = "1" width = "100%">
<tr>
 
   <th>Team_Name</th>
   <th>Opp_Team</th>
   <th>Own_Score</th>
   <th>Opp_Score</th>
</tr>
<tr>
<td>

</tr>
   <c:forEach items = "${sf}"  var = "s">
  <tr>
    
     <td>${s.getTeam()}</td>
     <td>${s.getOppTeam()} </td>
     <td>${s.getOwnScore()}</td>
     <td>${s.getOppScore()} </td> 
  </tr>   
 </c:forEach>

</table>


</body>
</html>