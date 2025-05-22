$(function() {
	
		function callPage() {
		var itemNum = $("#itemNO").val();

		$.ajax({
			url: "/rest/reviews/list",
			method: "get",
			data: { itemNo: itemNum },
			success: function(list) {
				if(list != null){
					//displayAttachments();
				displayItems(list);
				//console.log(list);
				}
			},
			error: function(xhr, status, error) {
				console.error("AJAX 오류 발생:", error);
			}
		});
	}

	
	function displayItems(items) {
		          const container = $('#reviewsList' 	);
				 const currentUserEmail = items.userEmailValid; //받아온거
		          items.list.forEach(item => {
	//					                    <img src= "/attachment/download?attachmentNo=${att}" class="card-img-top" >
	
				const isOwner = item.usersEmail === currentUserEmail; // 적혀는거랑 비교
				
				
		              const reviews = $(`
						<div class="row mt-4">
							<div class="col d-flex">
								<span>닉네임:${item.usersNickname }</span>
								<span>${item.reviewsWtime }</span>				
								<span class="ms-1">만족도 ${item.reviewsStar}</span>	
								<div class="ms-auto">
								<span>${item.itemTitle }</span>
								</div>
							</div>	
						</div>
						<div class="row mt-2">
							<div class="col">
							<h2>${item.reviewsTitle }</h2>
							<span>${item.reviewsContent }</span>
							
							</div>
						</div>
						<div class="d-flex">
							<input type="hidden" class="reviewNo" value="${item.reviewsNo}">
							<button class="btn btn-danger ms-auto btn-sm d-none delete-button"><i class="fa-solid fa-trash"></i></button>					
							 
						</div>				
						<hr>
		              `);
					//  console.log(isOwner);
					  if (isOwner) {
					             reviews.find('.delete-button').removeClass('d-none');
					         }
							 else{
								 reviews.find('.delete-button').addClass('d-none');
							 }
		              container.append(reviews);
				  });
		      }
			  //<input type="text" name="reviewsTitle" placeholder="제목을 작성해주세요">${item.reviewsTitle }</input>
/*										<button class="btn btn-danger ms-auto deleteButton">삭제</button>*/


//$(".delete-button").click(function(){ 
	// 동적으로 생성 된애들은 이런 이벤트가 적용되지 않아서 document를 다시 불러와서 처리해야함
	$(document).on("click",".delete-button",function(){
		const num= $(this).siblings(".reviewNo").val();
		
		console.log("please delete number"+num);
		
		$.ajax({
					url: "/rest/reviews/delete",
					method: "post",
					data:{reviewsNo:num},
					success:function(response){
						console.log(response);
						clearList();
						callPage();
					},
	});
	
	});
	
		
		
		//$(document).on("click",".write-button",function(){
		$(".write-button").click(function(){
		var itemNum = $("#itemNO").val();
		var reviewsT = $("[name=reviewsTitle]").val();
		var reviewsS = $("[name=reviewsStar]").val();
		var reviewsC = $("[name=reviewsContent").val();
  		$.ajax({
					url: "/rest/reviews/add",
					method: "post",
					data:{reviewsTitle:reviewsT, reviewsContent:reviewsC, reviewsStar:reviewsS, itemNo:itemNum},
					success:function(respose){
						clearWrite();
						clearList();
						callPage();
					},
		});
		
		});
		
		
		function clearWrite(){
			$(".write-box input").val(""); 
			   $(".write-box textarea").val(""); 
		}
		
		function clearList(){
			 $("#reviewsList").empty();
		}
		
		
	callPage();
});





