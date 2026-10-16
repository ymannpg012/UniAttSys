export function getEmploymentRoles(){
	
	return $.ajax({
			url: "/fetchRoles",
			type: "POST",
		})
}

export function getEmployeeTimeRecords(userId){
	
	return $.ajax({
			
		url: "/payroll/map",
		type: 'POST',
		data:{
			startDay: cycleStart,
			endDay: cycleEnd,
			userId: userId
		}
	
	});
}

export function getCycleData(assignedTo) {
    return $.ajax({
        url: "/timeLogs",
        type: "POST",
        data: {
            assignment: assignedTo
        }
    });
}

export function getTimeList(cycleStart, cycleEnd, userId){
	return $.ajax({
			url: "/payroll/map",
			type: 'POST',
			data:{
				startDay: cycleStart,
				endDay: cycleEnd,
				userId: userId
			},
		})
}

export function formatDateToday(){
	
	const todayDate = new Date();
	
	const today = todayDate.toISOString().split("T")[0];
	
	let todayMonth = String(getMonth(today)).padStart(2, "0");
	let todayDay = String(getDay(today)).padStart(2, "0");
	let todayYear = getYear(today);
	
	const transformedDate = todayYear + '-' + todayMonth + '-' + todayDay;
	
	return transformedDate;
	
}

export function getMonth(date){
	return date.split('-')[1];
}

export function getDay(date){
	
	let day = date.split('-')[2];
	
	return Number(day);
}

export function getYear(date){
	return date.split('-')[0];
}

	