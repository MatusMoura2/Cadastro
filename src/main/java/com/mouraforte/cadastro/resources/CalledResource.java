package com.mouraforte.cadastro.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.dtos.CalledDTO;
import com.mouraforte.cadastro.service.CalledService;

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
		List<CalledDTO> calledDTOList = calledList.stream().map(obj -> new CalledDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(calledDTOList);
	}

}
