<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart Confirmation</title>
<link rel="stylesheet" href="resources/styles.css" type="text/css">

</head>
<body>
	<script>
function myFunction() {
  document.getElementById("date").required = true;
  document.getElementById("time").required = true;
  document.getElementById("demo").innerHTML = "The date and time field must now be filled out before Place an Order.";
}
</script>

  <jsp:include page="header.jsp" />
 
  <jsp:include page="menu.jsp" />
 
  <fmt:setLocale value="en_US" scope="session"/> 
 
  <div class="page-title">Confirmation</div>
 
 
 
  <div class="customer-info-container">
      <h3>Customer Information:</h3>
      <ul>
          <li>Name: ${myCart.customerInfo.name}</li>
          <li>Email: ${myCart.customerInfo.email}</li>
          <li>Phone: ${myCart.customerInfo.phone}</li>
          <li>Address: ${myCart.customerInfo.address}</li>
          <li>Payment Method: ${myCart.customerInfo.paymentMethod}</li>
      </ul>
      <h3>Cart Summary:</h3>
      <ul>
          <li>Quantity: ${myCart.quantityTotal}</li>
          <li>Total:
          <span class="total">
            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
          </span></li>
      </ul>
  </div>
  <div>
  	<table>
      	<tr><td></td></tr>
    </table>
  </div>
  <div class="customer-info-container">
     <form method="POST" action="${pageContext.request.contextPath}/shoppingCartConfirmation">
      <h3>Reserve Date and Time-Slot for Pickup :</h3>
      <table>
      	<tr>
      	<td>Date *</td>
      	<td> <input type="date" id="date" name="date"></td>
      	</tr>
      	
      	<tr>
      	<td>Time *</td>
      	<td><input type="time" id="time" name="time"></td>
      	</tr>
      	
      	 <!-- <tr>
               <td>&nbsp;</td>
               <td> <button onclick="myFunction()">Add</button></td>
           </tr> -->
      </table>
      <p id="demo"></p>
      
      <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
          <div class="product-preview-container">
              <ul>
                  <li><img class="product-image"
                      src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}" /></li>
                  <li>Code: ${cartLineInfo.productInfo.code} <input
                      type="hidden" name="code" value="${cartLineInfo.productInfo.code}" />
                  </li>
                  <li>Name: ${cartLineInfo.productInfo.name}</li>
                  <li>Price: <span class="price">
                     <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/>
                  </span>
                  </li>
                  <li>Quantity: ${cartLineInfo.quantity}</li>
                  <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                    </span>
                  </li>
              </ul>
          </div>
      </c:forEach>
 `<br>
      <!-- Edit Cart -->
      <a  class="navi-item" 
          href="${pageContext.request.contextPath}/shoppingCart" style="color:yellow">Edit Cart</a>
 
      <!-- Edit Customer Info -->
      <a class="navi-item"
          href="${pageContext.request.contextPath}/shoppingCartCustomer" style="color:yellow">Edit
          Customer Info</a>
 
      <!-- Send/Save -->
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button onclick="myFunction()">Place Your Order</button>
  </form>
  </div>
 
  
 <jsp:include page="footer.jsp" />
 
</body>
</html>