<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parking View</title>
 <link rel="stylesheet" href="resources/styles.css" type="text/css">



</head>
<body>
 
   <jsp:include page="header.jsp" />
   <jsp:include page="menu.jsp" />
 
   <fmt:setLocale value="en_US" scope="session"/>
  <div class="parking-container">
   <div class="page-title">Customer Parking View</div>
 
   <table border="1" style="width:100%">
       <tr>
           <th>Registration Number</th>
           <th>Owner Name</th>
           <th>Parking Slot</th>
       </tr>
       <c:forEach items="${parkingInf.details}" var="parkingInf">
           <tr>
               <td>${parkingInf.reg_no}</td>
               <td>${parkingInf.owner_name}</td>
               <td>${parkingInf.slot}</td>
           </tr>
       </c:forEach>
      
   </table>
 </div>
 
    <jsp:include page="footer.jsp" />
 
</body>

</html>