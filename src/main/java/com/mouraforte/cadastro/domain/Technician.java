package com.mouraforte.cadastro.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Technician extends Person {

	private List<Called> calleds = new ArrayList<>();

	public Technician() {
		super();

	}

	public Technician(UUID id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<Called> getCalleds() {
		return calleds;
	}

	public void setCalleds(List<Called> calleds) {
		this.calleds = calleds;
	}

}
