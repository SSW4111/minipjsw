<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/wishlist.js"></script>
 
 <div class="container">
 	<div class="row mt-4">
 		<div class="col d-flex">
 			<div class="ms-auto">
 				<span >총 갯수:</span>
 			<span class="wishCount ms-auto"></span>
 			</div>
 		</div>
 	</div>
 	<div class="row justify-content-start mt-4" >
 		<div class="col">
 			<div  id="wishListContainer"></div>
 		</div>
 	</div>
 </div>



<div class="row mt-4">
	<div class="col text-center">
		<button class="btn more-btn">더보기</button>
	</div>
</div>

<div class="container">
<div class="row mt-4">
	<div class="col d-flex">
		<!--  <a class="btn btn-success ms-auto"></a>-->
	</div>
</div>
 </div>

 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
