package com.integrador.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.integrador.entities.Interesse;
import com.integrador.entities.pk.InteressePK;

public interface InteresseRepository extends JpaRepository<Interesse, InteressePK> {

	
	@Query("FROM"
			+ " Interesse obj"
			+ "	WHERE "
			+ "	obj.id.usuario.id = :usuarioId")
	Set<Interesse> findByUsuario(Long usuarioId);

	 


	@Query("FROM"
			+ " Interesse obj"
			+ "	WHERE "
			+ "	obj.id.usuario.id = :codUsuario and obj.id.evento.id = :eventoId")
	Interesse findByIdUsuarioIdEventoId(Long codUsuario, Long eventoId);
	
}
