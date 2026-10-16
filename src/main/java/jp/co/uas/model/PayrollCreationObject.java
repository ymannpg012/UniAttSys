package jp.co.uas.model;

import tools.jackson.databind.JsonNode;

public class PayrollCreationObject {
	
	private JsonNode schedules;
	private String assignment;
	public JsonNode getSchedule() {
		return schedules;
	}
	public void setSchedule(JsonNode schedules) {
		this.schedules = schedules;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

}
