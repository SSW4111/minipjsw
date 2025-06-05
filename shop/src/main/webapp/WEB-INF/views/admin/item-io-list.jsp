<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/iolist.js"></script>

<script type="text/javascript">

</script>

<div class="container">
	
	<div class="row mt-2">
		<div class="col d-flex align-items-center">
			<h1>재고관리</h1>
			<button class="btn btn-primary btn-sm ms-auto"><i class="fa fa-plus">입고</i></button>
		</div>
	</div>
	<h2>현재고</h2>
	<table class="table">
		<thead>
			<tr>
				<th>사이즈</th>
				<th>수량</th>
				<th></th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<h2>입출고 기록</h2>
	<table class="table ">
		<thead>
			<tr>
				<th>일자</th>
				<th>사이즈</th>
				<th>상태</th>
				<th>수량</th>
				<th>총합</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	
	
	
	
	
	
	
	
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
