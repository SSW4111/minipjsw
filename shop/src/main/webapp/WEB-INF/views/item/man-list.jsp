<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/item/man-list" method="get">
    <input type="text" name="keyword" value="${param.keyword}">
    <button>검색</button>
</form>

<!-- 테이블 -->
<h1>남자!!!!!</h1>
<div class="cell">
    <table class="table">
        <thead>
            <tr>
                <th>No</th>
                <th>color</th>
                <th>size</th>
                <th>title</th>
                <th>content</th>
                <th>gender</th>
                <th>category</th>
                <th>detail</th>     
                <th>별점</th>
                <th>리뷰수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="itemListViewDto" items="${listM}">
   				 <tr>
			        <td>${itemListViewDto.itemNo}</td>
			        <td>${itemListViewDto.itemColor}</td>
			        <td>${itemListViewDto.itemSize}</td>
			        <td>${itemListViewDto.itemTitle}</td>
			        <td>${itemListViewDto.itemContent}</td>
			        <td>${itemListViewDto.itemGender}</td>
			        <td>${itemListViewDto.itemCategory}</td>
			        <td>${itemListViewDto.itemDetail}</td>
			        <th>${itemListViewDto.itemAveStar}</th>
			        <th>${itemListViewDto.itemReviewsCount}</th>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
</div>
