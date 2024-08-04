package com.mouraforte.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.dtos.TechnicianDTO;
import com.mouraforte.cadastro.repository.TechnicianRepository;
import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

@Service
public class TechnicianService {
		
	@Autowired
	private TechnicianRepository technicianRepository;
	
	public Technician findById(Long id) {
		Optional<Technician> obj = technicianRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado. Id: "+ id));
	}

	public List<Technician> findAll() {
		return technicianRepository.findAll();
	}

	public Technician create(TechnicianDTO technicianDTO) {
		technicianDTO.setId(null);
		Technician technician = new Technician(technicianDTO);
		return technicianRepository.save(technician);
	}
}
