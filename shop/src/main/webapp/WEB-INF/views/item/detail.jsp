<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/menlist.js"></script>

<h2>디테일</h2>
"${itemDetailViewDto}"
<h2>칼라리스트</h2>
<c:forEach var="itemDto" items="${colorList}">
<c:if test="${itemDetailViewDto.itemNo != itemDto.itemNo}">
	<a href="/item/detail?itemNo=${itemDto.itemNo}">${itemDto.itemColor }</a>
</c:if>
</c:forEach>
<%-- "${colorList}" --%>
<h2>사이즈 리스트</h2>
"${sizeList}"







<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>