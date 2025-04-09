$(function() {
		
	var currentPage = 1;
	var size = 12;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
		//console.log("현재페이지3 : " + currentPage);
		
	});
	
	function callPage(){
		$.ajax({
			url:"/rest/item/listM",
			method:"post",
			data:{page:currentPage, size:size},
			success:function(list){
				displayItems(list);
			}
		})
	}
	callPage();
	
	function displayItems(items) {
				const att = items.attachmentList[0];
	          const container = $('#itemListContainer');
	          items.listM.forEach(item => {
//					                    <img src= "/attachment/download?attachmentNo=${att}" class="card-img-top" >
	              const itemCard = $(`
					<i class="fa-regular fa-heart  ms-auto like-heart" data-item-no="${item.itemNo}"></i>
					<a class="col me-auto" href="/item/detail?itemNo=${item.itemNo}">
					                <div class="card " style="width: 18rem;">
					                    <div class="card-header ">
					                        <span>${item.itemTitle}</span> 
											
					                    </div>
										<div class="card-body">
									
											<img src= "https://placehold.co/300" class="card-img-top" >
										</div>
					                    <div class="card-footer">
					                        <ul class="list-group">
					                            <li class="list-group-item">${item.itemCategory}</li>
					                            <li class="list-group-item">${item.itemDetail}</li>
					                            <li class="list-group-item">${item.itemColor}</li>
					                            <li class="list-group-item">${item.itemNo}</li>
					                        </ul>
					                    </div>
					                </div>
					            </a>
	              `);
	              
	              container.append(itemCard);
				  if(items.isLastPage == false){
					$(".more-btn").hide();
				  }
			  /*if("${itemVO.isLastPage}" == true){
			  }*/
			  });
;


	      }
	
	$(document).on("click", ".like-heart", function(e){
	var itemNo = $(this).data("item-no");
	console.log("itemNo:", itemNo); 
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