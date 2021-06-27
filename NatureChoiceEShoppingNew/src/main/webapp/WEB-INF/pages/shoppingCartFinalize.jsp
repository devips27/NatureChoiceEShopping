<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Nature Choice Organic Foods Online Shopping</title>
 <link rel="stylesheet" href="resources/styles.css" type="text/css">

</head>
<body>
   <jsp:include page="header.jsp" />
 
   <jsp:include page="menu.jsp" />
 
   <div class="page-title">Your Order is In-Progress !!! Will Update once order is ready through Mail.</div>
  
   <div class="final-container">
       <h3>Thank you for Shopping with us !!!</h3>
       Your order number is: ${lastOrderedCart.orderNum}
   </div>
 
    <jsp:include page="footer.jsp" />

</body>
</html>