package com.integrador.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.integrador.entities.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	
}
