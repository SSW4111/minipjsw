$(function(){
        
	var currentPage = 1;
	var size = 20;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
	});
	
	function callPage(){
		$.ajax({
			url:"/rest/wish/list",
			method:"post",
			data:{page: currentPage},
			success:function(list){
				displayItems(list);
				$(".wishCount").val(list.totalCount);
				
				
			}
		
		})
	}
	callPage();
	
	
	function displayItems(items) {
	    const att = items.attachmentList[0];
	    const container = $("#wishListContainer");

	    items.list.forEach(item => {
	        const table = $(`
	            <div class="cart-item d-flex align-items-center p-3" style="border: 1px solid black; position: relative;">
	                <a href="#"><i class="fa-solid fa-heart text-danger"></i></a>
	                <img src="http://placehold.co/80" class="ms-3" style="width: 80px; height: 80px;">
	                <div class="card-body">
	                    <h5 class="card-title">${item.itemCategory}</h5>
	                    <p class="card-text">${item.itemCategory}</p>
	                    <p class="card-text">${item.itemCategory}</p>
	                    <p class="card-text">${item.itemCategory}</p>
	                    <div class="d-flex justify-content-between align-items-center">
	                        <p>dddddd</p>
	                    </div>
	                </div>
	            </div>
	        `);

	        container.append(table);
	    });

	    if (items.isLastPage == false) {
	        $(".more-btn").hide();
	    }
	}
	
		

});


















