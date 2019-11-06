package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	Optional<Estado> findById(Integer id);

	void deleteById(Long id);
}
