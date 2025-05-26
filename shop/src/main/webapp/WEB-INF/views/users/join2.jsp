<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/join2.js"></script>


<div class="row mt-4">
	<div class="col-4 offset-4">
		<h2 class="text-center">비밀번호 입력</h2>
	<div style="min-height: 60px;"></div>
			<%
	    request.setAttribute("step", 2);  // 현재 단계
	%>
	<jsp:include page="../template/progressbar.jsp" />

		<form action="join2" method="post" class="form-check" autocomplete="off">
			<label class="form-label me-1 mt-4" for="pwCheck">비밀번호</label>
			<div class="mb-3 input-group">
				<input type="password" id="pwCheck" name="usersPw" class="form-control">
			</div>
			<label class="form-label me-1" for="pw-re">비밀번호 재입력</label>
			<div class="mb-3 input-group">
				<input type="password" id="pw-re" name="usersPwRe"
					class="form-control">
				<div class="invalid-feedback">비밀번호가 다릅니다</div>
			</div>
			
			<div class="d-flex">
				<button type="submit" class="btn btn-secondary next ms-auto" disabled>다음</button>
			</div>
			
		</form>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>