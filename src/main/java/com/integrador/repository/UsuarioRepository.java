package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
}
