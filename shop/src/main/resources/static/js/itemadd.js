$(function(){
	var status = {
		itemTitle : false, itemCategory: false, itemDetail:false, itemColor:false, itemPrice:false, itemGender: false,
		ok: function(){
			return this.itemTitle == true &&	this.itemCategory == true &&	this.itemDetail == true &&
			this.itemColor == true &&	this.itemPrice == true && this.itemGender == true;
		}
	};
	
	$("[name=itemTitle").blur(function(){
		var regex = /^.+$/;
		var isValid = regex.test($(this).val().trim());
		status.itemTitle = isValid;
	});
	$("[name=itemCategory").blur(function(){
		var regex = /^.+$/;
		var isValid = regex.test($(this).val().trim());
		status.itemCategory = isValid;
	});
	$("[name=itemDetail").blur(function(){
		var regex = /^.+$/;
		var isValid = regex.test($(this).val().trim());
		status.itemDetail = isValid;
	});
	$("[name=itemColor").blur(function(){
		var regex = /^[a-z]+$/;
		var isValid = regex.test($(this).val());
		status.itemColor = isValid;
	});
	$("[name=itemPrice").blur(function(){
			var regex = /^[0-9]+$/;
			var isValid = regex.test($(this).val());
			status.itemPrice = isValid;
		});
	$(".form-check-input").click(function(){
		status.itemGender = true;
	})
//	$(".")
//	$("[name=itemTitle").blur(function(){});
	//$("[name=itemTitle").blur(function(){});
	//$("[name=itemTitle").blur(function(){});
	
		
		
	
	$('.form-check-input').on('change', function () {
	    // 자신 이외의 다른 체크박스는 모두 해제
	    $('.form-check-input').not(this).prop('checked', false);
	});
	
	
	
	
	$(".image-btn").click(function() {
		$(this).siblings(".input").click();
	});
	
	$(".input").on("change", function () {
		var reader = new FileReader();
		var file = this.files[0];

		if (file) {
		       var FileTypes = ['image/png', 'image/jpeg'];
		       if (!FileTypes.includes(file.type)) {
		           alert("허용되지 않는 파일 형식입니다.");
		           $(this).val('');
		           return;
		       }

		       reader.onload = function(event) {
		           $(this).closest('.col-3').find('img').attr("src", event.target.result);
				   
				   if ($(this).closest('.col-3').find('img').attr('id') === 'firstImage') {
				                   $('#photoView').attr('src', event.target.result);
				               }
				   
		       }.bind(this); // input을 참조

		       reader.readAsDataURL(file);
		   } else {
		       $(this).closest('.col-3').find('img').attr("src", "/images/basic.png");
		   }
	   });

	   
	   
	   $("#summernote").summernote({
	           height:500,//높이(px)
	           minHeight:200, //최소높이(px)
	           maxHeight:600, //최대높이(px)
	           placeholder:"이미지 파일만 올려주세요",
	           toolbar: [
	               ["attach", ["picture"]],
	           ],
	     
	           callbacks: {
	               onImageUpload: function(files){
	                   if(files.length == 0) return;
	                   
	                   var form = new FormData();//form을 대신할 도구
	                   for(var i=0; i < files.length; i++) {
	                       form.append("attach", files[i]);		
	                   }
	                   
	                   $.ajax({
	                       processData: false,
	                       contentType: false,
	                       url:"/rest/admin/item/uploads",
	                       method:"post",
	                       data: form,
	                       success:function(response){
	                           for(var i=0; i < response.length; i++) {
	                               var tag = $("<img>").attr("src", "/attachment/download?attachmentNo="+response[i])
	                               .addClass("summernote-img")  //식별자 알아보기위한클래스
	                               .attr("data-attachment-no", response[i]); //추출을위한
	                               $("#summernote").summernote("insertNode", tag[0]);
	                           }
	                       }
	                   });
	               },
	           },
	       });
	      
		   
	       $("#summernote").blur(function(){
	   		var summernoteHtml = "<p><br></p>"; //진짜섬머노트 열받게하네
	   		var content = $(this).summernote('code').trim()  //섬머노트기능
	           var isValid = $(this).val().length > 0 && content !== summernoteHtml;
	           if(!isValid){
	               $(".note-placeholder").css("color","#d63031");
	           };
	           status.itemContent = isValid;
	       });
	   
	   
	   
	   $(".form-check").submit(function(){
			//summernote 비동기 실행
	           return status.ok();
	       });
	
});