package com.integrador.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrador.dto.EventoDTO;
import com.integrador.entities.Evento;
import com.integrador.repository.CategoriaRepository;
import com.integrador.repository.EventoRepository;
import com.integrador.repository.UsuarioRepository;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EventoService   {

	@Autowired
	private EventoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<EventoDTO> findAll() {
		List<Evento> list = repository.findAll();
		return list.stream().map(e -> new EventoDTO(e)).collect(Collectors.toList());
	}
	
	public EventoDTO findById(Integer id) {
		Evento entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new EventoDTO(entity);
	}
	
	

 
}
