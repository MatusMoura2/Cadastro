package com.mouraforte.cadastro.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.enums.Profiles;

public class TechnicianDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected String name;
	protected String cpf;
	protected String email;
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public TechnicianDTO() {
		super();
	}

	public TechnicianDTO(Technician technician) {
		super();
		this.id = technician.getId();
		this.name = technician.getName();
		this.cpf = technician.getCpf();
		this.email = technician.getEmail();
		this.password = technician.getPassword();
		this.profiles = technician.getProfiles().stream().map(x -> x.getProfileId()).collect(Collectors.toSet());
		this.creationDate = technician.getCreationDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profiles> getProfiles() {
		return profiles.stream().map(x -> Profiles.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfiles(Profiles profiles) {
		this.profiles.add(profiles.getProfileId());
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
