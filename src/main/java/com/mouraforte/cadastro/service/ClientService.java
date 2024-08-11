package com.mouraforte.cadastro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.Person;
import com.mouraforte.cadastro.domain.dtos.ClientDTO;
import com.mouraforte.cadastro.repository.ClientRepository;
import com.mouraforte.cadastro.repository.PersonRepository;
import com.mouraforte.cadastro.service.exceptions.DataIntegrityViolationException;
import com.mouraforte.cadastro.service.exceptions.ObjectNotFoundExeception;

import jakarta.validation.Valid;

@Service
public class ClientService {

	@Autowired
	private ClientRepository ClientRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Client findById(Long id) {
		Optional<Client> obj = ClientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado. Id: " + id));
	}

	public List<Client> findAll() {
		return ClientRepository.findAll();
	}

	public Client create(ClientDTO clientDTO) {
		clientDTO.setId(null);
		clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
		validatorPerCPFendEmail(clientDTO);
		Client newClient = new Client(clientDTO);
		return ClientRepository.save(newClient);
	}

	public Client update(Long id, @Valid ClientDTO clientDTO) {
		clientDTO.setId(id);
		Client Client = findById(id);
		validatorPerCPFendEmail(clientDTO);
		Client = new Client(clientDTO);
		return ClientRepository.save(Client);
	}

	public void delete(Long id) {
		Client Client = findById(id);
		if (Client.getCalleds().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico possue ordens de servico e não pode ser deletado!");
		} else {
			ClientRepository.deleteById(id);
		}
	}

	private void validatorPerCPFendEmail(ClientDTO clientDTO) {
		Optional<Person> optionalPerson = personRepository.findByCpf(clientDTO.getCpf());
		if (optionalPerson.isPresent() && optionalPerson.get().getId() != clientDTO.getId()) {
			throw new DataIntegrityViolationException("cpf já cadasreado no sistema");
		}
		optionalPerson = personRepository.findByEmail(clientDTO.getEmail());
		if (optionalPerson.isPresent() && optionalPerson.get().getId() != clientDTO.getId()) {
			throw new DataIntegrityViolationException("e-mail já cadasreado no sistema");
		}
	}
}
