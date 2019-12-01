package com.integrador.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.integrador.entities.Categoria;
import com.integrador.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Evento obj INNER JOIN obj.tipos cats WHERE LOWER(obj.titulo) LIKE LOWER(CONCAT('%',:titulo,'%')) AND cats IN :tipos")
	Page<Evento> findByEventNameContainingIgnoreCaseAndCategoriesIn(String titulo, List<Categoria> tipos,
			Pageable pageable);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Evento obj WHERE LOWER(obj.titulo) LIKE LOWER(CONCAT('%',:titulo,'%'))")
	Page<Evento> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Evento obj INNER JOIN obj.tipos cats WHERE LOWER(obj.titulo) LIKE LOWER(CONCAT('%',:titulo,'%')) AND cats IN :tipos")
	List<Evento> findByEventNameContainingIgnoreCaseAndCategoriesIn(String titulo, List<Categoria> tipos);

	List<Evento> findByTituloContainingIgnoreCase(String titulo);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Evento obj INNER JOIN obj.tipos cats WHERE LOWER(obj.titulo) LIKE LOWER(CONCAT('%',:titulo,'%')) AND (cats.id = :categoriaId or :categoriaId = 0)")
	List<Evento> findByCategoriaETitulo(Long categoriaId, String titulo);

  
}
