$(function(){
	$(".delete-btn").click(function(){
	//	console.log("deldledeldeldel");
	});
	
var nono = 0;
	
	$("#io").click(function(){
		var itemNum = $(this).closest("tr").find("td[data-item-no]").data("item-no");
		nono = itemNum;
		$.ajax({
			url:"/rest/admin/item-io/list",
			method:"get",
			data:{itemNo : itemNum},
			success: function(list){
				if(list != null){
				displayItems(list)
				}
			},
		})
	});
	
	
	function displayItems(items){
		const container = $("#ioList");
		
			items.forEach(item=>{
				const field = $(`
					      <tr >
					        <td data-item-no="${item.itemNo}">${item.itemNo}</td>
					        <td>${item.sizeName}</td>
					        <td>${item.itemIoTotal}</td>
					        <td>
					          <input type="number" inputmode="numeric" class="form-control" style="width: 80px;" value=0>
					        </td>
					        <td>
					        </td>
							<td><i class="fa-solid fa-trash text-danger ms-2 delete-io-btn"></i></td>
					      </tr>
					    `);

				container.append(field);
			});
	};
	

	
			
	
	$(".plus-btn").click(function(){
		var itemNum =nono;
		var size = $("[name=sizeName]").val()
		var inIo = $("[name=itemIoIn]").val()
		console.log(nono);
		console.log("nononono");
				$.ajax({
				url:"/rest/admin/item-io/add",
				method:"post",
				data:{itemNo : itemNum, sizeName: size, itemIoIn:inIo},
				success: function(success){
					if(success){
						console.log("wlqrkwk");
						$(".insert-box").empty();
						$("#ioList").empty();
						$("#io").click();
					}
				
									
				},
			})
	});
	
	$(document).on('click', '.delete-io-btn', function(){
		var itemNum = nono;
		 const sizeName = $(this).closest('tr').find('td').eq(1).text(); 
		
		$.ajax({
				url:"/rest/admin/item-io/delete",
				method:"get",
				data:{itemNo : itemNum, sizeName : sizeName},
				success: function(success){
					if(success){
						console.log("dkssud");
						$("#ioList").empty();
						$("#io").click();
					}
			}
		});
	});
	
	
	
	
	
});




























