<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/join1.js"></script>

<div class="row mt-4">
	<div class="col-4 offset-4">

		<h2 class="text-center">이메일로 가입하기</h2>
		<div style="min-height: 80px;"></div>

		<form action="join1" method="post" class="form-check" autocomplete="off">
			<label class="me-1 form-label" for="joinEmail">이메일 입력</label>
			<div class="mb-3 input-group">
				<input type="email" inputmode="email" class="form-control"
					id="joinEmail" name="usersEmail">
				<button type="button" class="btn btn-secondary cert rounded ms-1"
					disabled>
					인증번호<i class="fa-solid fa-paper-plane"></i>
				</button>
			</div>


			<!--      <div class="cert-wrapper" style="display: none;"> -->
			<!--  <div class="cert-wrapper" >
                       <label class="me-1 form-label" for="certNumber">인증번호 입력</label>
                       <div class="mb-3  input-group">
                            <div class="col-3">
                                <input type="text" inputmode="numeric" name="certNumber" class="form-control">
                            <div class="invalid-feedback">인증번호가 일치하지 않습니다</div>
                            <div class="valid-feedback">인증완료</div>
                            </div>
                            
                           <button type="button" class="btn btn-secondary confirm-cert rounded ms-1">
                               <span>인증하기</span>
                            </button>
                            
                            <button type="submit" class="btn btn-secondary next ms-auto" disabled>다음</button>
                           
                            
                        </div>
                    </div> -->
			<div class="cert-wrapper" >
				<label class="me-1 form-label" for="certNumber">인증번호 입력</label>
				<div class="mb-3 d-flex">
					<div class="col-3">
						<input type="text" inputmode="numeric" name="certNumber"
							class="form-control">
						<div class="invalid-feedback">인증번호가 일치하지 않습니다</div>
						<div class="valid-feedback">인증완료</div>
					</div>
					<div class="col-3">
					<button type="button"
						class="btn btn-secondary confirm-cert rounded ms-1">
						<span>인증하기</span>
					</button>
					</div>
					<div class="ms-auto">
					<button type="submit" class="btn btn-secondary next ms-auto rounded"
						disabled>다음</button>
					</div>
				</div>
			</div>
			<div class="col-2 offset-10"></div>

		</form>

		<div style="min-height: 150px;"></div>
		<!-- </div>

    </div> -->
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
