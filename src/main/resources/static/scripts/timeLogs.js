import { launchCalendarCreation, takePeriodStartAndEnd, displayCycleValues } from "/scripts/common/calendarCreation.js";

$(document).ready(async function(){
	
	//take the payroll cycle of the user according to its employment_role
	const response = await fetchCycleData();
	const displayNode = $("#test");
	const userId = "71e384f5-7f6a-4fd9-9944-0e33a6d081e2";
	
	launchCalendarCreation(response, displayNode, userId);
		
})

function fetchCycleData() {
    return $.ajax({
        url: "/timeLogs",
        type: "POST",
        data: {
            assignment: "REGULAR"
        }
    });
}

$(document).on('change', '#test', function(){
	
	displayCycleValues();
	takePeriodStartAndEnd();
		
})