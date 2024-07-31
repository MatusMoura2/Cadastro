package com.mouraforte.cadastro;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.enums.Priority;
import com.mouraforte.cadastro.domain.enums.Profiles;
import com.mouraforte.cadastro.domain.enums.Status;
import com.mouraforte.cadastro.repository.CalledRepository;
import com.mouraforte.cadastro.repository.ClientRepository;
import com.mouraforte.cadastro.repository.TechnicianRepository;

@SpringBootApplication
public class CadastroApplication implements CommandLineRunner {

	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CalledRepository calledRepository;

	public static void main(String[] args) {
		SpringApplication.run(CadastroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Technician tec1 = new Technician(null, "Matus Moura", "86112378964", "matus.test@test.com", "melancia123");
		tec1.addProfiles(Profiles.ADMIN);

		Client cli1 = new Client(null, "Luna Moura", "06012345678", "test.luna@test.com", "melancia321");

		Called called1 = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado Test", "teste", tec1, cli1);

		technicianRepository.saveAll(Arrays.asList(tec1));
		clientRepository.saveAll(Arrays.asList(cli1));
		calledRepository.saveAll(Arrays.asList(called1));
	}

}
