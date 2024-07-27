package com.mouraforte.cadastro.domain.enums;

public enum Priority {

	LOW(0,"LOW"), MEDIUM(1,"MEDIUM"), HIGH(2,"HIGH");
	
	private Integer priorityId;
	private String description;
	
	private Priority(Integer priorityId, String description) {
		this.priorityId = priorityId;
		this.description = description;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public String getDescription() {
		return description;
	}

	public static Priority toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for(Priority priority : Priority.values()) {
			if(id.equals(priority.getPriorityId())) {
				return priority;
			}
		}
		throw new IllegalArgumentException("priority invalid");
	}
}
