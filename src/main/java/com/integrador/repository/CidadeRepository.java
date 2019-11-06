package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	Optional<Cidade> findById(Integer id);

	void deleteById(Long id);
}
