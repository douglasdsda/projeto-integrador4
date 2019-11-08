package com.integrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrador.entities.Evento;

public interface EventoRepository  extends JpaRepository<Evento, Long> {

}
