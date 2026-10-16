$(document).ready(function(){

	$.ajax({
		
		url: '/logs-summary',
		type: 'POST',
		contentType: 'application/json',
		
		success: function(response){
			
			console.log("success");
			console.log(response);
			
			let row;

			if(response.length !== 0){
				
				localStorage.setItem("timeIns", JSON.stringify(response));
				
				listTimeInsForMonth();
				
			}else{
				row = `
						<tr>
							<td colspan="9">NO DATA</td>
						</tr>
					  `
					  
				$("tbody").append(row);
			}
		
		},
		
		error: function(response){
			
			console.log("failed");
			console.log(response)
		
		}
		
	})

})

$('#month').on('change', function(){
	
	let month = $('#month').val();
	
	listTimeInsForMonth(month);
	
});

function listTimeInsForMonth(month){
	
	$("tbody").empty();
	
	console.log(month === undefined)
	
	const item = JSON.parse(localStorage.getItem("timeIns"));
	
	if(month === undefined){
	
		 month = new Date().getMonth() + 1;
		 
		 month > 10 ? month : month = "0" + month;
		 
		 $('#month').val(month);
		 
		 console.log(month);
			
	}
					
	item.forEach(function(user){
					
		if(user.timeInDate.split("-")[1] === month){
						 	
			row = `
					<tr>
						<td class="user-id">${user.timeInDate}</td>
						<td class="user-id">${user.timeInTime}</td>
					</tr>
				`
									
				$("tbody").append(row);
							
		}
	})
}
