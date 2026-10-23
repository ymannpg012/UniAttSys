import { launchCalendarCreation, takePeriodStartAndEnd, fetchTimeList} from "/scripts/common/calendarCreation.js";


let response = "";
let displayNode = "";
let userId = "";

$(document).ready(async function(){
	
	//take the payroll cycle of the user according to its employment_role
	response = await fetchCycleData();
	displayNode = $("#test");
	userId = "71e384f5-7f6a-4fd9-9944-0e33a6d081e2";
	
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
	
	//displayCycleValues();
	let cycleRange = takePeriodStartAndEnd(displayNode);
	fetchTimeList(cycleRange.cycleStart, cycleRange.cycleEnd, response.cycleDuration, userId);
})