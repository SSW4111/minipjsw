<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/wishlist.js"></script>
 
 <div class="container">
 	<div class="row mt-4">
 		<div class="col">
 			<h2 class="wishCount"></h2>
 		</div>
 	</div>
 	<div class="row justify-content-start mt-4" id="wishListContainer"></div>
 </div>



<div class="row mt-4">
	<div class="col text-center">
		<button class="btn more-btn">더보기</button>
	</div>
</div>
 

 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
