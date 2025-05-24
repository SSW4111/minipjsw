$(function() {
	var status={
	  itemIoNo : $("#itemIo").val(), itemQty : $("#itemQty").val(),
	  ok:function(){
	      return this.itemIoNo > 0 && this.itemQty > 0;
	  }
	};
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
	$(document).ready(function() {
	    $('.thumbnail').click(function() {
	        const fullImageUrl = $(this).data('src');
	        $('#preview-image').attr('src', fullImageUrl);
	    });
	});	
	

	
	function displayItems(items) {
	  const container = $('#reviewsList');
	  const currentUserEmail = items.userEmailValid; // 받아온 현재 로그인 유저 이메일

	  // 날짜 포맷 함수 (yyyy-MM-dd HH:mm)
	  function formatDate(dateString) {
	    const date = new Date(dateString);
	    if (isNaN(date)) return dateString; // 날짜 변환 실패 시 원본 리턴

	    const pad = (n) => n.toString().padStart(2, '0');
	    const year = date.getFullYear();
	    const month = pad(date.getMonth() + 1);
	    const day = pad(date.getDate());
	    /*const hours = pad(date.getHours());
	    const minutes = pad(date.getMinutes());
	    return `${year}-${month}-${day} ${hours}:${minutes}`;
*/	  
		return `${year}-${month}-${day}`;
		}

	  items.list.forEach(item => {
	    const isOwner = item.usersEmail === currentUserEmail; // 적힌거랑 비교
	    const formattedDate = formatDate(item.reviewsWtime);

	    const reviews = $(`
	      <div class="row mt-4">
	        <div class="col d-flex">
	          <span>${formattedDate}</span>				
	          <span class="ms-3 text-muted" >만족도 ${item.reviewsStar}</span>	
	          <div class="ms-auto">
	            	
	          </div>
	        </div>	
	      </div>
	      <div class="row mt-2">
	        <div class="col">
			  <div class="d-flex">
	          <h2>${item.reviewsTitle}</h2>
			  <span class="ms-auto">${item.usersNickname}</span>
			  </div>
	          <span class="ms-1">${item.reviewsContent}</span>
	        </div>
	      </div>
	      <div class="d-flex">
	        <input type="hidden" class="reviewNo" value="${item.reviewsNo}">
	        <button class="btn btn-danger ms-auto btn-sm d-none delete-button">
	          <i class="fa-solid fa-trash"></i>
	        </button>					
	      </div>				
	      <hr>
	    `);

	    if (isOwner) {
	      reviews.find('.delete-button').removeClass('d-none');
	    } else {
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

	
		$(".cart-button").click(function(){
			if (!usersEmail || usersEmail === "null") {
				return;
			}
			
			
			var itemNum = $("#itemNO").val();
			var cartQty = $("#itemQty").val();
			var itemIo = $("#itemIo").val();
			$.ajax({
				url:"/rest/cart/add",
				method:"post",
				data:{itemNo:itemNum, itemIoNo:itemIo, cartQty:cartQty},
				success:function(response){
					console.log(response);
				}
			})
		})
		
		$(".order-button").click(function(){
			//console.log("hwhwhw");
			var itemNum = $("#itemNO").val();
			var cartQty = $("#itemQty").val();
			var itemIo = $("#itemIo").val();
			/*console.log(itemNum);
			console.log(cartQty);
			console.log(itemIo);*/
		})
		
		
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





