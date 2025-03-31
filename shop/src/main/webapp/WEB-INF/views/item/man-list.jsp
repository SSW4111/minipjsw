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
            </tr>
        </thead>
        <tbody>
            <c:forEach var="itemDto" items="${listM}">
   				 <tr>
			        <td>${itemDto.itemNo}</td>
			        <td>${itemDto.itemColor}</td>
			        <td>${itemDto.itemSize}</td>
			        <td>${itemDto.itemTitle}</td>
			        <td>${itemDto.itemContent}</td>
			        <td>${itemDto.itemGender}</td>
			        <td>${itemDto.itemCategory}</td>
			        <td>${itemDto.itemDetail}</td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
</div>
