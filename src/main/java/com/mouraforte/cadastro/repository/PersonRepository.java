package com.mouraforte.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouraforte.cadastro.domain.Person;
import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	Optional<Person> findByCpf(String cpf);
	Optional<Person> findByEmail(String email);
}
