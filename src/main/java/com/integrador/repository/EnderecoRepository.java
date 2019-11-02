package com.integrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
	Optional<Endereco> findById(Integer id);

	void deleteById(Long id);
}
