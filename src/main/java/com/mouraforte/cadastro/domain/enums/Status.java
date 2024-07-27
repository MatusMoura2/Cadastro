package com.mouraforte.cadastro.domain.enums;

public enum Status {

	OPEN(0,"OPEN"), PROGRESS(1,"PROGRESS"), CLOSED(2,"CLOSED");
	
	private Integer statuslId;
	private String description;
	
	private Status(Integer statuslId, String description) {
		this.statuslId = statuslId;
		this.description = description;
	}

	public Integer getStatusId() {
		return statuslId;
	}

	public String getDescription() {
		return description;
	}

	public static Status toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for(Status status : Status.values()) {
			if(id.equals(status .getStatusId())) {
				return status ;
			}
		}
		throw new IllegalArgumentException("status  invalid");
	}
}
