package com.integrador.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.integrador.dto.EventoDTO;
import com.integrador.entities.Evento;
import com.integrador.repository.EventoRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EventoService   {

	@Autowired
	private EventoRepository repository;
	
	public List<EventoDTO> findAll() {
		List<Evento> list = repository.findAll();
		return list.stream().map(e -> new EventoDTO(e)).collect(Collectors.toList());
	}
	
	public EventoDTO findById(Integer id) {
		Evento entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new EventoDTO(entity);
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Transactional
	public EventoDTO update(Integer id, EventoDTO obj) {
		try {
			Evento entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new EventoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Evento entity, EventoDTO obj) {
		entity.setData(obj.getData());
		entity.setDescricao(obj.getDescricao());
		entity.setLocalNome(obj.getLocalNome());
		entity.setTitulo(obj.getTitulo());
	}
	
	

 
}
