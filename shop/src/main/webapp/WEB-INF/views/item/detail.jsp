<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/mendetail.js"></script>




<div class="container">
	<div class="row mt-4">
		<div class="col-sm-6">

			<input type="hidden" value="${itemDetailViewDto.itemNo}" id="itemNO">

			<img id="preview-image" src="/attachment/download?attachmentNo=${attachList[0]}"  style="width: 600px;">
			<div class="d-flex mt-1 me-1">
			    <c:forEach var="attach" items="${attachList}">
			        <img class="thumbnail"
			             src="/attachment/download?attachmentNo=${attach}"
			             data-src="/attachment/download?attachmentNo=${attach}"
			             style="width: 80px; height:80px; border: 1px solid grey; margin-right: 5px; cursor: pointer;">
			    </c:forEach>
			</div>
		</div>

	
	

		<div class="col-sm-6 ">
			

			<div class="row mt-4">
				<div class="col">

					<h2 class="">${itemDetailViewDto.itemTitle}</h2>
					<span>${itemDetailViewDto.itemCategory }</span> <br>
					<span>${itemDetailViewDto.itemDetail }</span>
					<span>${itemDetailViewDto.itemAveStar }점</span>
					<h2>${itemDetailViewDto.itemPrice }원</h2>
				</div>
			</div>

			<div class="row mt-4">
				<div class="col">
					
					<c:forEach var="itemDto" items="${colorList}" varStatus="status">
					    <a href="/item/detail?itemNo=${itemDto.itemNo}">
					        <img src="/attachment/download?attachmentNo=${colorNoList[status.index]}"
					             alt="${itemDto.itemColor}"
					             title="${itemDto.itemColor}"
					             style="width: 80px; height: 80px; border: 1px solid grey; margin: 5px;">
					    </a>
					</c:forEach>
					
					<!--<c:forEach var="attach" items="${colorNoList}">
						<a href="/item/detail/itemNo={itemNo}">
					    <img src="/attachment/download?attachmentNo=${attach}" 
					         style="width: 80px; height: 80px; border: 1px solid grey; margin: 5px;">
						</a>
					</c:forEach>-->
								
					
					<!--<c:forEach var="itemDto" items="${colorList}">
					    <c:if test="${itemDetailViewDto.itemNo != itemDto.itemNo}">
					        <a href="/item/detail?itemNo=${itemDto.itemNo}">
					            ${itemDto.itemColor} ${itemDto.itemNo}
					        </a>

					        <c:forEach var="attach" items="${colorListAtta}">
					            <c:if test="${itemDto.itemNo == attach.key}">
					                <a href="/item/detail?itemNo=${itemDto.itemNo}">
					                    <img src="/attachment/download?attachmentNo=${attach.value}" style="width:50px;">
					                </a>
					            </c:if>
					        </c:forEach>
					    </c:if>
					</c:forEach>-->
				</div>
			</div>
			
			
			<div class="row mt-4">
		
				<!--<%-- "${iolist.item_io_total}" --%>-->
				<select class="form-select">
				<c:forEach var="ioDto" items="${iolist}">
					<option>${ioDto.sizeName}, ${ioDto.itemIoTotal}</option>	 	
					 
				</c:forEach>
				</select>
			</div>
			
			
		<div class = "row mt-4">
			<div class="col d-flex ">
				<div class="ms-auto">
					
				<button class="btn btn-outline-secondary">주문하러가기	</button>
				<button class="btn btn-outline-secondary">장바구니	</button>
				</div>
			</div>
		</div>
		</div>
	</div>

	



 



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