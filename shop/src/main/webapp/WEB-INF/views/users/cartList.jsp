<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/cartList.js"></script>
 
 <div class="container">
 	<div class="row mt-4">
 		<div class="col d-flex">
			<h2>🛒장바구니</h2>
 			<div class="ms-auto">
 				<span >총 갯수:</span>
 			<span class="wishCount ms-auto"></span>
 			</div>
 		</div>
 	</div>
	
	<div class="row mt-4">
		<div class="col d-flex ">
			<button class="btn btn-danger ms-auto delete-btn" type="button"><i class="fa fa-trash"></i></button>
		</div>
	</div>
	
	<form method="get" action="/order/selectList-cart" id="cartForm">
		
	<div class="row mt-4">
		<div class="col d-flex ">
			<button type="button" class="btn btn-outline-secondary ms-auto order-btn">주문하기</button>
		</div>
	</div>
		
 	<div class="row justify-content-start mt-4" >
 		<div class="col">
 			<div  id="cartListContainer"></div>
 		</div>
 	</div>
 </div>

	


<div class="row mt-4">
	<div class="col text-center">
		<button class="btn more-btn">더보기</button>
	</div>
</div>
</form>

 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
