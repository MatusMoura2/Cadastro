package com.mouraforte.cadastro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mouraforte.cadastro.domain.dtos.ClientDTO;
import com.mouraforte.cadastro.domain.enums.Profiles;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "cliente")
public class Client extends Person {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Called> calleds = new ArrayList<>();

	public Client() {
		super();
		addProfiles(Profiles.CLAINT);
	}

	public Client(ClientDTO clientDTO) {
		super();
		this.id = clientDTO.getId();
		this.name = clientDTO.getName();
		this.cpf = clientDTO.getCpf();
		this.email = clientDTO.getEmail();
		this.password = clientDTO.getPassword();
		this.profiles = clientDTO.getProfiles().stream().map(x -> x.getProfileId()).collect(Collectors.toSet());
		this.creationDate = clientDTO.getCreationDate();
	}

	public Client(Long id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfiles(Profiles.CLAINT);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}

}
