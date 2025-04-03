<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/change.js"></script>
<!-- <script src="/js/join3.js"></script> -->
<link rel="stylesheet" type="text/css" href="/css/join3.css">

		<form action="change" method="post" class="form-check" enctype="multipart/form-data">
<div class="row mt-4">
	<div class="col-4 offset-4">
		<h2 class="text-center">개인정보변경</h2>

			<input type="hidden" name="changeProfile" class="changeProfile" value="false">
<input type="file" class="form-control d-none" id="fileInput" name="usersProfile" required>
			<%-- 

			<label class="me-1 form-label" for="joinEmail">이메일 입력</label>
			<div class="mb-3 input-group">
				<input type="email" inputmode="email" class="form-control"
					id="joinEmail" name="usersEmail" value="${usersDto.usersEmail}">
			</div> --%>
			<label class="form-label me-1" for="pwCheck">새로운 비밀번호</label>
			<div class="mb-3 input-group">
				<input type="password" id="pwCheck" name="usersPw"
					class="form-control">
			</div>
			<label class="form-label me-1" for="pw-re">비밀번호 재입력</label>
			<div class="mb-3 input-group">
				<input type="password" id="pw-re" name="usersPwRe"
					class="form-control">
				<div class="invalid-feedback">비밀번호가 다릅니다</div>
			</div>

			<div class="mb-3">
				<label class="me-1 form-label" for="contact">연락처</label> <input
					type="text" inputmode="numeric" maxlength="13" name="usersContact"
					placeholder="010-xxxx-xxxx" class="form-control" id="contact"
					value="${usersDto.usersContact}">
			</div>
			<div class="mb-3">
				<label class="me-1 form-label" for="nickname">닉네임</label> <input
					type="text" name="usersNickname" class="form-control "
					value="${usersDto.usersNickname}" id="nickname">
			</div>
			<div class="mb-3">
				<a class="btn btn-secondary col-sm-12" id="profile"
					data-bs-toggle="modal" data-bs-target="#profileModal" href="#">프로필
					변경<i class="fa-solid fa-plus"></i>
				</a>
			</div>


			<div class="d-flex">
				<button type="submit" class="btn btn-primary ms-auto">변경하기</button>
			</div>
	</div>
</div>



<div class="modal fade" id="profileModal" tabindex="-1"
	data-bs-backdrop="static">
	<div class="modal-dialog modal-xl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="profileModalLabel">프로필 등록</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					data-bs-target="#fileUploadModal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div
					class="d-flex justify-content-center align-items-center photo-card">
					<!-- <img id="photoView" src="https://placehold.co/450x450" style="border-radius: 50%; position: relative;"> -->
					<!-- <input type="file" name="attach" accept=".png,jpg" class="field w-100" > -->

					<c:if test="${empty attachmentNo}">
						<img id="photoView" src="/images/basic.png" name="attach"
							style="border-radius: 50%; position: relative;">
					</c:if>
					<c:if test="${not empty attachmentNo}">
						<img id="photoView"
							src="/attachment/download?attachmentNo=${attachmentNo}"
							style="border-radius: 50%; position: relative;">
					</c:if>
					<!-- <img id="photoView" src= "attachment/download?attachmentNo=" name="attach" style="border-radius: 50%; position: relative;"> -->



					<button type="button" class="btn photo-btn">
						<span class="badge text-bg-secondary file"> <i
							class="fa-solid fa-rotate fs-3"></i>
						</span>
					</button>
				</div>



			</div>
			<div class="modal-footer">
				<hr>
				<div class="row">
					<div class="d-flex">
						<button class="btn btn-secondary me-auto btn-save">저장하기</button>

						<button type="button" class="btn btn-secondary ms-auto close-btn"
							>닫기</button>
					</div>
					<div class="col-sm-3"></div>
				</div>

			</div>
		</div>
	</div>
</div>

</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>













