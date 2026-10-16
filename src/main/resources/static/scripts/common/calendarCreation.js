import { formatDateToday, getYear, getMonth, getDay } from "/scripts/common/tools.js";

export function launchCalendarCreation(response, displayNode, userId){
	
		const cycles = JSON.parse(response.cycleDates);
		const cycleDuration = response.cycleDuration;
		
		displayAllPeriodsInDropdown(cycles, displayNode);
		displayPeriodForCurrentDate(cycles, displayNode);		
		displayCycleValues();
		
		let cycleRange = takePeriodStartAndEnd(displayNode);
		
		fetchTimeList(cycleRange.cycleStart, cycleRange.cycleEnd, cycleDuration, userId);
		
}


function displayPeriodForCurrentDate(cycles, displayNode){
	
	let transformedDate = formatDateToday();
	
	displayNode.val(getEndAndStartOfPeriodForCurrentdate(cycles, transformedDate));
	
}


function getEndAndStartOfPeriodForCurrentdate(cycles, transformedDate){
	
	for(const data of cycles){
										
		if(getMonth(data.cycleStart) <= getMonth(transformedDate) &&  getMonth(data.cycleEnd) >= getMonth(transformedDate)){
											
			return JSON.stringify({
								    cycleStart: data.cycleStart,
								    cycleEnd: data.cycleEnd
								   })		
				
		}
										
	}
}

function displayAllPeriodsInDropdown(cycles, displayNode){
	
	//display the Object's values individually as options 
	
	//displayNode.options.length = 0;
	
	cycles.forEach(cycle => {
		displayNode.append(
			`<option value='{"cycleStart":"${cycle.cycleStart}","cycleEnd":"${cycle.cycleEnd}"}'>${cycle.index}</option>`
		);
	});
	
}

export function takePeriodStartAndEnd(displayNode){
	
	if(displayNode == undefined || displayNode == null){
		
		displayNode = $('#test');
		
	}
	
	var cycleRange = JSON.parse(displayNode.val());
	
	
	return cycleRange;
	
}

export function displayCycleValues(){
	$('#values').text($('#test').val());
}

export function fetchTimeList(cycleStart, cycleEnd, cycleDuration, userId){
	
	$.ajax({
		url: "/payroll/map",
		type: 'POST',
		data:{
			startDay: cycleStart,
			endDay: cycleEnd,
			userId: userId
		},
		success: function(response){
			
			const cycleParam = takeCycleParameters(cycleStart, cycleEnd);
			
			createCalendarTable(cycleStart, cycleEnd, cycleParam, response, cycleDuration);
			
		},
		error: function(response){
			console.log(response);	
		}
	})
	
}


function takeCycleParameters(cycleStartDate, cycleEndDate){
	
	let maxDays = new Date(getYear(cycleStartDate), getMonth(cycleStartDate), 0).getDate();
	
	return {
		startRangeMonth: getMonth(cycleStartDate),
		endRangeMonth: getMonth(cycleEndDate),
		startRangeDay: getDay(cycleStartDate),
		endRangeDay: getDay(cycleEndDate),
		startRangeYear: getYear(cycleStartDate),
		maxDays: maxDays
	}
}

function createCalendarTable(cycleStart, cycleEnd, cycleParams, days, cycleDuration){
	
	$('#dates').empty();
	let isFebruaryBetweenRange;
	const typicalMaxCycleDurationInDays = 31;
		
	getMonth(cycleEnd) == 3 && getMonth(cycleStart) == 1 ? isFebruaryBetweenRange = true : isFebruaryBetweenRange = false;
	
	if(cycleDuration > typicalMaxCycleDurationInDays){
		
		mapMultiMonthCalendar(cycleStart, cycleEnd, cycleParams, days);
		return;
	}
	
	if(isFebruaryBetweenRange){
				
		mapMonthFebruaryBetweenRange(cycleParams, days);
				
	}else if(cycleParams.startRangeDay == 1 && cycleParams.endRangeDay == cycleDuration || cycleParams.startRangeMonth == cycleParams.endRangeMonth){
				
		mapMonthCalendar(cycleParams, days);
						
	}else{
							
		mapMonthRangeCalendar(cycleParams, days);
						
	}
	
}

