package com.mouraforte.cadastro.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.enums.Profiles;

import jakarta.validation.constraints.NotNull;

public class ClientDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected Long id;
	@NotNull(message = "Campo NOME é obrigatorio")
	protected String name;
	@NotNull(message = "Campo CPF é obrigatorio")
	protected String cpf;
	@NotNull(message = "Campo EMAIL obrigatorio")
	protected String email;
	@NotNull(message = "Campo SENHA obrigatorio")
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate creationDate = LocalDate.now();

	public ClientDTO() {
		super();
		addProfiles(Profiles.CLAINT);
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.email = client.getEmail();
		this.password = client.getPassword();
		this.profiles = client.getProfiles().stream().map(x -> x.getProfileId()).collect(Collectors.toSet());
		this.creationDate = client.getCreationDate();
		addProfiles(Profiles.CLAINT);
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
