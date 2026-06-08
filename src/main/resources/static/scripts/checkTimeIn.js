$(document).ready(function(){
	checkTimeIn();
})

function checkTimeIn(){
	$.ajax({
			
			url: '/checkTimeIn',
			type: 'POST',
			contentType: 'application/json',
			success: function(response){
				
				console.log(response)
				console.log(response.hasTimeInToday)
				
				if(response.hasTimeInToday && response.hasTimeOutToday){
					
					$('#time-in').prop('disabled', true);
					$('#time-out').prop('disabled', true);
					
				}else if(response.hasTimeInToday){
					
					$('#time-in').prop('disabled', true);
					$('#time-out').prop('disabled', false);
					
				}else{
					
					$('#time-in').prop('disabled', false);
					$('#time-out').prop('disabled', true);
					
				}
				
				/**
				if(response){
					alert("true")
					
					$('#time-out').prop('disabled', true);
					$('#time-in').prop('disabled', false);
					
				}else{
					alert("false")
					
					$('#time-in').prop('disabled', true);
					
				}
				*/
			},
			error: function(response){
				
				console.log(response)
				
				/**
				alert("loaded test error")
				
				$('#time-out').prop('disabled', true);
				$('#time-in').prop('disabled', true);
				*/
			}
			
		})
}