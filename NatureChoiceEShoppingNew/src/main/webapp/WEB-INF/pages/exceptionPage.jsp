<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/styles.css" type="text/css">
<title>Exception Page</title>
</head>
<body>
	<jsp:include page="header.jsp" />
   <jsp:include page="menu.jsp" />
 <h3 style="color: red">Oops!! Something went wrong!</h3>
 <h5 style="color: red">${name}</h5><br>
 <h5 style="color: red">${message}</h5>
</body>
</html>