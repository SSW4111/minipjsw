<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/menlist.js"></script>

<style>
.dropdown-toggle::after {
    display: none;  /* 화살표 숨기기 */
  }

#itemListContainer {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem; /* 카드들 간 간격을 조정 */
    justify-content: flex-start; /* 마지막 줄에서 아이템들이 왼쪽 정렬되도록 */
}

</style>

<%-- <form action="/item/man-list" method="get">
    <input type="text" name="keyword" value="${param.keyword}">
    <button>검색</button>
</form> --%>

<div class="row mt-4">
	<!-- <div class="col-2">
 	<input type="checkbox" class="detail" ID="BASIC2">
                    <label class="menu" for="BASIC2">상세조건</label>
                    <div class="input-box">
                        <input type="checkbox" class="member_gender" id="M">
                        <label for="M">남</label>
                        <input type="checkbox" class="member_gender" id="F">
                        <label for="F">여</label>
                        <br>
                        <input type="checkbox" class="member_level" id="user">
                        <label for="user">일반회원</label>
                        <input type="checkbox" class="member_level" id="admin">
                        <label for="admin">관리자</label>
                        <br>
                        <label>birth</label>
                        <input type="text" name="member_birth">

                    </div>
 	</div> -->
</div>





<!-- <div class="col-2 text-center">
 
<span>필터</span>
                    <div class="input-box">
                        <input type="checkbox" class="item_color" id="M">
                        <label for="M">남</label>
                        <input type="checkbox" class="member_gen" id="F">
                        <label for="F">여</label>
                        <br>
                        <input type="checkbox" class="member_level" id="user">
                        <label for="user">일반회원</label>
                        <input type="checkbox" class="member_level" id="admin">
                        <label for="admin">관리자</label>
                        <br>
                    </div>
                    <div>
                    
                    	<ul>
                    		<li></li>
                    		<li></li>
                    		<li></li>
                    		<li></li>
                    	</ul>
                    </div>
 -->


<div class="container">
	<div class="row">
		<div class="col-sm-1 ms-auto">
			<div class="dropdown">
				<button class="btn  dropdown-toggle"  type="button"
					data-bs-toggle="dropdown" aria-expanded="false" id="itemColor">Color</button>
				<ul class="dropdown-menu " >
					<li><a class="dropdown-item colorList" href="#" >bk</a></li>
					<li><a class="dropdown-item colorList" href="#">blue</a></li>
					<li><a class="dropdown-item colorList" href="#">navy</a></li>
					<li><a class="dropdown-item colorList" href="#">green</a></li>
					<li><a class="dropdown-item colorList" href="#">brown</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-1 ms-auto">
			<div class="dropdown">
				<button class="btn  dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false">Price</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="#">1만원 미만</a></li>
					<li><a class="dropdown-item" href="#">2만원 미만</a></li>
					<li><a class="dropdown-item" href="#">3만원 미만</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="container ">
	<div class="row justify-content-start mt-4" id="itemListContainer"></div>
</div>
<!--
            <img src="/attachment/download?attachmentNo=${attachmentNo}" 
                 style="object-fit: cover; height: 200px;" class="card-img-top">


<c:forEach var="itemDto" items="${listM}" varStatus="status">
    <c:set var="attachmentNo" value="${attachList[status.index]}" />

	<div class="col-sm-3 mb-4"> 
	                    <div class="card" style="width: 18rem;">
	                        <div class="card-header d-flex">
	                            <span>${itemDto.itemTitle}</span> 
	                            <i class="fa-solid fa-heart ms-auto"></i>  
	                            <i class="fa-regular fa-heart ms-auto like-heart"></i>
	                        </div>
							<img src="/attachment/download/item?itemNo=${itemDto.itemNo}">
							<img src="/attachment/download?attachmentNo=${attachList[status.index]}">
												        
							<div class="card-footer">
	                            <ul class="list-group">
	                                <li class="list-group-item">${itemDto.itemCategory}</li>
	                                <li class="list-group-item">${itemDto.itemDetail}</li>
	                                <li class="list-group-item">${itemDto.itemAveStar}</li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
</c:forEach>
-->


<!--<%--  <div class="row mt-4">
     <div class="col-sm-8 ">
        <div class="row">
            <c:forEach var="itemDto" items="${listM}"  varStatus="status">
                <div class="col-sm-3 mb-4"> 
                    <div class="card" style="width: 18rem;">
                        <div class="card-header d-flex">
                            <span>${itemDto.itemTitle}</span> 
                            <i class="fa-solid fa-heart ms-auto"></i>  
                            <i class="fa-regular fa-heart ms-auto like-heart"></i>
                        </div>
						<img src="/attachment/download/item?itemNo=${itemDto.itemNo}">
						<img src="/attachment/download?attachmentNo=${attachList[status.index]}">
											        
						<div class="card-footer">
                            <ul class="list-group">
                                <li class="list-group-item">${itemDto.itemCategory}</li>
                                <li class="list-group-item">${itemDto.itemDetail}</li>
                                <li class="list-group-item">${itemDto.itemAveStar}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
            
        </div>
    </div>
</div>
 --%>-->



<div class="row mt-4">
	<div class="col text-center">
		<button class="btn more-btn">더보기</button>
	</div>
</div>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
