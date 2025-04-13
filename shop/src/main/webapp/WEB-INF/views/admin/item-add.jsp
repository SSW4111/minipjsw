<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/itemadd.js"></script>

 <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/hiphop5782/score@latest/score.min.js"></script>






<div class="container">
	<div class="p-5 mb-4 bg-light rounded-3">
		<div class="container-fluid py-5">
			<h1 class="display-5 ">상품 항목 등록</h1>
		</div>
	</div>
</div>

<div class="container">
	<form action="item-add" method="post" enctype="multipart/form-data"
		autocomplete="off">
		<div class="row mt-2">
			<label class="col-sm-3 form-label">제목</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemTitle"
					placeholder="제목을 입력하세요">
			</div>
		</div>

		<div class="row mt-2">
			<label class="col-sm-3 form-label">분류</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemCategory"
					placeholder="분류을 입력하세요 ex) 상의, 하의">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">디테일</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemDetail"
					placeholder="옷의 종류를 적어주세요 ex) 자켓, 후드">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">색깔</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemColor"
					placeholder="색을 영여 소문자로만 입력하세요">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">가격</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" inputMode="numeric"
					name="itemPrice" placeholder="가격을 입력하세요">
			</div>
		</div>

		<div class="row mt-2">
			<label class="col-sm-3 form-label">성별</label>
			<div class="col-sm-9 d-flex">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" value="M" name="itemGender"
						id="maleCheck"> <label class="form-check-label"
						for="maleCheck">남</label>
				</div>
				<div class="form-check ms-2">
					<input class="form-check-input" type="checkbox" value="F" name="itemGender"
						id="femaleCheck"> <label class="form-check-label"
						for="femaleCheck">여</label>
				</div>

			</div>
		</div>




	<!-- summernote -->
		<div class="row mt-2">
			<label class="col-sm-3 form-label">내용</label>
			<div class="col-sm-9">
			<textarea id="summernote"  name="itemContent" class="form-control"></textarea>
			</div>
		</div>

		<div class="row mt-2">
			<div class="d-flex">
				<a href="#"  type="button" class="btn btn-secondary ms-auto"
				data-bs-toggle="modal" data-bs-target="#itemImageModal"  id="profile">
					<i class="fa-solid fa-image"></i><span class="ms-1">이미지등록</span>
				</a>
			</div>
		</div>


		<div class="row mt-2">
			<div class="d-flex">
				<button class="btn btn-success btn-lg w-100" type="submit">등록</button>
			</div>
		</div>




	<div class="modal fade" id="itemImageModal" tabindex="-1" data-bs-backdrop="static">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="profileModalLabel">상품 이미지 등록</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <div class="d-flex">
        <span class="text-muted ms-auto">사진갯수 최대 8 개 까지 가능*</span>
      </div>
        <div class="photo-card">
          <div class="row mt-4">
          
            <div class="col d-flex flex-column ">
            	<div class="col-3">
		              <img src="/images/basic.png"  id="firstImage" class="image-btn" style="width: 60px; margin-bottom: 8px; margin-top:8px;">
		              <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
            	<div class="col-3">
		              <img src="/images/basic.png"  class="image-btn"  style="width: 60px; margin-bottom: 8px;">
		               <input type="file" style="display:none;" class="input">
            	</div>
             
             
            </div>
            
            <div class="col-7 me-auto">
              <img id="photoView" src="/images/basic.png" id="main" name="attach" style="width: 300px;">
               <label for="photoView" class="fs-6 d-block mt-2 text-secondary">(대표 화면)</label>
            </div>
                	<div class="col-2 mt-auto ">
            		<button class="btn btn-success ">등록하기</button>
            	</div>
            
            

          </div>
          
        </div>
      </div>
    </div>
  </div>
</div>


	</form>

</div>



















<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>