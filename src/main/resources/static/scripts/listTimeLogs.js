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
				response.forEach(function(user){
				
					 row = `
							<tr>
								<td class="user-id">${user.timeInDate}</td>
								<td class="user-id">${user.timeInTime}</td>
							</tr>
						   `
								
					$("tbody").append(row);
				
				})
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