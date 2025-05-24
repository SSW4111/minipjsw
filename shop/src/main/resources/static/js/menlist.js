$(function() {

	var currentPage = 1;
	var size = 12;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
		//console.log("현재페이지3 : " + currentPage);
		
	});

	function callPage(currentPage){
		
		

		$.ajax({
			url:"/rest/item/listM",
			method:"post",
			data:{page:currentPage, size:size},
			success:function(list){
				
				displayItems(list);
				//console.log("dkssud");
				//console.log(list.attachmentList[118]);
				// attachmentList 싹 다 뽑힘
			}
		})
		


		
	}
	//목록에있는 itemNoList 뽑기
//	var itemNoList = [];

	callPage(currentPage);
	
	function displayItems(items) {    
	            const container = $('#itemListContainer');
	            items.listM.forEach(item => {
	          
	                  //좋아요 꽉찬하튼지 빈하트인지 처음에 체크해서 모양바꿔야함
	                  $.ajax({
	                      url:"/rest/item/check",
	                      method:"post",
	                      data:{itemNo : item.itemNo},
	                      success:function(response){
	                          $(`.like-heart[data-item-no="${item.itemNo}"]`)
	                          .removeClass("fa-solid fa-regular")
	                              .addClass(response.done ? "fa-solid" : "fa-regular");    
	                      }
	                  });
					  const aveStar = item.itemAveStar ? Number(item.itemAveStar).toFixed(2) : '0.00';
					  const itemCard = $(`
					    <div class="col mb-4">
					      <div class="card h-100 shadow-sm border-0" style="width: 18rem;">
					        
					        <!-- 이미지 -->
					        <a href="/item/detail?itemNo=${item.itemNo}">
					          <img src="/attachment/download/item?itemNo=${item.itemNo}" 
					               class="card-img-top" 
					               alt="${item.itemTitle}" 
					               style="object-fit: cover; height: 400px;">
					        </a>

					        <div class="card-body d-flex flex-column">
					          
					          <div class="d-flex align-items-center mb-2">
					            <h5 class="card-title mb-0 text-truncate" style="max-width: 85%;">
					              <a href="/item/detail?itemNo=${item.itemNo}" class="text-dark text-decoration-none">${item.itemTitle}</a>
					            </h5>
					            <i class="fa-regular fa-heart ms-auto like-heart text-danger" style="cursor: pointer;" data-item-no="${item.itemNo}"></i>
					          </div>

							  <div class="text-muted small d-flex justify-content-between align-items-center flex-wrap" style="font-size: 0.85rem;">
							    <span>${item.itemCategory}</span>
							    <span class="text-truncate" style="max-width: 50%;">${item.itemDetail}</span>
							    <span class="text-warning ms-auto">
							      <i class="fa-solid fa-star"></i> ${aveStar}
							    </span>
								
								</div>

					        </div>
					      </div>
					    </div>
					  `);

					  			
/*					  const itemCard = $(`
					    <div class="col me-auto text-decoration-none">
					      <div class="card" style="width: 18rem;">
					        <div class="card-header d-flex">
					          <a href="/item/detail?itemNo=${item.itemNo}" class="text-decoration-none"> <span>${item.itemTitle}</span> </a>
					          <i class="fa-regular fa-heart ms-auto like-heart" data-item-no="${item.itemNo}"></i>
					        </div>

					        <a class="card-body" href="/item/detail?itemNo=${item.itemNo}">
					          <img src="/attachment/download/item?itemNo=${item.itemNo}" 
					               class="card-img-top" 
					               style="object-fit: cover; weight:200px; height: 200px;">
					        </a>

					        <div class="card-footer">
					          <ul class="list-group">
					            <li class="list-group-item">${item.itemCategory}</li>
					            <li class="list-group-item">${item.itemDetail}</li>
					            <li class="list-group-item">${item.itemColor}</li>
					            <li class="list-group-item">${item.itemNo}</li>
					          </ul>
					        </div>
					      </div>
					    </div>
					  `);*/
				  const attachContainer = $('#attachList');
				//  const putAttachList = $(`
					//<img src= "/attachment/download?attachmentNo=${items.attachmentList[item.itemNo]}" class="card-img-top" >
					//`);
				  
//				  console.log(items.attachmentList[118]); 3개 뽑힘
				 // attachContainer.append(putAttachList);
	              container.append(itemCard);
				  if(items.isLastPage){

					$(".more-btn").hide();
				  }
				  else{
					$(".more-btn").show();
				  }
			  });

	      }
	
		
	
		  
		  
		  
		  
	  //이건 좋아요 /좋아요해제
	$(document).on("click", ".like-heart", function(e){
	var itemNo = $(this).data("item-no");

//	console.log("itemNo:", itemNo); 
		$.ajax({
		  url:"/rest/item/action",
		  method:"post",
		  data: {itemNo: itemNo},
		 	success:function(response){
		  		 $(e.target).removeClass("fa-solid fa-regular")
		  			.addClass(response.done ? "fa-solid" : "fa-regular");
		  			
		  	}
		  })
	 });
	

 


$(function () {
           $(document).ready(function() {
    $(".colorList").on("click", function() {
		$('#itemListContainer').empty();
        // 서버에 검색 요청 보내기
        $.ajax({
            url: '/rest/item/search',   // 요청할 URL (컨트롤러의 주소)
            method: 'GET',            // 요청 방식 (GET)
            data: {column:'item_color', keyword: $(this).text(), color:$(this).text()},     // 검색 조건 데이터
            success: function(list) {
                // 서버에서 받은 검색 결과를 테이블에 삽입
                var tableBody = $("#result tbody");
                tableBody.empty();  // 이전 결과 지우기

                // 검색 결과가 있을 경우
                if (list != null) {
					displayItems(list);
                } else {
                    tableBody.append("<span colspan='4'>검색 결과가 없습니다.</span>");
                }
            },
            error: function(xhr, status, error) {
                console.log("검색 중 오류 발생:", error);
            }
        });
    });
});



       });




});