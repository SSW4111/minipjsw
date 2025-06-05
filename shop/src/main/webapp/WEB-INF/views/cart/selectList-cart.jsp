<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/selectList-cart.js"></script>
<style>
  .tag {
    font-size: 0.75rem;
    padding: 0.2em 0.6em;
    border-radius: 0.5rem;
    margin-right: 5px;
  }

  .tag-default {
    background-color: #e3fbe3;
    color: #228B22;
    border: 1px solid #c1eac1;
  }

  .tag-rocket {
    background-color: #d0ebff;
    color: #1c7ed6;
    border: 1px solid #a5d8ff;
  }

  .address-header {
    background-color: #e9fbe5;
    padding: 12px 16px;
    border-top-left-radius: 0.5rem;
    border-top-right-radius: 0.5rem;
  }

  .card-full-hr {
    margin: 0;
    border-top: 1px solid #ccc;
  }
</style>
 <div class="container">
 	<div class="row mt-4">
 		<div class="col d-flex">
			<h2>주문 상세 </h2>
 			<div class="ms-auto">
 			</div>
 		</div>
		

		<div class="container mt-5">
		  <div class="card shadow-sm" style="max-width: 1000px; margin: auto;">
		    <div class="address-header d-flex align-items-center">
		      <h5 class="mb-0">기본 배송지 | <strong>${selectedItemVO.usersDto.usersNickname}</strong></h5>
			  <button class="btn btn-outline-secondary btn-sm ms-auto" data-bs-toggle="modal" data-bs-target="#addressModal">배송지 변경</button>
		    </div>
		    <hr class="card-full-hr">

		    <div class="p-3">
				
		      <p class="mb-1" id="integratedAddress"></p>
			  
		      <p class="mb-1">${selectedItemVO.itemList[0].usersEmail}</p>
		      <p class="text-muted">${selectedItemVO.usersDto.usersContact}</p>
		    </div>
		  </div>
		</div>

		<div class="container mt-5">
		  <div class="card shadow-sm" style="max-width: 1000px; margin: auto;">
		    <div class="address-header d-flex align-items-center">
		      <h5 class="mb-0">배송 요청사항</h5>
		  <button class="btn btn-outline-secondary btn-sm ms-auto" data-bs-toggle="modal" data-bs-target="#requestModal">변경</button>
		  		    </div>
		    <hr class="card-full-hr">

		    <div class="p-3">
		       <p class="mb-1" id="request"></p>
		    </div>
		  </div>
		</div>
			
		<div class="container mt-5">
		  <div class="card shadow-sm" style="max-width: 1000px; margin: auto;">
		    <div class="address-header d-flex align-items-center">
		      <h5 class="mb-0">주문할 상품</h5>
		  		    </div>
		    <hr class="card-full-hr">
			<div class="p-3">
				<c:forEach var="item" items="${selectedItemVO.itemList}">
				       <div class="list-group-item d-flex justify-content-between align-items-start">
				         <div>
				           <div class="fw-semibold">${item.itemTitle}</div>
				           <small class="text-muted">${item.sizeName} / ${item.cartQty}개</small>
				         </div>
				         <div class="text-end">
				           <div class="fw-bold">
				             ${item.itemPrice * item.cartQty}원
				           </div>
				         </div>
				       </div>
				     </c:forEach>
			</div>
		  </div>
		</div>
			
		
		<div class="modal fade" id="addressModal" tabindex="-1">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      
		      <div class="modal-header">
		        <h5 class="modal-title">배송지 설정</h5>
		      </div>

		      <div class="modal-body">
		        <!-- 탭 버튼 -->
		        <div class="d-flex mb-3">
		          <button class="btn btn-outline-secondary me-2" id="tab-list">📦 배송지 목록</button>
		          <button class="btn btn-outline-secondary" id="tab-add">➕ 배송지 추가</button>
		        </div>

		        <div id="page-list">
		          <ul id="addressList" class="list-group">
		            
		          </ul>
				  <div class="mt-2 d-flex justify-content-end align-items-center gap-2">		
					<!--<button class="btn btn-danger btn-sm " ><i class="fa fa-trash text-light" ></i> 삭제</button>	-->
					  <button class="btn btn-primary btn-sm set-main" >기본 주소지 설정</button>
				  </div>

		        </div>

		        <div id="page-add" style="display: none;">
		          <div class="form-group mb-2">
		            <label>우편번호</label>
		            <div class="d-flex gap-2">
		              <input type="text" id="postcode" class="form-control" readonly>
		              <button type="button" id="searchPostcode" class="btn btn-outline-primary">검색</button>
		            </div>
		          </div>
		          <div class="form-group mb-2">
		            <label>주소</label>
		            <input type="text" id="address" class="form-control" readonly>
		          </div>
		          <div class="form-group mb-2">
		            <label>상세 주소</label>
		            <input type="text" id="detailAddress" class="form-control">
		          </div>
		          <button class="btn btn-success mt-2" id="addAddress">저장</button>
		        </div>
		      </div>

		      <div class="modal-footer">
		         <button type="button" class="btn btn-danger" id="closeAddressModal" data-bs-dismiss="modal" aria-label="닫기">닫기</button>
				 
		      </div>
		    </div>
		  </div>
		</div>

		
		
		
		<div class="modal fade" id="requestModal" tabindex="-1" aria-labelledby="requestModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="requestModalLabel">요청사항 변경</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="닫기"></button>
		      </div>
			  <div class = "modal-body">
			  <div class="form-group p-4" >
			    <div style="display: flex; gap: 8px;">
			      <input type="text" class="form-control" name="request" placeholder="요청사항을 적어주세요"  style="flex: 1;">
			      
			    </div>
			  </div>

			  
			  </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" id="closeRequestModal" data-bs-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-primary" id="saveRequestBtn">저장</button>
		      </div>
		    </div>
		  </div>
		</div>

		
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
