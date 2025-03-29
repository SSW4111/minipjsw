$(function() {
	var status = {
		email: false,
		pw: false,
		login: false,
		ok: function() {
			return this.email && this.pw && this.login;
		}
	}
	$("#loginEmail").blur(function() {
		var regex = /^[A-Za-z0-9]+@[A-Za-z0-9.]+$/;
		var isValid = regex.test($(this).val());
		$(this).removeClass("is-valid", "is-invalid").addClass("is-valid", "is-invalid");
		status.email = isValid;
	})

	$("#loginPw").blur(function() {
		var regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$])[A-Za-z0-9!@#$]{8,16}$/;
		isValid = regex.test($(this).val());
		$(this).removeClass("is-valid", "is-invalid").addClass("is-valid", "is-invalid");
		status.pw = isValid;
	})


	$(".login-btn").click(function(event) {
		event.preventDefault();
		var email = $("#loginEmail").val();
		var pw = $("#loginPw").val();
		$.ajax({
			url: "/rest/users/login",
			method: "post",
			data: { usersEmail: email, usersPw: pw },
			success: function(response) {
				if (!response) {
					window.alert("로그인 정보가 옳지 않습니다");
				}
				else {
					$('#loginModal').modal('hide');
					$("#login-modal-open").hide();
					$("#logout-modal-open").show();

				}
			}
		});



		/*("form-check").submit();
		window.alert("wegawe");
		$(this).modal("hide");*/
	});

	$("#logoutYes").click(function() {
		$.ajax({
			url: "/rest/users/logout",
			method: "post",
			success: function() {
				$('#logoutModal').modal('hide');
				$("#login-modal-open").show();
				$("#logout-modal-open").hide();
				
			}
		});
	});


	$("form-check").submit(function() {

		return status.ok();
	});



});