function mapMultiMonthCalendar(cycleStart, cycleEnd, cycleParams, days){
	
	const months = [];
	const startCycleMonth = getMonth(cycleStart);
	const endCycleMonth = getMonth(cycleEnd);
			
	for(let loopIndex = Number(startCycleMonth); loopIndex <= endCycleMonth; loopIndex++){
				
		months.push(loopIndex);
				
	}
			
	for(let loopIndex = 0; loopIndex <= months.length; loopIndex++){
				
		let currentMonth = months[loopIndex];
		let currentMonthMaxDays = new Date(cycleParams.startRangeYear, currentMonth, 0).getDate();
				
		let dayIndex = 0;
				
		if(currentMonth == startCycleMonth){
					
			for(let cycleIndex = cycleParams.startRangeDay; cycleIndex <= currentMonthMaxDays; cycleIndex++){
				dayIndex = takeTableIndexValue(dayIndex, days, cycleIndex);
			}
					
		}else if(currentMonth != months[months.length - 1]){
					
			for(let cycleIndex = 1; cycleIndex <= currentMonthMaxDays; cycleIndex++){
				dayIndex = takeTableIndexValue(dayIndex, days, cycleIndex);
			}
								
		}else{
					
			for(let cycleIndex = 1; cycleIndex <= cycleParams.endRangeDay; cycleIndex++){
					dayIndex = takeTableIndexValue(dayIndex, days, cycleIndex);
			}
					
		}
				
				
	}
}

function mapMonthFebruaryBetweenRange(cycleParams, days){
	
	let dayIndex = 0;
	let i, j, k;
	
	let februaryMaxDays = new Date(cycleParams.startRangeYear, 2, 0).getDate();
	
	for(i = cycleParams.startRangeDay; i <= cycleParams.maxDays; i++){
				
		dayIndex = takeTableIndexValue(dayIndex, days, i);
				
		if(i == cycleParams.maxDays){
								
			for(j = 1; j <= februaryMaxDays; j++){
											
				dayIndex = takeTableIndexValue(dayIndex, days, j);
						
				if(j == februaryMaxDays){
																				
					for(k = 1; k <= cycleParams.endRangeDay; k++){
																							
						dayIndex = takeTableIndexValue(dayIndex, days, k);
								
					}
				}
			}
						
		}
				
	}
}

function mapMonthCalendar(cycleParams, days){
	
	let dayIndex = 0;
	
	for(let j = cycleParams.startRangeDay; j <= cycleParams.endRangeDay; j++){
											
		dayIndex = takeTableIndexValue(dayIndex, days, j);	
		
	}
}

function mapMonthRangeCalendar(cycleParams, days){
	
	let dayIndex = 0;
	
	for(let i = cycleParams.startRangeDay; i <= cycleParams.maxDays; i++){
			
		dayIndex = takeTableIndexValue(dayIndex, days, i);
			
		if(i == cycleParams.maxDays && cycleParams.maxDays != cycleParams.endRangeDay && i != cycleParams.endRangeDay){
							
			for(let j = 1; j <= cycleParams.endRangeDay; j++){
										
				dayIndex = takeTableIndexValue(dayIndex, days, j);
			}
		}
			
	}
	
}

function takeTableIndexValue(dayIndex, days, i){
	
	if(days[dayIndex] !== undefined && i === getDay(days[dayIndex].date)){
		
		displayNotNullTime(i, days[dayIndex]);
		dayIndex++;
	
	}else{
	
		displayNullTime(i);
	
	}
	
	return dayIndex;
}

function displayNotNullTime(index, day){
	
	if(day.timeOut !== null){
		
		$('#dates').append(`
						<tr>
							<td>${index}</td>
							<td>${day.timeIn}</td>
							<td>${day.timeOut}</td>
						</tr>			
					`)
	}else{
		
		$('#dates').append(`
						<tr>
							<td>${index}</td>
							<td>${day.timeIn}</td>
							<td></td>
						</tr>			
					`)
	}
	
}

function displayNullTime(index){
	
	$('#dates').append(`
						<tr>
							<td>${index}</td>
							<td></td>
							<td></td>
						</tr>		
					`)
					
}


