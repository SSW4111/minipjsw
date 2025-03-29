$(function(){
           var status = {
             
               nickname : false,
               ok : function(){
                   return nickname;
               }
           };

           $(".photo-btn").click(function(){
               $("#fileInput").click();
           });

           $("#fileInput").change(function(e){
               var reader = new FileReader();

               reader.onload = function(event){
                   $("#photoView").attr("src",event.target.result);
               }
               var file = e.target.files[0];
               if(file){
                   reader.readAsDataURL(file);
               }else{
                   $("#photoView").attr("src","./basic.png");
               }
           })
		   
		   $(".btn-save").click(function(){
			
		   })
		   

           $("[name=usersContact]").on("input",function(){
               var phone = $(this).val().replace(/[^0-9]/g, ''); 
               if (phone.length >= 4 && phone.length <= 7) {
                   phone = phone.replace(/(\d{3})(\d{0,4})/, '$1-$2');
               } else if (phone.length > 7) {
                   phone = phone.replace(/(\d{3})(\d{4})(\d{0,4})/, '$1-$2-$3');
               }
               $(this).val(phone);
           });
           $("[name=usersContact]").blur(function(){
               var regex = /^010-\d{4}-\d{4}$|^$/;
               var isValid = regex.test($(this).val());
               $(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
           });
		   
		   $("[name=usersNickname]").on("input",function(){
		                 var nickname = $(this).val().replace(/[^ㄱ-ㅎㅏ-ㅣ가-힣0-9]/g, ''); 
		                 $(this).val(nickname);
		             });

           $("[name=usersNickname]").blur(function(){
              var regex = /^[가-힣0-9]{1,12}$/;
               var isValid = regex.test($(this).val());
               $(this).removeClass("is-valid is-invalid").addClass(isValid ? "is-valid" : "is-invalid");
               status.nickname = isValid;
           })
		   $(".form-check").submit(function(){
		   	          return status.ok();
		   });
           
       })
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   