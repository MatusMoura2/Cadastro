package com.mouraforte.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.dtos.CalledDTO;
import com.mouraforte.cadastro.domain.enums.Priority;
import com.mouraforte.cadastro.domain.enums.Status;
import com.mouraforte.cadastro.repository.CalledRepository;
import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

import jakarta.validation.Valid;

@Service
public class CalledService {

	@Autowired
	private CalledRepository calledRepository;
	@Autowired
	private TechnicianService technicianService;
	@Autowired
	private ClientService clientService;

	public Called findById(Long id) {
		Optional<Called> calleds = calledRepository.findById(id);
		return calleds.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado!" + id));
	}

	public List<Called> findAll() {
		return calledRepository.findAll();
	}

	public Called create(@Valid CalledDTO calledDTO) {
		return calledRepository.save(newCalled(calledDTO));
	}

	private Called newCalled(CalledDTO calledDTO) {
		Technician technician = technicianService.findById(calledDTO.getTechnician());
		Client client = clientService.findById(calledDTO.getTechnician());

		Called called = new Called();
		if (calledDTO.getId() != null) {
			called.setId(calledDTO.getId());
		}

		called.setTechnician(technician);
		called.setClient(client);
		called.setPriority(Priority.toEnum(calledDTO.getPriority()));
		called.setStatus(Status.toEnum(calledDTO.getStatus()));
		called.setName(calledDTO.getName());
		called.setComments(calledDTO.getComments());
		return called;

	}
}
