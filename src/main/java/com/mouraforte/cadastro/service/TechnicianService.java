package com.mouraforte.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Person;
import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.dtos.TechnicianDTO;
import com.mouraforte.cadastro.repository.PersonRepository;
import com.mouraforte.cadastro.repository.TechnicianRepository;
import com.mouraforte.cadastro.service.exceptions.DataIntegrityViolationException;
import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

@Service
public class TechnicianService {
		
	@Autowired
	private TechnicianRepository technicianRepository;
	@Autowired
	private PersonRepository personRepository;
	
	public Technician findById(Long id) {
		Optional<Technician> obj = technicianRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado. Id: "+ id));
	}

	public List<Technician> findAll() {
		return technicianRepository.findAll();
	}

	public Technician create(TechnicianDTO technicianDTO) {
		technicianDTO.setId(null);
		validatorPerCPFendEmail(technicianDTO);
		Technician newTechnician = new Technician(technicianDTO);
		return technicianRepository.save(newTechnician);
	}

	private void validatorPerCPFendEmail(TechnicianDTO technicianDTO) {
		Optional<Person> optionalPerson = personRepository.findByCpf(technicianDTO.getCpf());
		if(optionalPerson.isPresent() && optionalPerson.get().getId() != technicianDTO.getId()) {
			throw new DataIntegrityViolationException("cpf já cadasreado no sistema");
		}
		optionalPerson = personRepository.findByEmail(technicianDTO.getEmail());
		if(optionalPerson.isPresent() && optionalPerson.get().getId() != technicianDTO.getId()) {
			throw new DataIntegrityViolationException("e-mail já cadasreado no sistema");
		}
	}
}
