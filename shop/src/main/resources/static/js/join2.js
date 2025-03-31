$(function(){
           var status={
               usersPw : false,
               usersPwRe : false,
               ok:function(){
                   return this.usersPw && this.usersPwRe;
               }
           };
           $("#pwCheck").blur(function(){
               var regex =  /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$])[A-Za-z0-9!@#$]{8,16}$/;
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
		   $(".form-check").submit(function(){
		          return status.ok();
		      });

       });