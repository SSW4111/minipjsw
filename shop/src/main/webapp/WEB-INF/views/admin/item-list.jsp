<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="/js/itemlist.js"></script>


<div class="container">
  <div class="row mt-2">
    <div class="col">
      <form action="item-list" method="get">
        <div class="d-flex justify-content-between align-items-center">
          <select name="column" class="form-select form-select w-auto">
            <option value="" ${empty param.column ? 'selected' : ''}>선택하세요</option>
            <option value="item_title" ${param.column == 'item_title' ? 'selected' : ''}>이름</option>
            <option value="item_color" ${param.column == 'item_color' ? 'selected' : ''}>컬러</option>
            <option value="item_gender" ${param.column == 'item_gender' ? 'selected' : ''}>성</option>
            <option value="item_category" ${param.column == 'item_category' ? 'selected' : ''}>분류</option>
            <option value="item_detail" ${param.column == 'item_detail' ? 'selected' : ''}>디테일</option>
          </select>
          <input type="text" name="keyword" value="${param.keyword}" class="form-control  me-2 " placeholder="검색어를 입력하세요">
          <button type="submit" class="btn btn-outline-primary"><i class="fa fa-search"></i></button>
        </div>
      </form>
    </div>
  </div>
</div>




<div class="container mt-4">
    <table class="table">
        <thead>
            <tr>
                <th>상품번호</th>
                <th>제목</th>
                <th>색</th>
                <th>성별</th>
                <th>분류</th>
                <th>디테일</th>
                <th>평균 만족도</th>
                <th>댓글수</th>
                <th>좋아요</th>
                <th>가격</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${list}">
                <tr class="trtr">
                    <td data-item-no="${item.itemNo}">${item.itemNo}</td>
                    <td>${item.itemTitle}</td>
                    <td>${item.itemColor}</td>
                    <td>${item.itemGender}</td>
                    <td>${item.itemCategory}</td>
                    <td>${item.itemDetail}</td>
                    <td>${item.itemAveStar}</td>
                    <td>${item.itemReviewsCount}</td>
                    <td>${item.itemLike}</td>
                    <td>${item.itemPrice}</td>
                    <td>
                    	<i class="fa-solid fa-trash text-danger delete-btn"></i>
                    	<i class="fas fa-box text-warning manage-btn" id="io"  data-bs-toggle="modal" data-bs-target="#itemIoModal"></i>
                    	
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>



<div class="container">
    <div class="row mt-2">
    <div class="col d-flex">
   	 	<div class="ms-auto">
        	<button type="submit" class="btn btn-secondary" name="highStar" value="true" >만족도순</button>
        	<button type="submit"  class="btn btn-secondary" name="recent" value="true">최신순</button>
    	</div>
    </div>
    </div>
</div>    

<div class="modal fade" id="itemIoModal" tabindex="-1" data-bs-backdrop="static">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="profileModalLabel">재고관리</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
			
				<div class="container" >
					
								<table class="table">
									<thead>
									<tr>
										<th>상품 번호</th>
										<th>크기</th>
										<th>현재 재고</th>
										<th>재고 수정</th>
										<th></th>
										<th></th>
									</tr>
									
									</thead>
										<tbody  id="ioList"> 
											
										</tbody>
								
								
								</table>				
							
							<div class="container insert-box">
							
							<div class="row mt-4">
								<label class="col-3 form-label " >크기</label>
								<select class="form-select" name="sizeName">
									<option value="">선택하세요</option>
									<option>XS</option>
									<option>S</option>
									<option>M</option>
									<option>L</option>
									<option>XL</option>
									<option>XXL</option>
									<option>XXXL</option>
								</select>
							</div>
							<div class="row mt-4">
						<label class="col-3 form-label " >수량</label>				
						<input type="number" class="form-control" name="itemIoIn">
						<input type="hidden" name="itemNo" value="${item.itemNo}">
				</div>		
				
							 </div>
				<hr>
				<div class="d-flex">
				<button class="btn btn-secondary me-auto plus-btn"><i class="fa fa-plus"></i></button>	
    			<button class="btn btn-success ms-auto">완료하기</button>
				</div>
       </div>
          
        </div>
      </div>
    </div>




<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
