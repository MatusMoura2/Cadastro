package com.mouraforte.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouraforte.cadastro.domain.Called;
import com.mouraforte.cadastro.domain.Client;

@Repository
public interface CalledRepository extends JpaRepository<Called, Long>{

}
