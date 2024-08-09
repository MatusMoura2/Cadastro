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

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.dtos.CalledDTO;
import com.mouraforte.cadastro.service.CalledService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/calleds")
public class CalledResource {

	@Autowired
	private CalledService calledService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CalledDTO> findBYid(@PathVariable Long id) {
		Called called = calledService.findById(id);
		return ResponseEntity.ok().body(new CalledDTO(called));
	}

	@GetMapping
	public ResponseEntity<List<CalledDTO>> findAll(){
		List<Called> calledList = calledService.findAll();
		List<CalledDTO> calledDTOList = calledList.stream().
				map(obj -> new CalledDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(calledDTOList);
	}
	
	@PostMapping
	public ResponseEntity<CalledDTO> create(@Valid @RequestBody CalledDTO calledDTO){
		Called called = calledService.create(calledDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
				path("/{id}").buildAndExpand(called.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
