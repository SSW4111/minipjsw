$(function(){
            var status={
                usersEmail : false,
                certNumber : false,
                ok:function(){
                    return this.usersEamil && this.certNumber;
                }
            };

            $("#joinEmail").blur(function(){ // 형식 검사
                var regex = /^[A-Za-z0-9]+@[A-Za-z0-9.]+$/;
                var isValid = regex.test($(this).val());
                $(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
                status.usersEmail = isValid;
				if(status.usersEmail){
					$(".cert").prop("disabled",false);
				}
            });

            $(".cert").click(function(){ // 인증 번호 생성
                var email = $("#joinEmail").val();
				console.log(email);
                $.ajax({
                    url:"/rest/cert/send",
                    method:"post",
                    data:{certEmail:email},
                    success:function(response){
                         if(response == true ){
                            $(".cert-wrapper").fadeIn();
                        }

                    },
                    beforeSend: function(){
                        if(status.usersEmail){
                            $(".cert").prop("disabled",true);
							$(".btn-send-cert").find("span").text("이메일 발송중");
                            $(".cert").find("i").removeClass("fa-paper-plane").addClass("fa-spinner fa-spin");
                        }
                        
                    },
                    complete: function(){
                        $(".cert").find("i").removeClass("fa-spinner fa-spin").addClass("fa-paper-plane");
                        $(".cert").prop("disabled",false);
                    }
                })
            })

            $(".confirm-cert").click(function(){ // 인증번호 확인
                var email = $("#joinEmail").val();
                var number = $("[name=certNumber]").val();
				
                $.ajax({
                    url:"/rest/cert/check",
                    method:"post",
                    data:{certEmail:email, certNumber:number},
                    success:function(response){
                        if(response == true){
							$(".next").prop("disabled",false);
                        }
                        $("[name=certNumber]").removeClass("is-valid is-invalid").addClass(response ? "is-valid" : "is-invalid");
                        console.log(response);
                        status.certNumber=response;
                    }
                })
            })
	
			$(".form-check").submit(function(){
				          return status.ok();
			});
        });
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		