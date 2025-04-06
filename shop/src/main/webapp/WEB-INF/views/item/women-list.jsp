<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/womenlist.js"></script>
 
 
 
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


 
 
 
  <div class="container " >
 		<div class="row mt-4" id="itemListContainer">
		 	
 		</div>
  </div> 
  
  
  
  
 <%--  <div class="row mt-4">
     <div class="col-sm-8 ">
        <div class="row">
            <c:forEach var="itemDto" items="${listM}">
                <div class="col-sm-3 mb-4"> 
                    <div class="card" style="width: 18rem;">
                        <div class="card-header d-flex">
                            <span>${itemDto.itemTitle}</span> 
                           <!-- <i class="fa-solid fa-heart ms-auto"></i>  -->
                            <i class="fa-regular fa-heart ms-auto like-heart"></i>
                        </div>
                        <img src="https://placehold.co/150" class="card-img-top" alt="Item Image">
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
 --%>
 
 
 
<div class="row mt-4">
	<div class="col text-center">
 <button class="btn more-btn">더보기</button>
	</div>
</div>
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
