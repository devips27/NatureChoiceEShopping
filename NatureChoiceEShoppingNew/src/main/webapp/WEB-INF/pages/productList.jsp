<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
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
  
    <fmt:setLocale value="en_US" scope="session"/> 
 
   <c:forEach items="${paginationProducts.list}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
               <li><img class="product-image"
                   src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" /></li>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
               <li><a style="color:yellow;"
                   href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
                       Buy Now</a></li>
               <!-- For Admin edit Product -->
               <security:authorize  access="hasRole('ROLE_ADMIN')">
                 <li><a style="color:red;"
                     href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">
                       Edit Product</a></li>
               </security:authorize>
                <!-- For Admin delete Product -->
               <security:authorize  access="hasRole('ROLE_ADMIN')">
                 <li><a style="color:red;"
                     href="${pageContext.request.contextPath}/productDelete?code=${prodInfo.code}">
                       Delete Product</a></li>
               </security:authorize>
           </ul>
       </div>
 
   </c:forEach>
   <br/>
  
 
   <c:if test="${paginationProducts.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationProducts.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="productList?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
          
       </div>
   </c:if>
 
    <jsp:include page="footer.jsp" />

</body>
</html>