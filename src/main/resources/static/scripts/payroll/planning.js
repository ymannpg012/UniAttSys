import {getEmploymentRoles} from "/scripts/common/tools.js";

$(document).ready(async function(){

	const response = await getEmploymentRoles();
	const roles = response.roles;
	localStorage.setItem("roles", JSON.stringify(response.roles));
		
	roles.forEach(role => {
			$('#selection').append(
				`<option value = '${role.roleId}'>${role.roleName}</option>`
		);
	});
	
	createPayrollCycle();
	
})

var schedules = [];

$('#payroll-type').on('change', function(){
	
	if($(this).val() === "custom"){
		
		$('#custom-payroll-duration').html(`
												<label for="custom-duration">Duration in days:&nbsp;</label>
												<input type="number" id="custom-duration">
												<p id="duration-error-message" style="color: red"></p>
											`);
		
	}else{
		$('#custom-payroll-duration').html(``);
	}
	
})

$(document).on('blur', '#custom-duration', function() {

	let durationDays = Number($(this).val());
		
	switch(durationDays){
		case 30:
					
			$('#payroll-type').val("monthly");	
			break;
			
		case 15:
				
			$('#payroll-type').val("bi-monthly");			
			break;
				
		case 7:
				
			$('#payroll-type').val("weekly");		
			break;
			
		case 1:
				
			$('#payroll-type').val("daily");					
			break;
				
	}

});

$(document).ready(function(){
	takeDateToday();
});

function takeDateToday(){
	
	const dateToday = new Date();
	const formattedDate = dateToday.toISOString().split('T')[0];
	$('#payroll-start').val(formattedDate);
	
}

function createPayrollCycle(){
	
	schedules = [];
	let startDate; 	 
	let payrollType = payrollTypeValue($('#payroll-type').val());
	
	startDate = new Date($('#payroll-start').val());
	
	if(startDate == 'Invalid Date'){
		
		startDate = new Date();
		
	}
	
	let forPlan = new Date(startDate);
	const nextYear = startDate.getFullYear() + 1;
	let index = 1;
	
	while (forPlan.getFullYear() < nextYear) {

	    let cycleStart = new Date(forPlan);

	    let cycleEnd = new Date(forPlan);
	    cycleEnd.setDate(cycleEnd.getDate() + payrollType - 1);
		
	    schedules.push({
			index,
			cycleStart: cycleStart.toISOString().split('T')[0],
			cycleEnd: cycleEnd.toISOString().split('T')[0]
	    });

	    // Move to the next cycle
	    forPlan = new Date(cycleEnd);
	    forPlan.setDate(forPlan.getDate() + 1);
		index++;
	}
	
	console.log(schedules);
	 
	 displayPayrollCycle(schedules);
	
}

function displayPayrollCycle(schedules) {

	$("table").remove();
	
    let html = `
	
	<div id="table-container">
        <table>
            <thead>
                <tr>
					<th>Number</th>
                    <th>Start</th>
                    <th>End</th>
                </tr>
            </thead>
            <tbody>
    `;

    schedules.forEach((cycle) => {

        html += `
            <tr>
				<td>${cycle.index}</td>
                <td>${cycle.cycleStart}</td>
                <td>${cycle.cycleEnd}</td>
            </tr>
        `;

    });

    html += `
            </tbody>
        </table>
	</div>
    `;

    $('#cycles').html(html);

}

function payrollTypeValue(payrollType){
			
		switch(payrollType){
			case "monthly":
						
				return 30;
				
			case "bi-monthly":
					
				return 15;
					
			case "weekly":
					
				return 7;
				
			case "daily":
					
				return 1;
			
			case "custom":
				
				const customValue = Number($('#custom-duration').val());
				
				return customValue;
		}
	
}

$('#publish').on('click', function(){
	
	storePlan();
	
})


$('#createPayroll').on('click', function(){
	
	createPayrollCycle();
	
})

function storePlan(){
	
	let assignment = $('#selection').val();
	let cycleType = $('#payroll-type').val();
	let cycleDuration = payrollTypeValue(cycleType);
	
	console.log(assignment);
	
	$.ajax({
		url: "/payroll/create",
		type: "POST",
		data:{
			   type: cycleType,
		       assignment: assignment,
		       schedule: JSON.stringify(schedules),
			   duration: Number(cycleDuration)
		      },
		success: function(response) {
		    console.log("Saved successfully", response);
			
			alert("payroll created successfully.");
			
			$("#publish").prop("disabled", true);
			
		},
		error: function(xhr, status, error) {
		    console.error("Error:", error);
		}
	})
	
	console.log(schedules);
	console.log(cycleType);
}