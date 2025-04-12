<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/mendetail.js"></script>



<div class="container">
	<div class="row mt-4">
		<div class="col-sm-6">

	<input type="hidden" value = "${itemDetailViewDto.itemNo}" id="itemNO">
			
			<%-- <img src = "/attachment/download?attachmentNo=${attachmentNo }" style="width:450px"> --%>
			<img src="http://placehold.co/450" style="width: 600px;">
		</div>

	
		<div class="col-sm-6 ">
		<div class="row mt-4">
			<div class="col">
				<div style="min-height: 180px; background-color:aqua;"></div>
			</div>
		</div>
		
			<div class="row mt-4">
			<div class="col">
				
				<h2 class="text-danger">"${itemDetailViewDto.itemTitle}"</h2>
				<span>${itemDetailViewDto.itemCategory }</span>
				<span>${itemDetailViewDto.itemDetail }</span>
				<span>${itemDetailViewDto.itemAveStar }</span>
				<h2>${itemDetailViewDto.itemPrice }</h2>
			</div>
		</div>
		
		<div class="row mt-4">
			<div class="col">
			
			<!-- 색 -->
			<c:forEach var="itemDto" items="${colorList}">
				<c:if test="${itemDetailViewDto.itemNo != itemDto.itemNo}">
					<a href="/item/detail?itemNo=${itemDto.itemNo}">${itemDto.itemColor }</a>
					<c:forEach var="attach" items="${colorListAtta }">
					
						<c:if test="${itemDto.itemNo == attach.key }">
							<%-- <a src="/attachment/download?attachmentNo=${attach.value}" style="width:450px;"></a> --%>
							<span>${attach.value}</span>
							<a href="#"><img src="http://placehold.co/450" style="width: 50px;"></a>

						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
			</div>
		</div>
	
	</div>
	<div>
		<hr>
	</div>
	<span>${itemDetailViewDto.itemContent }</span>
	</div>


		<hr>
	<div class="container">
		<div class="row mt-4">
			<h1>reviewsList 시작</h1>
			<div class="col" id="reviewsList">
				
		<%-- 	<c:forEach var="reviewsDto" items="${list}">
				<span>${list.reviewsNo }</span>
				<span>${list.reviewsTitle }</span>
				<span>${list.reviewsContent }</span>
				<span>${list.reviewsWtime }</span>
				<span>${list.ItemTitle }</span>
				<span>${list.usersNickname }</span>
				<span>${list.usersEmail }</span>
				
			</c:forEach> --%>
			
			</div>
		</div>
	</div>

		<hr>
	<div class="container write-box" >
		<div class="row mt-4">
			<div class="col" >
				<input type="text" class="form-control" name="reviewsTitle" placeholder="제목 입력">
			</div>
		</div>
			<div class="row mt-4">
			<div class="col" >
				<textarea class="form-control" rows="3" name="reviewsContent" placeholder ="내용입력"></textarea>
			</div>
			</div>
			<div class="row mt-4">
			<div class="col" >
				<input type="text"  class="form-control" name="reviewsStar" placeholder="만족도조사">
			</div>
			</div>
			<div class="row mt-4">
				<div class="col d-flex" >
				<div class="ms-auto">
					<button class="btn btn-success ms-auto write-button">작성</button>
					<%-- <input type="hidden" name="usersEmail" value="${ }"> --%>
				</div>
				</div>
			</div>
	</div>


<%@ page session="true" %>
<% 
    String usersEmail = (String) session.getAttribute("usersEmail");
    String usersLevel = (String) session.getAttribute("usersLevel");
%>

<p>현재 로그인된 이메일: <%= usersEmail %></p>
<p>사용자 레벨: <%= usersLevel %></p>
	<hr>
			"${itemDetailViewDto}"
			<h2>칼라리스트</h2>
			







<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>