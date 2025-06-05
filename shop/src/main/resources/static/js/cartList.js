$(function(){
        
	var currentPage = 1;
	var pageSize = 10;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
	});
	
	function callPage(currentPage){
		$.ajax({
			url:"/rest/cart/list",
			method:"post",
			data:{page: currentPage, size:pageSize},
			success:function(list){
				if(!list){
					console.log("nothing");
					return;
				}
				displayItems(list);
				//$(".wishCount").text(list.totalCount);
				//console.log(list);
			}
		
		})
	}
	
	
	$('.order-btn').on('click', function () {
	   // e.preventDefault(); 

	    const selectedValues = $(".events:checked").map(function () {
	        return $(this).val();
	    }).get();

	    if (selectedValues.length === 0) {
	        alert("주문할 상품을 하나 이상 선택해주세요.");
	        return;  
	    }

	    $('#cartForm input[name="cartNoList"]').remove();

	    selectedValues.forEach(function (cartNo) {
	        $('#cartForm').append(
	            `<input type="hidden" name="cartNoList" value="${cartNo}">`
	        );
	    });

	    $('#cartForm').submit(); 
	});
	
	
	
	function displayItems(items) {
	    const container = $("#cartListContainer");
		    container.empty(); 
		if (!items || items.length === 0) {
		      container.append(`
		          <div class="text-center text-muted py-5">
		              <h5>장바구니가 비어 있습니다 </h5>
		          </div>
		      `);
		      return; // 더 이상 진행하지 않음
		  }
	    items.forEach(item => {
			const gender = item.itemGender === 'M' ? '남자' : '여자';
	        const table = $(`
	            <div class="cart-item d-flex align-items-center p-3 border border-muted"  position: relative;">
					<input type="checkbox" class="form-check events" value=${item.cartNo}>
	                 <a  href="/item/detail?itemNo=${item.itemNo}">
					 <img src="/attachment/download/item?itemNo=${item.itemNo}" class="ms-5" style="width: 80px; height: 80px;">
	                </a>
					 <a  href="/item/detail?itemNo=${item.itemNo}" class="card-body ms-5 text-decoration-none">
	                    <h5 class="card-title">${item.itemTitle}, ${item.sizeName}</h5>
	                    <span class="card-title">${item.itemCategory}</span>
	                  
	                    <span class="card-text">${item.itemDetail}</span>
	                    <div class="d-flex justify-content-between align-items-center">
	                    <p>${gender} ${item.cartQty}개</p>   
						<p>${item.itemPrice * item.cartQty}원</p>
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
	
	
		
	/*$(".order-btn").on("click", function() {
		const selectedValues = $(".events:checked").map(function() {
			      return $(this).val();
			    }).get();
				console.log(selectedValues);
				$.ajax({
					url:"/rest/"
				})
	});*/
	
	$(".delete-btn").on("click", function() {
	    const selectedValues = $(".events:checked").map(function() {
	      return $(this).val();
	    }).get();

	   // console.log( selectedValues);
	   $.ajax({
		url:"/rest/cart/delete",
		method:"post",
		data:{cartNo:selectedValues},
		success:function(response){
			if(response = "ok"){
				currentPage = 1;
				callPage();
			}
		}
	   })
	})
	
	
});


















