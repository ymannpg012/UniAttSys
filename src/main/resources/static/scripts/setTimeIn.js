function setTimeIn(){
	
	alert("called")
	
	$.ajax({
		
		url: "/time-in/save",
		type: "POST",
		
		success: function(response){
			
			checkTimeIn();
			
			alert("success")
		},
		error: function(response){
			
			alert("failed")
			
		}
		
	})
}

function setTimeOut(){
	
	alert("out called")
	
	$.ajax({
		
		url: "/time-out/save",
		type: "POST",
		
		success: function(response){
			
			checkTimeIn();
			
			alert("out success")
			
			$('#time-out').prop('disabled', true);
			
		},
		error: function(response){
			
			alert("out failed")
			
		}
		
	})
}