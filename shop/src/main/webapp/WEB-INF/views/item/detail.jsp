<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/mendetail.js"></script>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<div class="container">
	<div class="row">
	   <!-- 왼쪽 이미지 영역 -->
	   <div class="col-md-6">
	     <input type="hidden" value="${itemDetailViewDto.itemNo}" id="itemNO">

	     <img id="preview-image" src="/attachment/download?attachmentNo=${attachList[0]}" style="width: 100%; max-width: 600px;">
	     <div class="d-flex mt-2">
	       <c:forEach var="attach" items="${attachList}">
	         <img class="thumbnail"
	              src="/attachment/download?attachmentNo=${attach}"
	              data-src="/attachment/download?attachmentNo=${attach}"
	              style="width: 80px; height: 80px; border: 1px solid grey; margin-right: 5px; cursor: pointer;">
	       </c:forEach>
	     </div>
	   </div>

	   <!-- 오른쪽 상품정보 영역 -->
	   <div class="col-md-6">
	     <h2>${itemDetailViewDto.itemTitle}</h2>
	     <div>
	       <span>${itemDetailViewDto.itemCategory}</span> |
	       <span>${itemDetailViewDto.itemDetail}</span> |
	       <span>${itemDetailViewDto.itemColor}</span>
	     </div>
	     <div class="mt-2" style="font-size: 0.9em;">
	       <span style="color: gold;">⭐</span>
	       <!--<span class="text-muted">${itemDetailViewDto.itemAveStar}</span>-->
		   <span>
		     <fmt:formatNumber value="${itemDetailViewDto.itemAveStar || '0.0'}" maxFractionDigits="2" />
		   </span>
	   		</div>
	     <h2 class="mt-3">${itemDetailViewDto.itemPrice}원</h2>

	     <!-- 색상 선택 -->
	     <div class="mt-4 d-flex">
	       <c:forEach var="itemDto" items="${colorList}" varStatus="status">
	         <a class="text-decoration-none me-2" href="/item/detail?itemNo=${itemDto.itemNo}">
	           <img src="/attachment/download?attachmentNo=${colorNoList[status.index]}"
	                style="width: 80px; height: 80px; border: 1px solid grey;">
	         </a>
	       </c:forEach>
	     </div>

	     <!-- 사이즈 선택 -->
	     <div class="mt-4" style="max-width: 200px;">
	       <select class="form-select" >
	         <option value="">사이즈 선택</option>
	         <c:forEach var="ioDto" items="${iolist}">
	           <option value="${ioDto.sizeName}">${ioDto.sizeName}, ${ioDto.itemIoTotal}개 재고</option>	 
	         </c:forEach>
	       </select>
	     </div>

	     <!-- 수량 선택 -->
	     <div class="mt-3" style="max-width: 150px;">
	       <select class="form-select" id="itemQty" name="itemQty">
	         <option value="">수량 선택</option>
	         <c:forEach var="i" begin="1" end="10">
	           <option value="${i}">${i}</option>
	         </c:forEach>
	       </select>
	     </div>

	     <!-- 버튼 -->
	     <div class="mt-4 d-flex gap-2">
	       <button class="btn btn-outline-secondary">주문하러가기</button>
	       <button class="btn btn-outline-secondary cart-button">장바구니</button>
	     </div>
	   </div>
	 </div>

	



 


<!--         내용              -->
<div class="row mt-4">
				<div class="col">
					<span>${itemDetailViewDto.itemContent }</span>
										
				</div> 
			</div>
</div>

<div class="container">
	<div class="row mt-4">
		<div class="col" id="reviewsList">

			<c:forEach var="reviewsDto" items="${list}">
				<span>${list.reviewsNo }</span>
				<span>${list.reviewsTitle }</span>
				<span>${list.reviewsContent }</span>
				<span>${list.reviewsWtime }</span>
				<span>${list.ItemTitle }</span>
				<span>${list.usersNickname }</span>
				<span>${list.usersEmail }</span>
				
			</c:forEach> 

		</div>
	</div>
</div>

<hr>
<div class="container write-box">
	<div class="row mt-4">
		<div class="col">
			<input type="text" class="form-control" name="reviewsTitle"
				placeholder="제목 입력">
		</div>
	</div>
	<div class="row mt-4">
		<div class="col">
			<textarea class="form-control" rows="3" name="reviewsContent"
				placeholder="내용입력"></textarea>
		</div>
	</div>
	<div class="row mt-4">
		<div class="col">
			<input type="text" class="form-control" name="reviewsStar"
				placeholder="만족도조사">
		</div>
	</div>
	<div class="row mt-4">
		<div class="col d-flex">
			<div class="ms-auto">
				<button class="btn btn-success ms-auto write-button">작성</button>
				<%-- <input type="hidden" name="usersEmail" value="${ }"> --%>
			</div>
		</div>
	</div>
</div>


<%@ page session="true"%>
<%
String usersEmail = (String) session.getAttribute("usersEmail");
String usersLevel = (String) session.getAttribute("usersLevel");
%>

<p>
	현재 로그인된 이메일:
	<%=usersEmail%></p>
<p>
	사용자 레벨:
	<%=usersLevel%></p>
<hr>
"${itemDetailViewDto}"








<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>