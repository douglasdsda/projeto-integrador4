package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer>{
	
	Optional<Pais> findById(Integer id);

	void deleteById(Long id);
}
