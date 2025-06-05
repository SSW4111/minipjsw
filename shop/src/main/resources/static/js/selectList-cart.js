$(function(){
	
	function callAllAddress(){
		$.ajax({
			url:"/rest/delivery/list",
			method:"GET",
			success: function(list) {
			    if (!list || list.length === 0) {
			      $('#addressList').html('<li>등록된 주소가 없습니다.</li>');
			      return;
			    }

			    let html = '';
			    list.forEach(function(dto) {
			      html += `
				  <li class="list-group-item d-flex align-items-center">
				        <strong>[${dto.deliveryPost}]</strong> ${dto.deliveryAddress1} <span class="text-muted">(${dto.deliveryAddress2})</span>
				      <input class="form-check-input ms-2" type="checkbox" name="selectedAddress" value="${dto.deliveryNo}" id="address-${dto.deliveryNo}">
				  		<i class="fa fa-xmark ms-auto text-danger" id="delete" style="cursor: pointer;"></i>
				  </li>
			      `;
			    });

			    $('#addressList').html(html);
			  },
		})
	}
	
	$(document).on("click", "#delete", function(){
		const no = $("[name=selectedAddress]").val();
		
		$.ajax({
			url:"/rest/delivery/delete",
			method:"GET",
			data:{deliveryNo:no},
			success:function(entity){
				callAllAddress();
			}
		})
	})
	
	$(document).on('change', 'input[name="selectedAddress"]', function () {
	   $('input[name="selectedAddress"]').not(this).prop('checked', false);
	 });
	
	 $(".set-main").click(function(){
		const selected = $('input[name="selectedAddress"]:checked');
		console.log(selected.val());
		$.ajax({
			url:"/rest/delivery/set/main",
			method:"GET",
			data:{deliveryNo:selected.val()},
			success:function(){
				callAddress();
			}
		})
	 })
	
	
	callAllAddress();
	function callAddress(){
		$.ajax({
			url:"/rest/delivery/one",
			method:"GET",
			success:function(dto){
				if (dto == null ) {
				        $('#integratedAddress').text("주소 설정을 해주세요");
				        return;
				      }
					  $('#integratedAddress').text(
					    `[${dto.deliveryPost}] ${dto.deliveryAddress1} ${dto.deliveryAddress2}`
					  );
				    },
				    error: function(xhr, status, error) {
				      console.log("error");
				      $('#integratedAddress').text("주소 설정을 해주세요");
			}
		})
	
	}
	
	function callRequest(){
		$.ajax({
				url:"/rest/option/one",
				method:"GET",
				success:function(dto){
			//		console.log(dto);
					if (dto == null || !dto.usersOrderOptionsRequest) {
					        $('#request').text("요청사항을 설정 해주세요");
					        return;
					      }
					      $('#request').text(dto.usersOrderOptionsRequest);
					},
					    error: function(xhr, status, error) {
					      console.log("error");
					      $('#request').text("요청사항을 설정 해주세요");
				}
			})
	}
	callAddress();
	callRequest();
	
	
	$("#saveRequestBtn").click(function(){
		const requestName = $("[name=request]").val();
		
		$.ajax({
			url:"/rest/option/save",
			method:"POST",
			data:{request:requestName},
			success:function(entity){
				$("#closeRequestModal").click();				
				callRequest();
				
			}
		})
	})
	
	
	$('#searchPostcode').click(function() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 도로명 주소 사용
	            let addr = data.roadAddress;

	            // 우편번호와 주소를 각 필드에 넣기
	            $('#postcode').val(data.zonecode);
	            $('#address').val(addr);
				console.log(addr);
	            // 상세주소 입력창에 포커스
	            $('#detailAddress').focus();
	        }
	    }).open();
	});
	
	
	$("#addAddress").click(function(){
		const post = $("#postcode").val();
		const address1 = $("#address").val();
		const address2 = $("#detailAddress").val();
		$.ajax({
			url:"/rest/delivery/add",
			method:"POST",
			data:{deliveryPost:post, deliveryAddress1:address1,
				deliveryAddress2:address2},
			success:function(){
				
				callAllAddress();
				$('#tab-list').click();
			}
		})
	})
	
	$('#tab-list').click(function () {
	   $('#page-list').show();
	   $('#page-add').hide();
	   $(this).addClass('btn-primary').removeClass('btn-outline-secondary');
	   $('#tab-add').removeClass('btn-primary').addClass('btn-outline-secondary');
	 });

	 $('#tab-add').click(function () {
	   $('#page-add').show();
	   $('#page-list').hide();
	   $(this).addClass('btn-primary').removeClass('btn-outline-secondary');
	   $('#tab-list').removeClass('btn-primary').addClass('btn-outline-secondary');
	 });
});
	
	
	
	
	





























