import * as tools from "/scripts/common/tools.js";
import { launchCalendarCreation, takePeriodStartAndEnd, fetchTimeList } from "/scripts/common/calendarCreation.js";


$(document).ready(async function(){
 
	
	const response = await tools.getEmploymentRoles();
	const roles = response.roles;
	localStorage.setItem("roles", JSON.stringify(response.roles));
	
	roles.forEach(role => {
			$('#selection').append(
				`<option value = '${role.roleId}'>${role.roleName}</option>`
		);
	});
	
	displayEmployeesByRole();
	
})

function takeEmployeesByRole(){
	return $.ajax({
		url: "/fetchEmployeesByRoles",
		type: "POST",
		data:{
			role: $('#selection').val()
		},
	})

}

async function displayEmployeesByRole(){
	
	const response = await takeEmployeesByRole();
	
	console.log(response.userInfo)
	$('#employees').empty();
					
	let users = response.userInfo;
					
	users.forEach(user =>
						
		$('#employees').append(
			`<tr>
				<td data-user-id="${user.userId}">${user.firstName + ' ' + user.lastName}</td>
			</tr>`
		)
						
	)
	
	let userId = $('#employees td:first').data('user-id');
	createCalendarForUser(userId)
	
}
		
$('#selection').on('change', function(){
	
	console.log($('#selection').val());
	
	displayEmployeesByRole();
	
})




$('tbody').on('click', 'td', async function(){
	
	let userId = $(this).data('user-id');
	createCalendarForUser(userId)
	
})

async function getSelectedRoleData(){
	
	let roleName;
	let roleId = $('#selection').val()
	let roles = JSON.parse(localStorage.getItem("roles"));
		
	for(const role of roles){
			
		if(role.roleId == roleId){
			roleName = role.roleName;
		}
			
	}
		
	let cycleData = await tools.getCycleData(roleName);
	
	console.log(cycleData)
	
	return cycleData;
	
}

$(document).on('change', '#test', function(){
	
	//displayCycleValues();
	let cycleRange = takePeriodStartAndEnd(selectedNodeValue);
	fetchTimeList(cycleRange.cycleStart, cycleRange.cycleEnd, selectedUserDuration, selectedUserId);
		
})

let selectedNodeValue = "";
let selectedUserId = "";
let selectedUserDuration = "";

async function createCalendarForUser(userId){
	
	let cycleData = await getSelectedRoleData();
	const displayNode = $("#test");
		
	selectedUserId = userId;
	selectedUserDuration = cycleData.cycleDuration;
	selectedNodeValue = displayNode;
		
	launchCalendarCreation(cycleData, displayNode, userId);
	
}
