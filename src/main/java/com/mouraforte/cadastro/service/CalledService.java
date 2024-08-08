package com.mouraforte.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.repository.CalledRepository;
import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

@Service
public class CalledService {

	@Autowired
	private CalledRepository calledRepository;

	public Called findById(Long id) {
		Optional<Called> calleds = calledRepository.findById(id);
		return calleds.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado!" + id));
	}

	public List<Called> findAll() {
		return calledRepository.findAll();
	}
}
