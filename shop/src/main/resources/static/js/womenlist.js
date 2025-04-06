$(function() {
		
	var currentPage = 1;
	var size = 12;
	
	$(".more-btn").click(function(){
		currentPage += 1;
		callPage(currentPage);
		//console.log("현재페이지3 : " + currentPage);
	});
	
	function callPage(){
		$.ajax({
			url:"/rest/item/listW",
			method:"post",
			data:{page:currentPage, size:size},
			success:function(list){
				//console.log("현재페이지1 : " + currentPage);
				displayItems(list);
				//console.log(list.attachmentList);
				//console.log(list.isLastPage);
				//console.log(list.listM);			
				if(list==null){
					$(".more-btn").addClass("d-none");
				}
			}
		})
	}
	callPage();
	
	function displayItems(items) {
	          const container = $('#itemListContainer');
//			  console.log(items.attachmentList.length());
			 // console.log("현재페이지2 : " + currentPage);
	          items.listW.forEach(item => {
				const att = items.attachmentList[item.itemNo][0];
				
	              const itemCard = $(`
					<a class="col mb-4 ms-1" href="/item/detail?itemNo=${item.itemNo}">
					                <div class="card " style="width: 18rem;">
					                    <div class="card-header d-flex ">
					                        <span>${item.itemTitle}</span> 
					                        <i class="fa-regular fa-heart ms-auto like-heart"></i>
					                    </div>
					                    <img src= "/attachment/download?attachmentNo=${att}" class="card-img-top" >
					                    <div class="card-footer">
					                        <ul class="list-group">
					                            <li class="list-group-item">${item.itemNo}</li>
					                            <li class="list-group-item">${item.itemCategory}</li>
					                            <li class="list-group-item">${item.itemDetail}</li>
					                            <li class="list-group-item">${item.itemAveStar}</li>
					                        </ul>
					                    </div>
					                </div>
					            </a>
	              `);
	              
	              container.append(itemCard);
				
			  
				    //  console.log(items.attachmentList[item.itemNo][0]);
	          });
	      }
	
	
	
		  
$(".like-heart").click(function(){
	
});


$(function () {
           $(document).ready(function() {
    // 검색 입력이 있을 때마다 AJAX 호출
    $(".search").on("input", function() {
	
        var searchParams = {
            memberId: $("#memberId").val(),
            memberNickname: $("#memberNickname").val(),
            memberEmail: $("#memberEmail").val(),
            memberGender: $(".memberGender:checked").val(),
            memberLevel: $(".memberLevel:checked").val()
        };
	

        // 서버에 검색 요청 보내기
        $.ajax({
            url: '/rest/member/search',   // 요청할 URL (컨트롤러의 주소)
            method: 'GET',            // 요청 방식 (GET)
            data: searchParams,      // 검색 조건 데이터
            success: function(response) {
                // 서버에서 받은 검색 결과를 테이블에 삽입
                var tableBody = $("#result tbody");
                tableBody.empty();  // 이전 결과 지우기

                // 검색 결과가 있을 경우
                if (response.length > 0) {
                    response.forEach(function(list) {
                        var row = "<tr>" +
                            "<td>" + list.memberId + "</td>" +
                            "<td>" + list.memberNickname + "</td>" +
                            "<td>" + list.memberEmail + "</td>" +
                            "<td>" + list.memberGender + "</td>" +
                            "<td>" + list.memberLevel + "</td>" +
                            "</tr>";
                        tableBody.append(row);
                    });
                } else {
                    tableBody.append("<tr><td colspan='4'>검색 결과가 없습니다.</td></tr>");
                }
            },
            error: function(xhr, status, error) {
                console.log("검색 중 오류 발생:", error);
            }
        });
    });
});



          
           $("#M").on("change", function () {
               var checkM = $("#M").prop('checked');
               var checkF = $("#F").prop('checked');

              
               if (checkM && checkF) {
                   $("#F").prop('checked', false);
               }
              
           });

           $("#F").on("change", function () {
               var checkM = $("#M").prop('checked');
               var checkF = $("#F").prop('checked');

               
               if (checkM && checkF) {
                   $("#M").prop('checked', false);
               }
              
           });

           $("#user").on("change", function(){
               var checkU = $("#user").prop('checked');
               var checkA = $("#admin").prop('checked');

               if(checkU && checkA) {
                   $("#admin").prop('checked', false);
               }
             
           });

           $("#admin").on("change", function(){
               var checkA = $("#admin").prop('checked');
               var checkU = $("#user").prop('checked');

               if(checkA && checkU){
                   $("#user").prop('checked', false);
               }
               
           });

           function sub(){
               var f = document.querySelector(".searchTest");
               f.submit;
           }
           // $(".input-box").on("input", function () {
           //     var f = document.querySelector(".searchTest");
           //     f.submit;
           // });
       });




});