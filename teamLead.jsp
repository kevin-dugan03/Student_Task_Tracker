<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <script src="helper.js" language="javascript"></script>
  <link rel = "stylesheet" type = "text/css" href = "main.css"/>
<title>Team Lead Page</title>
</head>
<body>
<div id="main">
<center><h1>Final Exam Project - COMP 321</h1></center>
<br>
<form action="updateProject.do">
<table class="header">

  <tr>
    <td><b>Project Name:</b></td>
    <td id="projectTitle">${project.projectName}</td>
    <td><a href="javascript:changeTitle()">edit</a></td>
  </tr>
  
  <tr>
    <td><b>Due Date:</b> </td>
    <td id="projectDate">${project.projectDueDate}</td>
    <td><a href="javascript:changeDate()">edit</a></td>
  </tr>
</table>

<fieldset class="placement">
<legend>Project Tasks</legend>
  <div>
	<table class="center">
	  <th>Task #</th>
	  <th>Description</th>
	  <th>Assigned To</th>
	  <th>Due Date</th>
	  <th>Progress</th>
  	<c:forEach items="${project.tasks}" var="task">
      <tr>
        <td><center>${task.id}</center></td>
        <td><center>${task.taskName}</center></td>
        <td><center>${task.assignedName}</center></td>
        <td><center>${task.taskDueDate}</center></td>
        <td><center>${task.progress}</center></td>
      </tr> 
    </c:forEach>
    </table>
  </div>
</fieldset>

<fieldset class = "placement">
<legend><center>Add a Task:</center></legend>
  <div id="addTask">
	<table class = "center">
  		<tr>
   		 <td>Task Name:</td>
   		 <td><input type="text" name="taskDescription"/></td>
  		</tr>
  		
 		 <tr>
  		  <td>Assigned To:</td>
  		  <td>
   		   <select name="assignedName">
   		     <option></option>
    	    <c:forEach items="${project.team}" var="member">
         	 <option>${member.name}</option>
        	</c:forEach>
      	  </select>
          </td>
        </tr>
        
        <tr>
          <td>Due Date:</td>
          <td><input type="text" name="taskDate"/></td>
        </tr>
        
        <tr>
          <td>Progress %:</td>
          <td>
          <select name="progress">
    	    <c:forEach var="i" begin="0" end="100" step="5">
         	 <option>${i}</option>
        	</c:forEach>
      	  </select></td>
        </tr>
        
        <tr><td colspan="2"></td></tr>
        <tr><td>${addTaskMessage}</td></tr>
	</table>
</fieldset>
  </div>
  <center><input type="submit" value="Save Changes" /></center>
  </form>
  <form action="logout.do">
    <center><input type="submit" value="Logout"/></center>
  </form>
  
  
   <div class="placement">
   <table>
   <th>Name</th>
   <th>Position</th>
    <c:forEach items="${project.team}" var="member" >
      <tr><td>${member.name}</td>
        <td>${member.position }</td></tr>
          </c:forEach>
  </table>
  </div>
</div>


</body>
</html>