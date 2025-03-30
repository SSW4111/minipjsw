$(function() {
	var status = {
		usersEmail: false,
		usersPw: false,
		contact: false,
		nickname: false,
		ok: function() {
			return this.usersEmail && this.usersPw && this.contact && this.nickname;
		}
	}
	$("#joinEmail").blur(function() { // 형식 검사
		var regex = /^[A-Za-z0-9]+@[A-Za-z0-9.]+$/;
		var isValid = regex.test($(this).val());
		$(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
		status.usersEmail = isValid;

	});

	$("#pwCheck").blur(function() {
		var regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$])[A-Za-z0-9!@#$]{8,16}$|$/;
		isValid = regex.test($(this).val());
		$(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
		status.usersPw = isValid;
	});
	$("[name=usersPwRe]").blur(function(){
	             var usersPw = $("#pwCheck").val();
	             var target = $(this).val();
	             var isValid;
	             if(usersPw == target){
	                 isValid = true;
	             }
	             else{
	                 isValid=false;
	             }
	             $(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid":"is-invalid");
	             status.usersPwRe = isValid;
			 /*  if(status.ok){
					$(".next").prop("disabled",false);
			   }*/
			   if(status.usersPw && status.usersPwRe){
				$(".next").prop("disabled",false);
			   }
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
	




		$("form-check").submit(function() {
			$("[name], #joinEmail, #pwCheck").trigger("blur");
			return status.ok();
		});



	});