$(function(){
        
	var currentPage = 1;
	var pageSize = 3;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
	});
	
	function callPage(currentPage){
		$.ajax({
			url:"/rest/wish/list",
			method:"post",
			data:{page: currentPage, size:pageSize},
			success:function(list){
				displayItems(list);
				$(".wishCount").text(list.totalCount);
			}
		
		})
	}
	
	

	
	
	
	
	function displayItems(items) {
	    const container = $("#wishListContainer");

	    items.list.forEach(item => {
			const gender = item.itemGender === 'M' ? '남자' : '여자';
	        const table = $(`
	            <div class="cart-item d-flex align-items-center p-3" style="border: 1px solid black; position: relative;">
	                <a style="text-decoration:none;"><i class="fa-solid fa-heart  ms-auto like-heart" data-item-no="${item.itemNo}"></i></i></a>
	                 <a  href="/item/detail?itemNo=${item.itemNo}">
					 <img src="http://placehold.co/80" class="ms-5" style="width: 80px; height: 80px;">
	                </a>
					 <a  href="/item/detail?itemNo=${item.itemNo}" class="card-body ms-5">
	                    <h5 class="card-title">${item.itemCategory}</h5>
	                  
	                    <p class="card-text">${item.itemDetail}</p>
	                    <div class="d-flex justify-content-between align-items-center">
	                    <p>${gender} </p>   
						<p>${item.itemPrice}원</p>
	                    </div>
	                </a>
	            </div>
	        `);

	        container.append(table);
	    });

	    if (items.isLastPage == false) {
	        $(".more-btn").hide();
	    }
	}
	
	callPage(currentPage);
	
	
		$(document).on("click", ".like-heart", function(e){
		var itemNo = $(this).data("item-no");

			$.ajax({
			  url:"/rest/item/action",
			  method:"post",
			  data: {itemNo: itemNo},
			 	success:function(response){
			  		 $(e.target).removeClass("fa-solid fa-regular")
			  			.addClass(response.done ? "fa-solid" : "fa-regular");
						$('#wishListContainer').empty();
						callPage();
			  	}
			  })
		 });
		

});


















