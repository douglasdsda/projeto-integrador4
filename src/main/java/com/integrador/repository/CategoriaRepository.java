package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
}
