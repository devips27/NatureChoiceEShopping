<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Registration</title>

<link rel="stylesheet" href="resources/styles.css" type="text/css">
<script src="resources/register.js" type="text/javascript" ></script> 

</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="menu.jsp" />
 
<div class="page-title"> Registration Information </div>
  <div class="register-container">
   <form:form id="register" name="register" method="POST" action="${pageContext.request.contextPath}/registration" onsubmit="return validateform()">
       <table>
           <tr>
               <td>User Name *</td>
               <td><input type="text" placeholder="Enter User Name" name="name" id="name" required></td>
           </tr>
 
           <tr>
               <td>Password *</td>
               <td> <input type="password" placeholder="Enter Password" name="psw" id="psw" required></td>
           </tr>
           <tr>
               <td>Confirm Password *</td>
               <td> <input type="password" placeholder="Re-enter Password" name="cpass" id="cpass" required></td>
           </tr>
 
           <tr>
               <td>Role * </td>
               <td><input type="radio" id="role" name="role" value="USER" required>
               <label for="male">Customer</label></td>
               <td><input type="radio" id="role" name="role" value="ADMIN" required>
               <label for="male">Admin</label></td>
            </tr>
 
            <tr>
               <td>&nbsp;</td>
<!--                <button type="submit" value="Submit" id="button">Register</button> -->
               
<!--                <td> <input type="submit" value="Submit" />  -->
 				<td> <button type="submit" value="Submit" id="button">Register</button>

               <input type="reset" value="Reset" /></td>
           </tr>
       </table>
       <div class="container signin">
   				 <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a>.</p>
 			 </div>
   </form:form>
 </div>
 <jsp:include page="footer.jsp" />
</body>
</html>