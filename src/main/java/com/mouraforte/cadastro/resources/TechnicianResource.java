package com.mouraforte.cadastro.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouraforte.cadastro.domain.Technician;
import com.mouraforte.cadastro.domain.dtos.TechnicianDTO;
import com.mouraforte.cadastro.service.TechnicianService;

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
}
