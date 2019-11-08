package com.integrador.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.integrador.dto.EstadoDTO;
import com.integrador.entities.Estado;
import com.integrador.repository.EstadoRepository;
import com.integrador.services.exceptions.DatabaseException;
import com.integrador.services.exceptions.ResourceNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;

	
	public List<EstadoDTO> findAll() {
		List<Estado> list = repository.findAll();
		return list.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());
	}
	
	public EstadoDTO findById(Integer id) {
		Optional<Estado> obj = repository.findById(id);
		Estado entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new EstadoDTO(entity);
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
	public EstadoDTO update(Integer id, EstadoDTO obj) {
		try {
			Estado entity = repository.getOne(id);
			updateData(entity, obj);
			entity = repository.save(entity);
			return new EstadoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);			
		}
	}
	
	private void updateData(Estado entity, EstadoDTO obj) {
		entity.setNome(obj.getNome());
	}
	
	public EstadoDTO insert(EstadoDTO obj) {
		Estado entity = obj.toEntity();
		entity = repository.save(entity);
		return new EstadoDTO(entity);	
	}
}
