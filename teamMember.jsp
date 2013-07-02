<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <script src="helper.js" language="javascript"></script>
   <link rel = "stylesheet" type = "text/css" href = "main.css"/>
  <title>${member.name}'s Page </title>
</head>
<body>
<center>Welcome, ${member.name}</center>
<div id="taskList">
	<table class="placement">
	  <th>Task #</th>
	  <th>Description</th>
	  <th>Due Date</th>
	  <th>Progress</th>
  	<c:forEach items="${member.memberTasks}" var="task">
      <tr>
        <td>${task.id}</td>
        <td>${task.taskName}</td>
        <td>${task.taskDueDate}</td>
        <td id="progress">${task.progress}</td>
      </tr> 
    </c:forEach>
    </table>
  </div>
  
  <form action="updateProgress.do">
    <center><h2>Select task to update progress:</h2></center>
    <center><table>
      <tr><td>
        <select name="taskNum">
          <option></option>
          <c:forEach items="${member.memberTasks}" var="task">
            <option>${task.id}</option> 
          </c:forEach> 
        </select>
        <td>
        <select name="progress">
    	    <c:forEach var="i" begin="0" end="100" step="5">
         	 <option>${i}</option>
        	</c:forEach>
      	  </select></td>
      </td></tr>
    </table>
    <input type="submit" value="Update Tasks" /></center>
  </form>
  
  <form action="logout.do">
      <center><input type="submit" value="Logout"/></center>
    </form>

</body>
</html>