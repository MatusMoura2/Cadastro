package com.mouraforte.cadastro.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mouraforte.cadastro.domain.Client;
import com.mouraforte.cadastro.domain.dtos.ClientDTO;
import com.mouraforte.cadastro.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		Client obj = this.clientService.findById(id);
		return ResponseEntity.ok().body(new ClientDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> Clients = clientService.findAll();
		List<ClientDTO> ClientDTOs = Clients.stream().map(obj -> new ClientDTO()).collect(Collectors.toList());
		return ResponseEntity.ok().body(ClientDTOs);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
		Client Client = clientService.create(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Client.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
		Client Client = clientService.update(id, clientDTO);
		return ResponseEntity.ok().body(new ClientDTO(Client));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
