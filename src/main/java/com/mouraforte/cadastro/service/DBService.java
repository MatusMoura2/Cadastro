package com.mouraforte.cadastro.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.enums.Priority;
import com.mouraforte.cadastro.domain.enums.Profiles;
import com.mouraforte.cadastro.domain.enums.Status;
import com.mouraforte.cadastro.repository.CalledRepository;
import com.mouraforte.cadastro.repository.ClientRepository;
import com.mouraforte.cadastro.repository.TechnicianRepository;

@Service
public class DBService {

	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRepository;

	public void startDB() {
		Technician tec0 = new Technician(null, "Doni Moura", "00000000000", "doni.m.test@test.com", "123**");
		tec0.addProfiles(Profiles.ADMIN);
		Technician tec1 = new Technician(null, "Beatriz Moura", "11111111111", "bia_m.test@test.com", "melancia123");
		tec1.addProfiles(Profiles.ADMIN);
		Technician tec2 = new Technician(null, "Luna Moura", "22222222222", "lunam.test@test.com", "m2m3m4m5m6");
		tec2.addProfiles(Profiles.TECHNICIAN);

		Client cli0 = new Client(null, "Donizete Moura", "12345678910", "test.zete@test.com", "botafogo");
		Client cli1 = new Client(null, "Alai Moura", "10987654321", "test.lai@test.com", "deuséamor");
		Client cli2 = new Client(null, "Gonalves Moura", "78787878783", "test.go@test.com", "ccb");

		Called called0 = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado Test", "teste", tec0, cli0);
		Called called1 = new Called(null, Priority.LOW, Status.OPEN, "Chamado Test", "teste", tec1, cli1);
		Called called2 = new Called(null, Priority.MEDIUM, Status.CLOSED, "Chamado Test", "teste", tec2, cli2);

		technicianRepository.saveAll(Arrays.asList(tec0,tec1,tec2));
		clientRepository.saveAll(Arrays.asList(cli0,cli1,cli2));
		calledRepository.saveAll(Arrays.asList(called0,called1,called2));
	}
}
