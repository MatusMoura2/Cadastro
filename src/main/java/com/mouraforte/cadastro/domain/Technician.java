package com.mouraforte.cadastro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mouraforte.cadastro.domain.enums.Profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "tecnico")
public class Technician extends Person {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "technician")
	private List<Called> calleds = new ArrayList<>();

	public Technician() {
		super();
		addProfiles(Profiles.TECHNICIAN);
	}

	public Technician(UUID id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfiles(Profiles.TECHNICIAN);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}

}
