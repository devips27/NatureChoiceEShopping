<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Account Info</title>

<link rel="stylesheet" href="resources/styles.css" type="text/css">
 

</head>
<body>
 
 
   <jsp:include page="header.jsp" />
   <jsp:include page="menu.jsp" />
 
   <div class="page-title">User Info</div>
 
   <div class="account-container">
 
 
       <ul>
           <li>User Name: ${pageContext.request.userPrincipal.name}</li>
           <li>Welcome to Nature Choice Online Shopping!!!</li>
       </ul>
   </div>
 
 
   <jsp:include page="footer.jsp" /> 
 
</body>
</html>