<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = " iplteam.PointTable,java.util.*, iplteam.Dao" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Details</title>
</head>
<body>

<a href = "IplUserView.jsp">Home</a>
<br>
<br>
<%
    List<PointTable> pt = Dao.idName();
    request.setAttribute("pt", pt);
    %>
    
<table>

<form action = "IplUpdateService" method = "post">
 
<tr>
<td>1st Team :</td>
<td> <select name = 'teamId' style='width:70px'>
<c:forEach items = "${pt}"  var = "p">
      <option value = ${p.getTeamId()}>${p.getTeamName()}</option>     
    </c:forEach></tr>  
</select>
</td></tr>

<tr>
<td>1st Team Score:</td>
<td> <input type = 'number' name = 'ownScore'  min = 0 oninput = "validity.valid||(value= '');" required>
</td></tr>
<tr><td>

<tr>
<td>2nd Team :</td>
<td> <select name = 'oppId' style='width:70px'>
<c:forEach items = "${pt}"  var = "p">
      <option value = ${p.getTeamId()}>${p.getTeamName()}</option>     
    </c:forEach></tr>
</select>
<td></tr>
<tr>

<td>2nd Team Score:</td>
<td> <input type = 'number' name = 'oppScore'  min = 0 oninput = "validity.valid||(value= '');" required>
</td></tr>
<tr><td>
<input type = "submit" value = "submit">
</td></tr>

</form>
</table>
</body>
</html>