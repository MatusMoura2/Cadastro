package com.mouraforte.cadastro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.dtos.TechnicianDTO;
import com.mouraforte.cadastro.service.TechnicianService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {
	
	@Autowired
	private TechnicianService technicianService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TechnicianDTO> findById(@PathVariable Long id){
		Technician obj = this.technicianService.findById(id);
		return ResponseEntity.ok().body(new TechnicianDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<TechnicianDTO>> findAll(){
		List<Technician> technicians = technicianService.findAll();
		List<TechnicianDTO> technicianDTOs = technicians.stream().map(obj -> new TechnicianDTO()).collect(Collectors.toList());
		return ResponseEntity.ok().body(technicianDTOs);
	}
	
	@PostMapping
	public ResponseEntity<TechnicianDTO> createTechnician(@Valid @RequestBody TechnicianDTO technicianDTO){
		Technician technician = technicianService.create(technicianDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(technician.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
