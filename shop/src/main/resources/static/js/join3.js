$(function() {
	var status = {
		contact: false,
		nickname: false,
		ok: function() {
			return this.contact && this.nickname;
		}
	};

	$(".photo-btn").click(function () {
	               $("#fileInput").trigger("click");
	           });
	           var backupFile = null
	           //파일 선택시 백업
	           $("#fileInput").change(function(){
	               var reader = new FileReader(); 
	               var file = this.files[0];
	               
	               //정상 선택
	               if(file){
					  var FileTypes = ['image/png', 'image/jpeg']; 
					  if (!FileTypes.includes(file.type)) { //배열 안에 특정 값이 포함되어 있는지 확인하는메서드
					        alert("허용되지 않는 파일 형식입니다.");
					        $(this).val(''); // 초기화
					        return;
					    }
				
	                reader.onload = function(e) { 
	                $("#photoView").attr("src", e.target.result);
	               }
	               backupFile = file;
	               reader.readAsDataURL(file); 
	               return;
	           }
	             //  취소를 눌렀을경우 백업파일있을경우
	               if(backupFile){
	                  var inputFile = $("#fileInput")[0];
	                  var dataTransfer = new DataTransfer();
	                  dataTransfer.items.add(backupFile) 
	                  inputFile.files = dataTransfer.files;
	                  return;
	               }
	           }    
	           );		
	    	///프로필 삭제
			/*$("#deleteBtn").click(function(){
	            $("#myPhoto").attr("src","https://placehold.co/150x150");
	               var inputFile = $("[name=memberProfile]")[0];
	               $(inputFile).val("");
	               return;
			 });*/

	
	
	
	
	
	
	/*$(".photo-btn").click(function() {
		$("#fileInput").click();
	});

	$("#fileInput").change(function(e) {
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
				$("#photoView").attr("src", event.target.result);
			}


			if (file) {
				reader.readAsDataURL(file);
			} else {
				$("#photoView").attr("src", "./basic.png");
			}
		}
	});
	*/
		
		
		
	$(".btn-save").click(function() {
		$("#profileModal").modal('hide');
		console.log($("[name=usersProfile]").text());
	});


	$("[name=usersContact]").on("input", function() {
		var phone = $(this).val().replace(/[^0-9]/g, '');
		if (phone.length >= 4 && phone.length <= 7) {
			phone = phone.replace(/(\d{3})(\d{0,4})/, '$1-$2');
		} else if (phone.length > 7) {
			phone = phone.replace(/(\d{3})(\d{4})(\d{0,4})/, '$1-$2-$3');
		}
	
		$(this).val(phone);
	});
	$("[name=usersContact]").blur(function() {
		var regex = /^010-\d{4}-\d{4}$/;
		var isValid = regex.test($(this).val());
		$(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
		status.contact = isValid;
	});

	

	$("[name=usersNickname]").blur(function() {
		var regex = /^[가-힣0-9]{1,12}$/;
		var isValid = regex.test($(this).val());
		$(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
		status.nickname = isValid;
	})
	$(".form-check").submit(function() {
			
		$("[name]").trigger("blur");
	
		return status.ok();
	});

	
	
	
	
	
})



























