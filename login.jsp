<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
  <c:if test="${message != null }">
    <h3 style="color:red"><u>****${message }****</u></h3>
  </c:if>
  
  <center><h3 style="color:red">Login</h3></center>
  <form action="j_security_check" method="post">
  	<center><table class="placement">
  	  <tr>
  	    <td>Username:</td>
  	    <td><input type="text" name="j_username" /></td>
  	  </tr>
  	  <tr>
  	    <td>Password:</td>
  	    <td><input type="password" name="j_password" /></td>
  	  </tr>
  	  <tr> 
  	    <td colspan="2" style="text-align: center"><input type="submit" value="Login" name="j_security_check" /></td>
  	  </tr>
  	</table></center>
  </form>