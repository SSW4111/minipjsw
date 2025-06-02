$(function(){
	var status = {
		
		
	};
	 var fileList = [];
	$("#profile").click(async function(){
		const atta = JSON.parse($(".at").val());
		  fileList = await Promise.all(
		          atta.map(attachmentNo => transfer(attachmentNo))
		      );

		console.log(fileList);
		
	})
	
	let fileSet = new Set();

	$("input[type='file']").each(function(index) {
	    $(this).change(function () {
	        const file = this.files[0];
	        if (!file) return;

	        const key = `${file.name}-${file.size}-${file.lastModified}`;

	        if (fileList[index]) {
	            const prevFile = fileList[index];
	            const prevKey = `${prevFile.name}-${prevFile.size}-${prevFile.lastModified}`;
	            fileSet.delete(prevKey); // 중복방지용
	        }

	        fileList[index] = file;
	        fileSet.add(key);

	        fileList.forEach((f, i) => {
	            console.log(i, "파일 이름:", f.name, "크기:", f.size);
	        });

	    });
	});
	
	/*$("input[type='file']").change(function () {
	     const files = Array.from(this.files);
	     fileList = fileList.concat(files);
	 	 console.log(fileList);
	 });*/

	 $(".che").click(function(){
		const formData = new FormData();
		fileList.forEach((file, i) => formData.append(`fileList`, file));
		
		const itemNo = $("input[name='itemNo']").val(); // input에서 가져오거나
		formData.append("itemNo", itemNo);
		
		console.log(formData);
		$.ajax({
		    url: '/rest/item/upload',
		    method: 'POST',
		    data: formData,
		    processData: false,
		    contentType: false,
		    success: res => console.log(res.message),
		    error: err => console.error(err)
		});
		
	 })		
	const transfer = function(attachmentNo) {
	    return new Promise((resolve, reject) => {
	        $.ajax({
	            url: "/attachment/item",
	            method: "get",
	            data: { attachmentNo },
	            xhrFields: { responseType: "blob" },
	            success: function (blob, status, xhr) {
	                const disposition = xhr.getResponseHeader("Content-Disposition");
	                let fileName = "unknown";

					if (disposition) {
					  // filename* (UTF-8 URL 인코딩) 우선 처리
					  const fileNameStarMatch = disposition.match(/filename\*\=([^;]+)/);
					  if (fileNameStarMatch) {
					    const encodedFileName = fileNameStarMatch[1].split("''")[1];
					    fileName = decodeURIComponent(encodedFileName);
					  } else {
					    // 없으면 ASCII filename 사용
					    const fileNameMatch = disposition.match(/filename=([^;]+)/);
					    if (fileNameMatch) {
					      fileName = fileNameMatch[1].replace(/['"]/g, "");
					    }
					  }
					}

	                const file = new File([blob], fileName, { type: blob.type });
	                resolve(file);
	            },
	            error: function (xhr, status, error) {
	                reject(error);
	            }
	        });
	    });
	};
	
	
	
	$(".save-btn").click(function(){
		
		
		
		$("#itemUpdate").submit();
	});
	
    $('input[name="itemGender"]').on('change', function () {
        $('input[name="itemGender"]').not(this).prop('checked', false);
    });
	
	$(document).ready(function() {
	   
		var existingGender = $("#gender").val();
		//console.log("existingGender:", existingGender);
		//console.log($("#gender").val());
	    if (existingGender.includes("M")) {
	        $('#maleCheck').prop('checked', true);
	    }
	    if (existingGender.includes("F")) {
	        $('#femaleCheck').prop('checked', true);
	    }
	});
	
	   

	    $('.image-btn').each(function(index) {
	        $(this).on('click', function() {
	            $(this).closest('.col-3').find('input[type="file"]').click();
	        });
	    });

	    $('input[type="file"]').each(function(index) {
	        $(this).on('change', function(e) {
	            var file = e.target.files[0];
	            if (file) {
	                fileList[index] = file;
	            } else {
	                fileList[index] = null;
	            }
	        });
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
	   
		   $(document).ready(function() {
		     // 썸네일 클릭하면 대표 이미지 교체
		     $(document).on('mouseenter', '.image-btn', function () {
		       const clickedSrc = $(this).attr('src');
		       $('#photoView').attr('src', clickedSrc);
		     });

		     // input[type=file] 바뀌었을 때 썸네일 및 대표 미리보기도 변경
		     $('.input').on('change', function () {
		       const reader = new FileReader();
		       const imgTag = $(this).prev('.image-btn')[0]; // 연결된 썸네일 img

		       reader.onload = function (e) {
		         const newSrc = e.target.result;
		         imgTag.src = newSrc;

		         // 대표 이미지도 새로 선택한 걸로 바꾸기
		         $('#photoView').attr('src', newSrc);
		       };

		       if (this.files[0]) {
		         reader.readAsDataURL(this.files[0]);
		       }
		     });
		   });

	   
	   $(".form-check").submit(function(){
	           return status.ok();
	       });
	
});