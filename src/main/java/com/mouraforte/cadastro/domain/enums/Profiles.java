package com.mouraforte.cadastro.domain.enums;

public enum Profiles {

	ADMIN(0,"ROLE_ADMIN"), CLAINT(1,"ROLE_CLAINT"), TECHNICIAN(3,"ROLE_TECHNICIAN");
	
	private Integer perfilId;
	private String description;
	
	private Profiles(Integer perfilId, String description) {
		this.perfilId = perfilId;
		this.description = description;
	}

	public Integer getPerfilId() {
		return perfilId;
	}

	public String getDescription() {
		return description;
	}

	public static Profiles toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for(Profiles profiles : Profiles.values()) {
			if(id.equals(profiles.getPerfilId())) {
				return profiles;
			}
		}
		throw new IllegalArgumentException("profile invalid");
	}
}
