package com.mouraforte.cadastro.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void startDB() {
		
		Technician tec0 = new Technician(null, "Doni Moura", "432.865.860-30", "doni.m.test@test.com", encoder.encode("123**"));
		tec0.addProfiles(Profiles.ADMIN);
		Technician tec1 = new Technician(null, "Beatriz Moura", "098.700.540-56", "bia_m.test@test.com", encoder.encode("321654"));
		tec1.addProfiles(Profiles.ADMIN);
		Technician tec2 = new Technician(null, "Luna Moura", "448.800.670-16", "lunam.test@test.com", encoder.encode("melamcia123467"));
		tec2.addProfiles(Profiles.TECHNICIAN);
		Technician tec3 = new Technician(null, "Ramon Moura", "550.482.150-95", "r.test@test.com", encoder.encode("ATUSM"));

		Client cli0 = new Client(null, "Donizete Moura", "048.022.490-04", "test.zete@test.com", encoder.encode("botafogo"));
		Client cli1 = new Client(null, "Alai Moura", "169.238.320-55", "test.lai@test.com", encoder.encode("deuseamor"));
		Client cli2 = new Client(null, "Gonalves Moura", "332.277.880-04", "test.go@test.com", encoder.encode("Aleluia"));

		Called called0 = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado Test", "teste", tec0, cli0);
		Called called1 = new Called(null, Priority.LOW, Status.OPEN, "Chamado Test", "teste", tec1, cli1);
		Called called2 = new Called(null, Priority.MEDIUM, Status.CLOSED, "Chamado Test", "teste", tec2, cli2);

		technicianRepository.saveAll(Arrays.asList(tec0,tec1,tec2,tec3));
		clientRepository.saveAll(Arrays.asList(cli0,cli1,cli2));
		calledRepository.saveAll(Arrays.asList(called0,called1,called2));
	}
}
