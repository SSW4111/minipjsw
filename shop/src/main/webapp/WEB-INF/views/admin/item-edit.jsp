<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="/js/itemedit.js"></script>

 <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/hiphop5782/score@latest/score.min.js"></script>






<div class="container">
	<div class="p-5 mb-4 bg-light rounded-3">
		<div class="container-fluid py-5">
			<h1 class="display-5 ">상품 항목 수정</h1>
		</div>
	</div>
</div>

<div class="container">
	<form action="update" method="post" enctype="multipart/form-data" autocomplete="off">
		<div class="row mt-2">
			<label class="col-sm-3 form-label">제목</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemTitle" value="${itemDto.itemTitle}"
					placeholder="제목을 입력하세요">
			</div>
		</div>
		<input type="hidden" name="itemNo" value="${itemDto.itemNo}">
		<div class="row mt-2">
			<label class="col-sm-3 form-label">분류</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemCategory" value="${itemDto.itemCategory}"
					placeholder="분류을 입력하세요 ex) 상의, 하의">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">디테일</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemDetail" value="${itemDto.itemDetail}"
					placeholder="옷의 종류를 적어주세요 ex) 자켓, 후드">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">색깔</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" name="itemColor" value="${itemDto.itemColor}"
					placeholder="색을 영여 소문자로만 입력하세요">
			</div>
		</div>
		<div class="row mt-2">
			<label class="col-sm-3 form-label">가격</label>
			<div class="col-sm-9">
				<input class="form-control" type="text" inputMode="numeric" value="${itemDto.itemPrice}"
					name="itemPrice" placeholder="가격을 입력하세요">
			</div>
		</div>		
		
		<input type="hidden" id="gender" value="${itemDto.itemGender}">
			
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
			<textarea id="summernote" name="itemContent" class="form-control">${itemDto.itemContent}</textarea>
			</div>
		</div>

		<div class="row mt-2">
			<div class="d-flex">
				<a href="#"  type="button" class="btn btn-secondary ms-auto"
				data-bs-toggle="modal" data-bs-target="#itemImageModal"  id="profile">
					<i class="fa-solid fa-image"></i><span class="ms-1">이미지수정</span>
				</a>
			</div>
		</div>


		<div class="row mt-2">
			<div class="d-flex">
				<button class="btn btn-success btn-lg w-100" type="submit">수정</button>
			</div>
		</div>




	<div class="modal fade" id="itemImageModal" tabindex="-1" data-bs-backdrop="static">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="profileModalLabel">상품 이미지 수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <div class="d-flex">
        <span class="text-muted ms-auto">사진갯수 최대 6 개 까지 가능*</span>
      </div>
        <div class="photo-card">
          <div class="row mt-4">
          
			<div class="col d-flex flex-column">
			    <c:forEach var="i" begin="0" end="5">
			        <div class="col-3">
			            <c:choose>
			                <c:when test="${i < fn:length(attachList)}">
			                    <img src="/attachment/download?attachmentNo=${attachList[i]}" 
			                         class="image-btn" 
			                         style="width: 60px; margin: 8px 0; cursor:pointer;" />
			                </c:when>
			                <c:otherwise>
			                    <img src="/images/basic.png" 
			                         class="image-btn" 
			                         style="width: 60px; margin: 8px 0;" />
			                </c:otherwise>
			            </c:choose>
						
			            <input type="file" name="attach" style="display:none;" class="input">
			        </div>
			    </c:forEach>
			</div>
            
			<div class="col-7 me-auto">
			    <img id="photoView" 
			         src="<c:choose>
			                <c:when test="${not empty attachmentList}">
			                    ${attachmentList[0]}
			                </c:when>
			                <c:otherwise>
			                    /images/basic.png
			                </c:otherwise>
			              </c:choose>" 
			         style="width: 300px;" />
			    <label for="photoView" class="fs-6 d-block mt-2 text-secondary">(대표 화면)</label>
			</div>
                	<div class="col-2 mt-auto ">
            		<button type="button" class="btn btn-success save-btn">수정하기</button>
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