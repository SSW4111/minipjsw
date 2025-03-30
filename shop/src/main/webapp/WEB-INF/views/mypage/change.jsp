<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <jsp:include page = "/WEB-INF/views/template/header.jsp"></jsp:include>
     <script src="/js/change.js"></script>
		<!-- userDto -->

<div class="row mt-4">
	<div class="col-4 offset-4">
     <h2 class="text-center">개인정보변경</h2>
		<form action="change" method="post" class="form-check"
			enctype="multipart/form-data">

			<label class="me-1 form-label" for="joinEmail">이메일 입력</label>
			<div class="mb-3 input-group">
				<input type="email" inputmode="email" class="form-control"
					id="joinEmail" name="usersEmail"  value="${usersDto.usersEmail}">
			</div>
			<label class="form-label me-1" for="pwCheck" >바꿀 비밀번호</label>
			<div class="mb-3 input-group">
				<input type="password" id="pwCheck" name="usersPw" class="form-control" >
			</div>
			<label class="form-label me-1" for="pw-re">비밀번호 재입력</label>
			<div class="mb-3 input-group">
				<input type="password" id="pw-re" name="usersPwRe"
					class="form-control">
				<div class="invalid-feedback">비밀번호가 다릅니다</div>
			</div>
			
			<div class="mb-3">
				<label class="me-1 form-label" for="contact">연락처</label> 
				<input
					type="text" inputmode="numeric" maxlength="13" name="usersContact"
					placeholder="010-xxxx-xxxx" class="form-control" id="contact"  value="${usersDto.usersContact}">
			</div>
			<div class="mb-3">
				<label class="me-1 form-label" for="nickname">닉네임</label> 
				<input
					type="text" name="usersNickname" class="form-control "  value="${usersDto.usersNickname}"	id="nickname">
			</div>
			<button> 변경하기</button>
		</form>
	</div>
</div>
<jsp:include page = "/WEB-INF/views/template/footer.jsp"></jsp:include>
       
       
       
       
       