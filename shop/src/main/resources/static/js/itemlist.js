$(function(){
	$(".delete-btn").click(function(){
	//	console.log("deldledeldeldel");
	var itemNo = $(this).data('item-no');
//		   console.log("nonono = ", itemNo);
var choice = window.confirm("delete??");
			if(choice){
				//console.log("delete")
				$.ajax({
					url:"/rest/admin/item/delete",
					method:"get",
					data:{itemNo:itemNo},
					success:function(response){
						if(response){
							$("#ioList").empty();
								callPage();
						}								
					}
				})
			}

	});
	var dup = 0;
	
	$(".manage-io-btn").click(function(){
	
		var non = $(this).data('item-no');	
		dup = non;
		console.log("modal no = " + non);
		$.ajax({
			url:"/rest/admin/item-io/list",
			method:"get",
			data:{itemNo : non},
			success: function(list){
				if(list != null){
				displayItems(list)
				}
			},
		})
	});
	
	function callPage(){
		$.ajax({
					url:"/rest/admin/item-io/list",
					method:"get",
					data:{itemNo : dup},
					success: function(list){
						if(list != null){
						displayItems(list)
						}
					},
		})
	}
	

	$(".modify-io-btn").click(function(){
		console.log("h2h2h");
		console.log("dup = " + dup);
		
		const sizeName = $(".trtrtr").find('td').eq(1).text().trim(); //
		//  const inputVal = tr.find('input[name="modify-input"]').val();
		console.log(sizeName);
		//console.log(inputVal);
		
	})	;
	
	var modifiedInputList = [];
//	$("[name=modify-input]").input(function(){
	$(document).on('input', '[name=modify-input]', function(){
	/*	$("#ioList").empty();
		callPage();*/
		//dup - itemNo
		//$(document).on()
		console.log("this val = " + $(this).val());
		console.log($("[name=modify-input")).val();
		
	})
	
	function displayItems(items){
		const container = $("#ioList");
		
			items.forEach(item=>{
				const field = $(`
					      <tr class="trtrtr">
					        <td data-item-no="${item.itemNo}">${item.itemNo}</td>
					        <td>${item.sizeName}</td>
					        <td>${item.itemIoTotal}</td>
					        <td>
					          <input type="number" inputmode="numeric" class="form-control" name="modify-input" style="width: 80px;" value=0>
							  
					        </td>
					        <td>
					        </td>
							<td><i class="fa-solid fa-trash text-danger ms-2 delete-io-btn"></i></td>
					      </tr>
					    `);

				container.append(field);
			});
	};
	
	$(".finish-btn").click(function(){
		location.reload();
	})


	
	$(".plus-btn").click(function(){
		console.log("dup");
		console.log(dup);
		var size = $("[name=sizeName]").val()
		console.log("size name = " + size);
		var inIo = $("[name=itemIoIn]").val()
		console.log("in iinin = " + inIo);
		var regex = /^[0-9]+$/;
		var isvalid = regex.test(inIo);
		console.log(isvalid);
		if($("select[name=sizeName]").val().length > 0 && isvalid){
		
				$.ajax({
				url:"/rest/admin/item-io/add",
				method:"post",
				data:{itemNo : dup, sizeName: size, itemIoIn:inIo},
				success: function(success){
					if(success){
						console.log("wlqrkwk");
						$("#ioList").empty();
						$("[name=sizeName]").val("");
						$("[name=itemIoIn]").val("");
							callPage();
					}
				},
			})
		}
		else{
			window.alert("수량과 사이즈선택 모두다 하셔야합니다");
						
		}
});
	
	
	
	$(document).on('click', '.delete-io-btn', function(){
				const sizeName = $(this).closest("tr").find("td").eq(1).text();
		$.ajax({
				url:"/rest/admin/item-io/delete",
				method:"get",
				data:{itemNo : dup, sizeName : sizeName},
				success: function(success){
					if(success){
						console.log("dkssud");
						$("#ioList").empty();
							callPage();
					}
			}
		
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	});
	
	
	
	
	




























