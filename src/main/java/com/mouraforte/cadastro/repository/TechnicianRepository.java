package com.mouraforte.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouraforte.cadastro.domain.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long>{

}
