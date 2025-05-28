<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/css/join3.css">
<script src="/js/join3.js"></script>

<div class="container-fluid">
	<div class="row mt-4">
		<div class="col-4 offset-4">
			<h1 class="text-center">이메일로 가입하기</h1>
		</div>
	</div>

	<div class="row mt-4">
		<div class="col-sm-4 offset-4">
			<form action="join3" method="post" class="form-check" enctype="multipart/form-data" autocomplete ="off">

				<div class="mb-3">
					<label class="me-1 form-label" for="contact">연락처</label> <input
						type="text" inputmode="numeric" maxlength="13" name="usersContact"
						placeholder="010-xxxx-xxxx" class="form-control" id="contact">
				</div>
				<div class="mb-3">
					<label class="me-1 form-label" for="nickname">닉네임</label> <input
						type="text" name="usersNickname" class="form-control "
						id="nickname">
				</div>
				<div class="mb-3 row mt-4">
					<a class="btn btn-secondary col-sm-3" id="profile"
						data-bs-toggle="modal" data-bs-target="#profileModal" href="#">프로필
						등록하기<i class="fa-solid fa-plus"></i>
					</a>
					<button type="submit" class="btn btn-secondary col-sm-3 offset-6">회원가입
						완료</button>
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
									<img id="photoView" src="/images/basic.png"
										style="border-radius: 50%; position: relative;">
									<button type="button" class="btn photo-btn">
										<span class="badge text-bg-secondary file"> <i
											class="fa-solid fa-plus fs-3"></i>
										</span>
									</button>
								</div>
								<div class="mb-3">

									<input type="file" class="form-control d-none" id="fileInput" name="usersProfile" accept=".png, .jpg" >
								</div>

							</div>
							<div class="modal-footer">
								<hr>
								<div class="row">
									<div class="d-flex">
										<button type="button" class="btn btn-secondary me-auto btn-save">
											저장하기</button>

										<button type="button" class="btn btn-secondary ms-auto"
											data-bs-dismiss="modal">닫기</button>
									</div>
									<div class="col-sm-3"></div>
								</div>

							</div>
						</div>
					</div>
				</div>

			</form>
		</div>
	</div>

</div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>