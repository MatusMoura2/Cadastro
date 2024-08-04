package com.mouraforte.cadastro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mouraforte.cadastro.domain.dtos.TechnicianDTO;
import com.mouraforte.cadastro.domain.enums.Profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "tecnico")
public class Technician extends Person {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "technician")
	private List<Called> calleds = new ArrayList<>();

	public Technician() {
		super();
		addProfiles(Profiles.CLAINT);
	}

	public Technician(Long id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfiles(Profiles.CLAINT);
	}
	
	public Technician(TechnicianDTO technicianDTO) {
		super();
		this.id = technicianDTO.getId();
		this.name = technicianDTO.getName();
		this.cpf = technicianDTO.getCpf();
		this.email = technicianDTO.getEmail();
		this.password = technicianDTO.getPassword();
		this.profiles = technicianDTO.getProfiles().stream().map(x -> x.getProfileId()).collect(Collectors.toSet());
		this.creationDate = technicianDTO.getCreationDate();
	}


	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}

}
