$(function(){
	$('#searchPostcode').click(function() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 도로명 주소 사용
	            let addr = data.roadAddress;

	            // 우편번호와 주소를 각 필드에 넣기
	            $('#postcode').val(data.zonecode);
	            $('#address').val(addr);

	            // 상세주소 입력창에 포커스
	            $('#detailAddress').focus();
	        }
	    }).open();
	});
	
	
	
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
	
	
	
	
	





























